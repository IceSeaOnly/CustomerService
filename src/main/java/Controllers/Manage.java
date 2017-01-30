package Controllers;

import Entity.Conversation;
import Entity.Message;
import Entity.User;
import Utils.FailAnswer;
import Utils.MD5;
import Service.CommonService;
import Utils.SuccessAnswer;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/25.
 */
@Controller
@RequestMapping("manage")
public class Manage {

    @Resource
    CommonService commonService;

    @RequestMapping("newChat")
    @ResponseBody
    public String newChat(@RequestParam String appKey,
                          @RequestParam String secret,
                          @RequestParam String encodeUrl,
                          @RequestParam Long lid
                          ){
        User u = commonService.getUserByappKeySecret(appKey,secret);
        if(u == null){
            return FailAnswer.answer("appKey and secret is not correct.");
        }else{
            String key = MD5.encryption(System.currentTimeMillis()+appKey);
            String url = null;
            try {
                url = URLDecoder.decode(encodeUrl,"UTF-8");
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
                return FailAnswer.answer("can not decode the url,please encode as UTF-8.");
            }
            Conversation c = new Conversation(key,url,Conversation.WAITSERVICE,lid,u.getId(),System.currentTimeMillis());
            c = (Conversation) commonService.merge(c);
            JSONObject succ = SuccessAnswer.blankAnswer();
            succ.put("cid",c.getId());
            succ.put("ckey",c.getSkey());
            succ.put("lid",c.getLid());
            return succ.toJSONString();
        }
    }

    @RequestMapping("setcs")
    @ResponseBody
    public String setCustomerService(@RequestParam String appKey,
                                     @RequestParam String secret,
                                     @RequestParam Long cid,
                                     @RequestParam Long rid
                                     ){
        User u = commonService.getUserByappKeySecret(appKey,secret);
        if(u == null){
            return FailAnswer.answer("appKey and secret is not correct.");
        }else{
            Conversation c = commonService.getConversationById(u.getId(),cid);
            if(c == null){
                return FailAnswer.answer("you can not access to this conversation.");
            }else{
                c.setRid(rid);
                c.setStatus(Conversation.INSERVICE);
                commonService.update(c);
                return SuccessAnswer.blankAnswer().toJSONString();
            }
        }
    }

    @RequestMapping("list")
    @ResponseBody
    public String listConversation(@RequestParam String appKey,
                                   @RequestParam String secret){
        User u = commonService.getUserByappKeySecret(appKey,secret);
        if(u == null){
            return FailAnswer.answer("认证失败");
        }else{
            ArrayList<Conversation> cs = commonService.listConversation(u.getId());
            JSONObject data = SuccessAnswer.blankAnswer();
            ArrayList<Long>ids = new ArrayList<Long>();
            for (int i = 0; i < cs.size(); i++) {
                ids.add(cs.get(i).getId());
            }
            data.put("conversations",ids);
            return data.toJSONString();
        }
    }

    @RequestMapping("qConversation")
    @ResponseBody
    public String qConversation(@RequestParam String appKey,
                                @RequestParam String secret,
                                @RequestParam Long cid){
        User u = commonService.getUserByappKeySecret(appKey,secret);
        if(u == null){
            return FailAnswer.answer("认证失败");
        }else{
            Conversation c = commonService.getConversationById(u.getId(),cid);
            JSONObject data = SuccessAnswer.blankAnswer();
            data.put("conversation",c);
            return data.toJSONString();
        }
    }

    /**
     * 加入信息到会话中 v2.0
     * type = 0,以用户身份
     * type = 1,以客服身份
     * */
    @RequestMapping("add_msg")
    @ResponseBody
    public String add_msg(@RequestParam String appKey,
                          @RequestParam String secret,
                          @RequestParam Long cid,
                          @RequestParam String word,
                          @RequestParam int type){
        User u = commonService.getUserByappKeySecret(appKey,secret);
        if(u == null){
            return FailAnswer.answer("认证失败");
        }else{
            Conversation c = commonService.getConversationById(u.getId(),cid);
            if(c != null){
                if(type == 1 || type == 0){
                    try {
                        commonService.save(new Message(cid,type, URLDecoder.decode(word,"UTF-8")));
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }else
                    return FailAnswer.answer("type参数仅能为0或1");
            }else{
                return FailAnswer.answer("会话归属错误");
            }
            JSONObject data = SuccessAnswer.blankAnswer();
            return data.toJSONString();
        }
    }
}
