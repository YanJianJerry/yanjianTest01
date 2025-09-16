package com.yanjian.ai.intf;

import java.util.Map;

/**
 *
 */
public interface aiApi {
    //
    void callAi();
    public abstract String assembleRequestBody(Map<String, Object> bodyMap);
    public void sendRequest(String body);
    abstract void getJsonResult(String result);
}
