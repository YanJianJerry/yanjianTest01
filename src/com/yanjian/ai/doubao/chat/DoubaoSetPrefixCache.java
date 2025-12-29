package com.yanjian.ai.doubao.chat;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yanjian.ai.doubao.vision.DoubaoVisaonStatic;
import com.yanjian.util.properties.PropertiesUtils;
import okhttp3.*;

import java.io.IOException;

public class DoubaoSetPrefixCache {

    /**
     * AI模型
     */
    public static String EDP_AI_01 = "ep-20250701174142-m2zn8";

    /**
     * API_KEY
     */
    public static String API_KEY = null;
    public static void main(String[] args) {
        PropertiesUtils.init("dev", "D:/config/yanjian/config.properties");
        if(API_KEY == null){
            API_KEY = PropertiesUtils.getProperty("doubao.apikey");
        }
        doPostRequestAction();
    }
//    public static <T,K> Map<String,T> createPrefixCache(Map<String,K> dataMap) {
//
//    }

    public static <K> String doPostRequestAction(){
        OkHttpClient client = new OkHttpClient.Builder()
                .connectTimeout(60, java.util.concurrent.TimeUnit.SECONDS) // 连接超时
                .readTimeout(60, java.util.concurrent.TimeUnit.SECONDS)    // 读取超时
                .writeTimeout(60, java.util.concurrent.TimeUnit.SECONDS)   // 写入超时
                .build();

        String url = "https://ark.cn-beijing.volces.com/api/v3/context/create";//创建缓存的url
        String jsonBody = assembleRequestBody(DoubaoVisaonStatic.requirement_new,EDP_AI_01);
        System.out.println(jsonBody);

        // 构建 JSON 请求体
        RequestBody body = RequestBody.create(
                jsonBody,
                MediaType.get("application/json; charset=utf-8")
        );

        // 构建请求
        Request request = new Request.Builder()
                .url(url)
                .post(body)
                .header("Content-Type", "application/json")
                .header("Accept", "application/json")
                .addHeader("Authorization", "Bearer "+API_KEY)
                .build();

        // 同步执行请求
        try (Response response = client.newCall(request).execute()) {
            if (response.isSuccessful() && response.body() != null) {
                System.out.println("响应状态码: " + response.code());
                System.out.println("响应内容: " + response.body().string());
                return "";
            } else {
                System.err.println("请求失败，状态码: " + response.code());
                if (response.body() != null) {
                    System.err.println("错误信息: " + response.body().string());
                    return "";
                }
                return "";
            }
        } catch (IOException e) {
            System.err.println("请求过程中发生异常: " + e.getMessage());
            e.printStackTrace();
        }
        return "";
    }

    public static String assembleRequestBody(String content,String modelId){
        try {
            // 组装body
            JSONObject message1 = new JSONObject();
            message1.put("role","system");
            message1.put("content",content);
            JSONArray messages = new JSONArray();
            messages.add(message1);

            JSONObject bodyJson = new JSONObject();
            bodyJson.put("messages",messages);
            bodyJson.put("pattern",modelId);//推理接入点ID
            bodyJson.put("ttl",604800);//过期时间7天，每次使用后会重置
            bodyJson.put("mode","common_prefix");//代表使用的前缀缓存模式

            return bodyJson.toString();
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }
}
