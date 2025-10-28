package com.thread.multiThread.producerAndConsumer;

public class Producer1 implements Runnable{

    private final SharedResource1 sharedResource1;
    private String[] inputArray;

    public Producer1(SharedResource1 sharedResource1, String[] inputArray) {
        this.sharedResource1 = sharedResource1;
        this.inputArray = inputArray;
    }

    public Producer1(SharedResource1 sharedResource1) {
        this.sharedResource1 = sharedResource1;
    }
    @Override
    public void run() {
        for (String data : inputArray){
            // 将数据放入公共资源中
            sharedResource1.putData(data);
            // 模拟生产耗时
            try {
                Thread.sleep((long) (Math.random() * 1000));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}
