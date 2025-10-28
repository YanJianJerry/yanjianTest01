package com.thread.multiThread.Runnable.demo01;

public class ThreadSyncDemo {
    public static void main(String[] args) {
        // 创建共享资源对象
        SharedResource resource = new SharedResource();
        
        // 创建生产者和消费者线程
        Thread producerThread = new Thread(new Producer(resource), "Producer1");
        Thread consumerThread = new Thread(new Consumer(resource), "Consumer1");
        
        // 启动线程
        producerThread.start();
        consumerThread.start();
        
        try {
            // 等待线程执行完成
            producerThread.join();
            consumerThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        
        System.out.println("主线程执行完毕");
    }
}

// 共享资源类
class SharedResource {
    private String data;
    private boolean available = false;
    
    // 生产者放入数据的方法（同步方法）
    public synchronized void put(String data) {
        // 等待消费者取走数据
        while (available) {
            try {
                System.out.println(Thread.currentThread().getName() + " 等待...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
        
        // 生产数据
        this.data = data;
        available = true;
        System.out.println(Thread.currentThread().getName() + " 生产: " + data);
        
        // 通知消费者数据已准备好
        notifyAll();
    }
    
    // 消费者获取数据的方法（同步方法）
    public synchronized String take() {
        // 等待生产者放入数据
        while (!available) {
            try {
                System.out.println(Thread.currentThread().getName() + " 等待...");
                wait();
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return null;
            }
        }
        
        // 消费数据
        available = false;
        System.out.println(Thread.currentThread().getName() + " 消费: " + data);
        
        // 通知生产者可以继续生产
        notifyAll();
        return data;
    }
}

// 生产者类
class Producer implements Runnable {
    private final SharedResource resource;
    
    public Producer(SharedResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run() {
        String[] messages = {
            "消息1", "消息2", "消息3", "消息4", "消息5"
        };
        
        for (String message : messages) {
            resource.put(message);
            
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

// 消费者类
class Consumer implements Runnable {
    private final SharedResource resource;
    
    public Consumer(SharedResource resource) {
        this.resource = resource;
    }
    
    @Override
    public void run() {
        for (int i = 0; i < 5; i++) {
            resource.take();
            
            // 模拟消费耗时
            try {
                Thread.sleep((long) (Math.random() * 1500));
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }
        }
    }
}