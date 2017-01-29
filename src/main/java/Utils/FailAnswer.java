package Utils;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/1/25.
 */
public class FailAnswer {

    public static String answer(String reason) {
        JSONObject object = new JSONObject();
        object.put("result",false);
        object.put("reason",reason);
        return object.toJSONString();
    }


}
