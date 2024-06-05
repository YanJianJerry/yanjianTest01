package com.tools.fastJson;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.TypeReference;

import java.util.HashMap;
import java.util.Map;

public class FastJsonTest02 {
    public static void main(String[] args) {
        FastJsonTest02 fastJsonTest02 = new FastJsonTest02();

        fastJsonTest02.test01();
    }

    public void test01(){
        Map<String,Object> map1 = new HashMap<>();
        HashMap<String, String> map2 = null;
        map1.put("code","OK");
        String[] strArray = {"123","234"};
        map1.put("data",strArray);

        String str = "{\"message\":\"SUCCESS\",\"data\":[\"2024-01-05\"]}";
        map2 = JSON.parseObject(str, new TypeReference<HashMap<String, String>>(){});
        System.out.println(map2.toString());
        System.out.println(map2.get("data"));

        JSONArray jsonArr1 = JSON.parseArray(map2.get("data"));
        System.out.println(jsonArr1.get(0));

    }
}
