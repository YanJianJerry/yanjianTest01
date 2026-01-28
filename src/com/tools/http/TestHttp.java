package com.tools.http;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestHttp {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();

    public static void main(String[] args) {
        test2();
    }

    public static void test1(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            // 构造 JSON 请求体
            Map<String, Object> jsonMap = new HashMap<>();
            HashMap<String, Object> textMap = new HashMap<>();
            ArrayList<String> mentioned_mobile_list = new ArrayList<>();
            mentioned_mobile_list.add("@all");
            textMap.put("mentioned_mobile_list", mentioned_mobile_list);
            textMap.put("content", "今日有雨");
            jsonMap.put("text", textMap);
            jsonMap.put("msgtype", "text");

            String json = mapper.writeValueAsString(jsonMap);

            RequestBody body = RequestBody.create(json, JSON);

            // 构建请求
            Request request = new Request.Builder()
                    .url("") // 替换为你自己的 API 地址
                    .post(body)
                    .build();

            // 发送请求并获取响应
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                System.out.println(response.body().string());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void test2(){
        try {
            ObjectMapper mapper = new ObjectMapper();
            // 构造 JSON 请求体
            Map<String, Object> jsonMap = new HashMap<>();
            HashMap<String, Object> textMap = new HashMap<>();
            ArrayList<String> mentioned_mobile_list = new ArrayList<>();
            mentioned_mobile_list.add("@all");
            textMap.put("mentioned_mobile_list", mentioned_mobile_list);
            textMap.put("content", "各位，点了外卖没");
            jsonMap.put("text", textMap);
            jsonMap.put("msgtype", "text");

            String json = mapper.writeValueAsString(jsonMap);

            RequestBody body = RequestBody.create(json, JSON);

            // 构建请求
            Request request = new Request.Builder()
                    .url("") // 替换为你自己的 API 地址
                    .post(body)
                    .build();

            // 发送请求并获取响应
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                assert response.body() != null;
                System.out.println(response.body().string());
            }
        } catch (IOException e){
            e.printStackTrace();
        }
    }

    private static class MyAuthenticator implements Authenticator {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
            // 获取原始请求
            Request request = response.request();

            // 检查响应码，如果不是401，则不需要重新认证
            if (response.code() != 401) {
                return null;
            }

            // 获取原始请求的授权信息
            String originalAuth = request.header("Authorization");
            return request;
        }
    }
}
