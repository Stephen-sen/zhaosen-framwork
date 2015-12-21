//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by Fernflower decompiler)
//

package com.zhaosen.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
@SuppressWarnings({ "rawtypes", "unchecked" })
public class JSONUtil {
    public JSONUtil() {
    }

    public static Object getObject4JsonString(String jsonString, Class pojoCalss) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Object pojo = JSONObject.toBean(jsonObject, pojoCalss);
        return pojo;
    }

    public static Map getMap4Json(String jsonString) {
        JSONObject jsonObject = JSONObject.fromObject(jsonString);
        Iterator keyIter = jsonObject.keys();
        HashMap valueMap = new HashMap();

        while(keyIter.hasNext()) {
            String key = (String)keyIter.next();
            String value = jsonObject.get(key).toString();
            valueMap.put(key, value);
        }

        return valueMap;
    }

    public static Object[] getObjectArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        return jsonArray.toArray();
    }

    public static List getList4Json(String jsonString, Class pojoClass) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        ArrayList list = new ArrayList();

        for(int i = 0; i < jsonArray.size(); ++i) {
            JSONObject jsonObject = jsonArray.getJSONObject(i);
            Object pojoValue = JSONObject.toBean(jsonObject, pojoClass);
            list.add(pojoValue);
        }

        return list;
    }

    public static String[] getStringArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        String[] stringArray = new String[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            stringArray[i] = jsonArray.getString(i);
        }

        return stringArray;
    }

    public static Long[] getLongArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Long[] longArray = new Long[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            longArray[i] = Long.valueOf(jsonArray.getLong(i));
        }

        return longArray;
    }

    public static Integer[] getIntegerArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Integer[] integerArray = new Integer[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            integerArray[i] = Integer.valueOf(jsonArray.getInt(i));
        }

        return integerArray;
    }

    public static Double[] getDoubleArray4Json(String jsonString) {
        JSONArray jsonArray = JSONArray.fromObject(jsonString);
        Double[] doubleArray = new Double[jsonArray.size()];

        for(int i = 0; i < jsonArray.size(); ++i) {
            doubleArray[i] = Double.valueOf(jsonArray.getDouble(i));
        }

        return doubleArray;
    }

    public static String getJsonString4JavaPOJO(Object javaObj) {
        JSONObject json = JSONObject.fromObject(javaObj);
        return json.toString();
    }

    public static void main(String[] args) {
        String jsonStr = "[{ \"firstName\": \"Isaac\", \"lastName\": \"Asimov\", \"genre\": \"science fiction\" },{ \"firstName\": \"Tad\", \"lastName\": \"Williams\", \"genre\": \"fantasy\" },{ \"firstName\": \"Frank\", \"lastName\": \"Peretti\", \"genre\": \"christian fiction\" }]";
        Object[] objs = getObjectArray4Json(jsonStr);
        Object[] var6 = objs;
        int var5 = objs.length;

        for(int var4 = 0; var4 < var5; ++var4) {
            Object object = var6[var4];
            System.out.println(object.toString());
        }

    }
}
