package Utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/1/25.
 */
public class SuccessAnswer {
    public static JSONObject blankAnswer() {
        JSONObject object = new JSONObject();
        object.put("result",true);
        return object;
    }
}
