package Utils;

/**
 * Created by Administrator on 2017/1/28.
 */
public class CallBackReqeust implements Runnable {
    private String url;
    private String params;

    public CallBackReqeust(String url, String params) {
        this.url = url;
        this.params = params;
    }

    public void run() {
        makeReqeust();
    }

    public void makeReqeust(){
        if(url.startsWith("http://")||url.startsWith("https://")){
            HttpUtils.sendGet(url,params);
        }
    }
}
