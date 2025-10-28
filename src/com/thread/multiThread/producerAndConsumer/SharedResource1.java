package com.thread.multiThread.producerAndConsumer;

import kotlin.jvm.Synchronized;

import java.util.List;
import java.util.Map;

public class SharedResource1 {
    private boolean isExist = false;

    int count;

    private String data;

    private Map<String, String> dataMap;

    private List<Map<String, Object>> listData;

    private String[] arrData;

    public synchronized void putData(String data) {
        // 释放锁，等待数据被使用，用while防止假唤醒
        while (isExist) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("释放锁异常");
                Thread.currentThread().interrupt();
            }
        }

        // 获取锁并继续
        this.data = data;
        count++;
        System.out.println(Thread.currentThread().getName() + " 生产: " + data+",计数："+count);
        this.isExist = true;

        notifyAll();
    }

    public synchronized String takeData() {
        //
        while(!isExist) {
            try {
                wait();
            } catch (InterruptedException e) {
                System.out.println("释放锁异常");
                Thread.currentThread().interrupt();
            }
        }

        // 消费数据
        this.isExist = false;
        count--;
        System.out.println(Thread.currentThread().getName() + " 消费: " + data+",计数："+count);
        notifyAll();
        return data;
    }
}
