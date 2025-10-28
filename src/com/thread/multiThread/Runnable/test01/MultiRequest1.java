package com.thread.multiThread.Runnable.test01;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.Callable;

public class MultiRequest1 implements Callable<Integer> {

    private String message;
    int count;

    public MultiRequest1(String message, int count) {
        this.message = message;
        this.count = count;
    }
    @Override
    public Integer call() throws Exception {
        for (int i = 0; i < 10; i++) {
            System.out.println(message+" 计数："+count++ +" 时间："+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH-mm-ss:SSS")));
            System.out.println();
            Thread.sleep((long) (Math.random() * 200));
        }
        return count;
    }
}
