package com.thread.multiThread.producerAndConsumer;

import sun.nio.cs.ext.SJIS;

public class Consumer1 implements Runnable{

    private final SharedResource1 sharedResource1;
    private int count;

    public Consumer1(SharedResource1 sharedResource1,int count) {
        this.sharedResource1 = sharedResource1;
        this.count = count;
    }

    @Override
    public void run() {
        for (int i = 0; i < count; i++) {
            String data = sharedResource1.takeData();
            System.out.println("消费数据："+data);
            //
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }

    }
}
