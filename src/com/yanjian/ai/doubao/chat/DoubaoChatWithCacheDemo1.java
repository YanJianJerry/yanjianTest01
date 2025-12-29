package com.yanjian.ai.doubao.chat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yanjian.util.properties.PropertiesUtils;
import okhttp3.*;

import java.util.concurrent.TimeUnit;

public class DoubaoChatWithCacheDemo1 {
    /**
     * ai请求url，使用上下文缓存url
     */
    public static String BASEURL = "https://ark.cn-beijing.volces.com/api/v3/context/chat/completions";

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
        String messages = "请在第一个页面生成一个产品输入框。";
        System.out.println(callDoubaoAi(messages));
    }

    /**
     * AI调用入口
     * @param messages
     * @return
     */
    public static String callDoubaoAi(String messages){

        // 组装对话内容
        String content = messages;

        return sendRequestToDaobaoAi(content,EDP_AI_01);
    }

    /**
     * 组装请求内容以及发送请求
     * @param content 内容
     * @param model 调用AI的模型
     * @return
     */
    public static String sendRequestToDaobaoAi(String content,String model){
        try {
            // 组装AI接口JSON
            JSONObject message1 = new JSONObject();
            message1.put("role","user");//对话角色
            message1.put("content",content);//对话内容
            JSONArray messages = new JSONArray();
            messages.add(message1);

            JSONObject bodyJson = new JSONObject();
            bodyJson.put("messages",messages);
            bodyJson.put("pattern",model);//调用的AI模型
            bodyJson.put("context_id","ctx-20250701182432-4mszl");//调用的AI模型
//            bodyJson.put("max_tokens",8000);
//            bodyJson.put("response_format",new JSONObject().put("type","json_object"));//[text, json_object]
            bodyJson.put("temperature",0.2);//ossible values: <= 2
            bodyJson.put("top_p",0.8);//Possible values: <= 1


            // 创建请求
            OkHttpClient client = new OkHttpClient().newBuilder()
//                    .addInterceptor(logging)
                    .connectTimeout(60, TimeUnit.SECONDS) // 连接超时 60 秒
                    .readTimeout(60, TimeUnit.SECONDS)    // 读取超时 60 秒
                    .writeTimeout(60, TimeUnit.SECONDS)   // 写入超时 60 秒
                    .build();
            MediaType mediaType = MediaType.parse("application/json");

            // 组装请求的body
            RequestBody body = RequestBody.create(bodyJson.toJSONString(), mediaType);

            // 请求构建
            Request request = new Request.Builder()
                    .url(BASEURL)
                    .method("POST", body)
                    .addHeader("Content-Type", "application/json")
                    .addHeader("Accept", "application/json")
                    .addHeader("Authorization", "Bearer "+API_KEY)
                    .build();

            // 执行请求
            String res;
            try (Response response = client.newCall(request).execute()) {
                if (response.body() == null) {
                    throw new RuntimeException("Response body is null");
                }
                // 返回提取的有效json内容
                res = getJsonResult(response.body().string());
            }
            return res;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 从AI返回的JSON中提取返回的对话内容content
     * @param resp
     * @return
     */
    public static String getJsonResult(String resp){
        try {
            // 从返回数据中提取content
            JSONObject respJson = JSON.parseObject(resp);
            JSONArray choices = respJson.getJSONArray("choices");
            JSONObject message = choices.getJSONObject(0).getJSONObject("message");
            String returnContent = message.get("content").toString();
            // 返回
            return returnContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
