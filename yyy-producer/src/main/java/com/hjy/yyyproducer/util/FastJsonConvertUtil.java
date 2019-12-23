package com.hjy.yyyproducer.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class FastJsonConvertUtil {

    public static String convertObjectToJSON(Object object) {
        try {
            return JSON.toJSONString(object);
        } catch (Exception e) {
            throw new RuntimeException("转化为JSON失败!");
        }
    }

    public static Object convertJSONToObject(String string, Class o) {
        return JSONObject.parseObject(string, o);
    }
}
