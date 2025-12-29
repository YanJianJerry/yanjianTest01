package com.yanjian.ai.doubao.demo;

import com.yanjian.ai.doubao.chat.DoubaoChatDemo;

import java.util.concurrent.Callable;

public class DoubaoChatThread1 implements Callable<String> {
    private final String message;

    public DoubaoChatThread1(String message) {
        this.message = message;
    }

    @Override
    public String call() throws Exception {
        return DoubaoChatDemo.callDoubaoAi(message);
    }
}
