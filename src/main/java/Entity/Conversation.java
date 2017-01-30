package Entity;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/1/25.
 * 会话层
 */
@Table
@Entity
public class Conversation {

    public static int WAITSERVICE = 0;
    public static int INSERVICE = 1;
    public static int ENDINGSERVICE = 2;
    public static int SERVICEENDED = 3;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String skey;
    private String callBackUrl;
    private int status;
    private Long lid;
    private Long rid;
    private Long managerId;
    private Long createTime;
    private Long lendTime;
    private Long rendTime;
    private int score;
    private String endText;
    private String userToken;
    private String serverToken;
    private String callBcakToken;

    public Conversation(String skey, String callBackUrl, int status, Long lid, Long managerId, Long createTime,String ut,String st,String cbt) {
        this.skey = skey;
        this.callBackUrl = callBackUrl;
        this.status = status;
        this.lid = lid;
        this.rid = null;
        this.managerId = managerId;
        this.createTime = createTime;
        this.lendTime = null;
        this.rendTime = null;
        this.score = -1;
        this.endText = null;
        this.userToken = ut;
        this.serverToken = st;
        this.callBackUrl = cbt;
    }

    public Conversation() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getSkey() {
        return skey;
    }

    public void setSkey(String key) {
        this.skey = key;
    }

    public String getCallBackUrl() {
        return callBackUrl;
    }

    public void setCallBackUrl(String callBackUrl) {
        this.callBackUrl = callBackUrl;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Long getLid() {
        return lid;
    }

    public void setLid(Long lid) {
        this.lid = lid;
    }

    public Long getRid() {
        return rid;
    }

    public void setRid(Long rid) {
        this.rid = rid;
    }

    public Long getManagerId() {
        return managerId;
    }

    public void setManagerId(Long managerId) {
        this.managerId = managerId;
    }

    public Long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Long createTime) {
        this.createTime = createTime;
    }

    public Long getLendTime() {
        return lendTime;
    }

    public void setLendTime(Long lendTime) {
        this.lendTime = lendTime;
    }

    public Long getRendTime() {
        return rendTime;
    }

    public void setRendTime(Long rendTime) {
        this.rendTime = rendTime;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public String getEndText() {
        return endText;
    }

    public void setEndText(String endText) {
        this.endText = endText;
    }

    public String getUserToken() {
        return userToken;
    }

    public void setUserToken(String userToken) {
        this.userToken = userToken;
    }

    public String getServerToken() {
        return serverToken;
    }

    public void setServerToken(String serverToken) {
        this.serverToken = serverToken;
    }

    public String getCallBcakToken() {
        return callBcakToken;
    }

    public void setCallBcakToken(String callBcakToken) {
        this.callBcakToken = callBcakToken;
    }
}
