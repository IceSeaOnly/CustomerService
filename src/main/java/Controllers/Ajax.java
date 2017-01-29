package Controllers;

import Entity.Conversation;
import Entity.Message;
import Utils.CallBackReqeust;
import Utils.FailAnswer;
import Utils.SuccessAnswer;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import Service.CommonService;
import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/25.
 */
@Controller
@RequestMapping("ajax")
public class Ajax {
    @Resource
    CommonService commonService;

    @RequestMapping(value = "getMsg",produces = "application/json;charset=UTF-8")
    @ResponseBody
    public String getMsg(
            @RequestParam Long cid,
            @RequestParam Long uid,
            @RequestParam String ckey
            ){
        Conversation c = commonService.getConversationByKeyId(cid,ckey);
        if(c == null){
            return FailAnswer.answer("非法参数");
        }
        if(c.getStatus() == Conversation.ENDINGSERVICE)
            return FailAnswer.answer("endingservice");
        if(c.getStatus() == Conversation.SERVICEENDED)
            return FailAnswer.answer("serviceended");

        if(c.getLid().equals(uid)){
            ArrayList<Message>msgs = commonService.listAllMsg(cid);
            JSONObject data = SuccessAnswer.blankAnswer();
            data.put("msgs",msgs);
            return data.toJSONString();
        }else{
            if(c.getRid() != null && c.getRid().equals(uid)){
                ArrayList<Message>msgs = commonService.listAllMsg(cid);
                JSONObject data = SuccessAnswer.blankAnswer();
                data.put("msgs",msgs);
                return data.toJSONString();
            }else{
                return FailAnswer.answer("非法参数");
            }
        }
    }

    @RequestMapping(value = "submit_msg",method = RequestMethod.POST)
    @ResponseBody
    public String submit_msg(@RequestParam String ckey,
                             @RequestParam Long cid,
                             @RequestParam Long uid,
                             @RequestParam String msg){
        Conversation c = commonService.getConversationByKeyId(cid,ckey);
        if(c == null)
            return "非法参数";
        if(c.getStatus()>Conversation.INSERVICE)
            return "本次服务已结束，发送失败";
        if(c.getLid().equals(uid)){
            commonService.save(new Message(cid,0,msg));
            makeCallBack(c.getCallBackUrl(),cid,"user");
            return "true";
        }else if(c.getRid().equals(uid)){
            commonService.save(new Message(cid,1,msg));
            makeCallBack(c.getCallBackUrl(),cid,"server");
            return "true";
        }else{
            return "非法参数";
        }

    }

    private void makeCallBack(String url, Long cid, String t){
        new Thread(new CallBackReqeust(url,"notice=newMsg&cid="+cid+"&type="+t)).start();
    }
}
