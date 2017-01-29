package Controllers;

import Entity.Conversation;
import Utils.CallBackReqeust;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import Service.CommonService;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;


/**
 * Created by Administrator on 2017/1/25.
 */
@Controller
@RequestMapping("service")
public class Service {
    @Resource
    CommonService commonService;

    @RequestMapping("enterChat")
    public String enterChat(
            @RequestParam String ckey,
            @RequestParam Long cid,
            @RequestParam Long uid,
            ModelMap map
            ){
        Conversation c = commonService.getConversationByKeyId(cid,ckey);

        map.put("ckey",ckey);
        map.put("cid",cid);
        map.put("uid",uid);
        if(c == null){
            map.put("err","invalid parameters");
            return "error_page";
        }else{
            map.put("conversation",c);
            if(c.getLid().equals(uid)){
                map.put("msgs",commonService.listAllMsg(cid));
                return "customer_side";
            }else{
                if(c.getRid() == null){
                    map.put("err","invalid parameters");
                    return "error_page";
                }
                map.put("msgs",commonService.listAllMsg(cid));
                return "cs_side";
            }
        }
    }

    @RequestMapping(value = "rend",method = RequestMethod.POST)
    public String RightEndService(
            @RequestParam String ckey,
            @RequestParam Long cid,
            @RequestParam Long uid,
            @RequestParam String endText,
            ModelMap map
    ){
        Conversation c = commonService.getConversationByKeyId(cid,ckey);
        if(c == null){
            map.put("err","invalid parameters");
            return "error_page";
        }
        if(c.getRid().equals(uid)){
            c.setStatus(c.getStatus() == Conversation.ENDINGSERVICE?Conversation.SERVICEENDED:Conversation.ENDINGSERVICE);
            c.setRendTime(System.currentTimeMillis());
            c.setEndText(endText);
            commonService.update(c);
            makeCallBack(c.getCallBackUrl(),"notice=serverEnd&cid="+cid);
        }else{
            map.put("err","invalid parameters");
            return "error_page";
        }
        return "finish_page";
    }

    @RequestMapping(value = "lend",method = RequestMethod.GET)
    public String LeftEndService(
            @RequestParam String ckey,
            @RequestParam Long cid,
            @RequestParam Long uid,
            @RequestParam int score,
            ModelMap map
    ){
        Conversation c = commonService.getConversationByKeyId(cid,ckey);
        if(c == null){
            map.put("err","invalid parameters");
            return "error_page";
        }
        if(c.getLid().equals(uid)){
            c.setStatus(c.getStatus() == Conversation.ENDINGSERVICE?Conversation.SERVICEENDED:Conversation.ENDINGSERVICE);
            c.setLendTime(System.currentTimeMillis());
            c.setScore(score);
            commonService.update(c);
            makeCallBack(c.getCallBackUrl(),"notice=userEnd&cid="+cid);
        }else{
            map.put("err","invalid parameters");
            return "error_page";
        }
        return "finish_page";
    }

    private void makeCallBack(String url,String param){
        new Thread(new CallBackReqeust(url,param)).start();
    }

}
