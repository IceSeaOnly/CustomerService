package Entity;

import Utils.TimeFormat;

import javax.persistence.*;

/**
 * Created by Administrator on 2017/1/25.
 * 消息层
 */
@Table
@Entity
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long pid;
    private int LR; // left = 0,right = 1
    private String msg;
    private Long time;
    private String timetext;

    public Message(Long pid, int LR, String msg) {
        this.pid = pid;
        this.LR = LR;
        this.msg = msg;
        this.time = System.currentTimeMillis();
        this.timetext = TimeFormat.format(this.time);
    }

    public Message() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    public int getLR() {
        return LR;
    }

    public void setLR(int LR) {
        this.LR = LR;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getTimetext() {
        return timetext;
    }

    public void setTimetext(String timetext) {
        this.timetext = timetext;
    }

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }
}
