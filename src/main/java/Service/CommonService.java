package Service;

import Dao.CommonDao;
import Entity.Conversation;
import Entity.Message;
import Entity.User;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/25.
 */
@Service
public class CommonService {
    @Resource
    CommonDao commonDao;

    public User getUserByEmailPass(String email, String pass) {
        return commonDao.login(email,pass);
    }

    public boolean uniqueEmail(String email) {
        return commonDao.uniqueEmail(email);
    }

    public void save(Object o) {
        commonDao.save(o);
    }

    public Object merge(Object o) {
        return commonDao.merge(o);
    }

    public User getUserByappKeySecret(String appKey, String secret) {
        return commonDao.getUserByappKeySecret(appKey,secret);
    }

    public Conversation getConversationById(Long mid,Long cid) {
        return commonDao.getConversationById(mid,cid);
    }

    public void update(Object o) {
        commonDao.update(o);
    }

    public Conversation getConversationByKeyId(Long cid, String ckey) {
        return commonDao.getConversationByKeyId(cid,ckey);
    }

    public ArrayList<Message> listAllMsg(Long cid){
        ArrayList<Message>msgs = commonDao.listAllMsg(cid);
        return msgs == null?new ArrayList<Message>():msgs;
    }

    public ArrayList<Conversation> listConversation(Long id) {
        ArrayList<Conversation>r = commonDao.listConversation(id);
        return r==null?new ArrayList<Conversation>():r;
    }
}
