package test.demo;

import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.StringRequestEntity;

public class HttpTest01 {
    public static void main(String[] args) {
        HttpTest01 httpTest01 = new HttpTest01();
        httpTest01.test();
    }

 public void test() {
     // 返回的结果
     String jsonResult = "";
     try {
         HttpClient client = new HttpClient();
         // 连接超时
         client.getHttpConnectionManager().getParams().setConnectionTimeout(3 * 1000);
         // 读取数据超时
         client.getHttpConnectionManager().getParams().setSoTimeout(3 * 60 * 1000);
         client.getParams().setContentCharset("UTF-8");
         PostMethod postMethod = new PostMethod("https://api-aiplatform-prod.ab-inbev.cn/str-ocr/ocr/pass_date");

         postMethod.setRequestHeader("content-type", "application/json");

         String json = new StringBuilder().append("{\"imgs\":[\"").append("/9j/4AAQSkZJRgABAQEAAAAAAAD/4QES").append("\"]}").toString();

         // 非空
         if (null != json && !"".equals(json)) {
             StringRequestEntity requestEntity = new StringRequestEntity(json, "application/json", "UTF-8");
             System.out.println(requestEntity.getContent());
             postMethod.setRequestEntity(requestEntity);
         }
         int status = client.executeMethod(postMethod);
         if (status == HttpStatus.SC_OK) {
             jsonResult = postMethod.getResponseBodyAsString();
         } else {
             throw new RuntimeException("接口连接失败！");
         }
     } catch (Exception e) {
         throw new RuntimeException("接口连接失败！");
     }
 }

}

