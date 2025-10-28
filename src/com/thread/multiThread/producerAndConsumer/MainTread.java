package com.thread.multiThread.producerAndConsumer;

public class MainTread {
    public static void main(String[] args) {
        SharedResource1 sharedResource1 = new SharedResource1();
        String[] dataArr = new String[]{"01","03","消息1","消息2"};

        Thread producer1Thread = new Thread(new Producer1(sharedResource1,dataArr),"producer1");
        Thread consumer1Thread = new Thread(new Consumer1(sharedResource1,dataArr.length),"consumer1");

        // 启动线程
        producer1Thread.start();
        consumer1Thread.start();

        try {
            producer1Thread.join();
            consumer1Thread.join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
