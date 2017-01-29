package Dao;

import Entity.Conversation;
import Entity.Message;
import Entity.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.ArrayList;

/**
 * Created by Administrator on 2017/1/25.
 */
@Repository
public class CommonDao {
    @Resource
    SessionFactory sessionFactory;

    public User login(String email, String pass) {
        Session session = sessionFactory.openSession();
        User u = (User) session.createQuery("from User where email = :E and pass = :P")
                .setParameter("E",email)
                .setParameter("P",pass)
                .uniqueResult();
        session.close();
        return u;
    }

    public boolean uniqueEmail(String email) {
        Session session = sessionFactory.openSession();
        Long s = (Long) session.createQuery("select coalesce(count (*),0) from User where email = :E")
                .setParameter("E",email)
                .uniqueResult();
        session.close();
        return s<2;
    }

    public void save(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.save(o);
        session.getTransaction().commit();
        session.close();
    }

    public Object merge(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        Object t = session.merge(o);
        session.getTransaction().commit();
        session.close();
        return t;
    }

    public User getUserByappKeySecret(String appKey, String secret) {
        Session session = sessionFactory.openSession();
        User u = (User) session.createQuery("from User where appKey = :A and secret = :S")
                .setParameter("A",appKey)
                .setParameter("S",secret)
                .uniqueResult();
        session.close();
        return u;
    }

    public Conversation getConversationById(Long mid,Long cid) {
        Session session = sessionFactory.openSession();
        Conversation c = (Conversation) session.createQuery("from Conversation where id = :I and managerId = :M")
                .setParameter("M",mid)
                .setParameter("I",cid)
                .uniqueResult();
        session.close();
        return c;
    }

    public void update(Object o) {
        Session session = sessionFactory.openSession();
        session.beginTransaction();
        session.update(o);
        session.getTransaction().commit();
        session.close();
    }

    public Conversation getConversationByKeyId(Long cid, String ckey) {
        Session session = sessionFactory.openSession();
        Conversation c = (Conversation) session.createQuery("from Conversation where id = :I and skey = :K")
                .setParameter("I",cid)
                .setParameter("K",ckey)
                .uniqueResult();
        session.close();
        return c;
    }

    public ArrayList<Message> listAllMsg(Long cid) {
        Session session = sessionFactory.openSession();
        ArrayList<Message>msgs = (ArrayList<Message>) session.createQuery("from Message where pid = :P")
                .setParameter("P",cid)
                .list();
        session.close();
        return msgs;
    }

    public ArrayList<Conversation> listConversation(Long id) {
        Session session = sessionFactory.openSession();
        ArrayList<Conversation> r = (ArrayList<Conversation>) session.createQuery("from Conversation where managerId = :I")
                .setParameter("I",id)
                .list();
        session.close();
        return r;
    }
}
