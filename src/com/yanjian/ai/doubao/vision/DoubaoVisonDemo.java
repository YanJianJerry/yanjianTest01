package com.yanjian.ai.doubao.vision;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yanjian.util.io.file.FileUtil;
import com.yanjian.util.io.image.Base64Util;
import com.yanjian.util.properties.PropertiesUtils;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.nio.charset.StandardCharsets;
import java.util.Map;

public class DoubaoVisonDemo {

    public static String BASEURL = "https://ark.cn-beijing.volces.com/api/v3/chat/completions";

    public static String API_KEY = null;

    public static String DOUBAO_1_5_PRO_32 = "doubao-1-5-pro-32k-250115";
    public static String DOUBAO_1_5_LITE_32 = "doubao-1-5-lite-32k-250115";
    public static String DOUBAO_1_5_PRO_256 = "doubao-1-5-pro-256k-250115";
    public static String DOUBAO_PRO_32 = "doubao-pro-32k-241215";
    public static String DEEPSEEK_R1 = "deepseek-r1-250120";
    public static String DEEPSEEK_V3 = "deepseek-v3-250324";
    public static String DEEPSEEK_R1_7B = "deepseek-r1-distill-qwen-7b-250120";
    public static String DEEPSEEK_R1_32B = "deepseek-r1-distill-qwen-32b-250120";

    public static String EDP_AI_01 = "ep-20250524190318-jm94z";

    public static String VISION_1_5_PRO = "doubao-1.5-vision-pro-250328";
    public static String VISION_1_5_LITE = "doubao-1.5-vision-lite-250315";
    public static String VISION_1_5_PRO_32 = "doubao-1-5-vision-pro-32k-250115";
    public static String VISION_THINK_1_5_PRO = "doubao-1-5-thinking-vision-pro-250428";

    public static String INPUT_PRICE = "0.003";
    public static String OUTPUT_PRICE = "0.009";

//    public static String IMAGE_PATH = "D:\\data\\images\\excel截图1.png";
    public static String IMAGE_PATH = "D:\\data\\images\\手写界面1.png";
    public static String IMAGE_TYPE = "png";

    public static void main(String[] args) {
        PropertiesUtils.init("dev", "D:/config/yanjian/config.properties");
        if(API_KEY == null){
            API_KEY = PropertiesUtils.getProperty("doubao.apikey");
        }
        String response = callDoubaoVisionAi("");
    }

    public static String callDoubaoVisionAi(String messages) {
        try {
            String text = DoubaoVisaonStatic.requirement_new+messages;
//            String base64_image = getImageBase64(IMAGE_PATH);
            String image_url = "https://bucket-zxww.oss-cn-hangzhou.aliyuncs.com/edp/excel%E6%88%AA%E5%9B%BE2.png";
            return sendRequestToDaobaoVsionAi(BASEURL,text,VISION_1_5_PRO,image_url);
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String sendRequestToDaobaoVsionAi(String url, String text, String modelId,String base64_image) throws Exception {
        long startTimeMillis = System.currentTimeMillis();

        //组装请求内容
        String body = assembleRequestBody(text,modelId,base64_image);

        // 创建 HttpClient 实例
        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            // 创建 HttpPost 请求
            HttpPost httpPost = new HttpPost(url);

            // 设置请求头
            httpPost.setHeader("Content-Type", "application/json");
            httpPost.setHeader("Accept", "application/json");
            httpPost.setHeader("Authorization", "Bearer "+API_KEY);

            // 超时设置
            RequestConfig requestConfig = RequestConfig.custom()
                    .setConnectTimeout(60000) // 连接超时 60 秒
                    .setSocketTimeout(60000)  // 读取超时 60 秒
                    .build();

            HttpClients.custom().setDefaultRequestConfig(requestConfig).build();

            // 设置请求体
            StringEntity requestEntity = new StringEntity(body, StandardCharsets.UTF_8);
            httpPost.setEntity(requestEntity);

            // 发送请求并获取响应
            HttpResponse response = httpClient.execute(httpPost);

            // 解析响应
            if (response.getEntity() != null) {
                // 计算请求耗时
                String entity =  EntityUtils.toString(response.getEntity(), StandardCharsets.UTF_8);
                long costTime = (System.currentTimeMillis() - startTimeMillis) / 1000;

                String res = getJsonResult(entity);
                System.out.println("AI花费时间(秒)：" + costTime);
                System.out.println(res);
                System.out.println("***********豆包结果end************");
                return res;
            } else {
                throw new RuntimeException("Response body is null");
            }
        }
    }

    public static String assembleRequestBody(String text,String modelId,String image_url){
        try {
            //

            // 组装content
            JSONObject iamge_url = new JSONObject();
//            iamge_url.put("url","data:image/"+IMAGE_TYPE+";base64,"+base64_image);
            iamge_url.put("url",image_url);
            JSONObject content_1 = new JSONObject();
            content_1.put("type","image_url");
            content_1.put("image_url",iamge_url);

            JSONObject content_2 = new JSONObject();
            content_2.put("type","text");
            content_2.put("text",text);



            JSONArray content = new JSONArray();
            content.add(content_1);
            content.add(content_2);

            // 组装body
            JSONObject message1 = new JSONObject();
            message1.put("role","user");
            message1.put("content",content);
            JSONArray messages = new JSONArray();
            messages.add(message1);

            JSONObject bodyJson = new JSONObject();
            bodyJson.put("messages",messages);
            bodyJson.put("model",modelId);
//            bodyJson.put("max_tokens",4096);//最大tokens返回
//            bodyJson.put("response_format",new JSONObject().put("type","json_object"));//[text, json_object]
            bodyJson.put("temperature",0.5);//ossible values: <= 2
            bodyJson.put("top_p",1);//Possible values: <= 1

            String body = bodyJson.toString();
//            body = body.replace("\"202505252222","f\"");

            System.out.println("#########请求##############");
            System.out.println(body);

            return body;
        }catch (Exception e){
            e.printStackTrace();
            return null;
        }
    }

    public static String getImageBase64(String imagePath) throws IOException {
        // 读取图片文件并转换为Base64编码
        byte[] imgData = FileUtil.readFileByBytes(imagePath);
        return Base64Util.encode(imgData);
    }

    public static String getJsonResult(String resp){
        try {
            JSONObject respJson = JSON.parseObject(resp);
//            System.out.println(resp);
            JSONArray choices = respJson.getJSONArray("choices");
            JSONObject message = choices.getJSONObject(0).getJSONObject("message");

            System.out.println("***********豆包结果Start************");
//            System.out.println(message.get("content"));

            String _prompt_tokens = new JSONObject((Map)respJson.get("usage")).get("prompt_tokens").toString();
            String _completion_tokens = new JSONObject((Map)respJson.get("usage")).get("completion_tokens").toString();
            System.out.println("prompt_tokens:"+_prompt_tokens);
            System.out.println("completion_tokens:"+_completion_tokens);
            BigDecimal prompt_tokens = new BigDecimal(_prompt_tokens);
            BigDecimal completion_tokens = new BigDecimal(_completion_tokens);
            BigDecimal K = new BigDecimal("1000");
            BigDecimal input_price = new BigDecimal(INPUT_PRICE);
            BigDecimal output_price = new BigDecimal(OUTPUT_PRICE);
            BigDecimal amount = input_price.multiply(prompt_tokens).add(output_price.multiply(completion_tokens));
            System.out.println("AI金额开销："+amount.divide(K,5, RoundingMode.HALF_UP)+"元");
//            System.out.println("***********豆包结果end************");

            return message.get("content").toString();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
