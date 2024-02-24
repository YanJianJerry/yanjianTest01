package com.thread;

/**
 * //创建线程方式﹔实现runnable接口,重写run方法，执行线程需要丢入runnable接口实现类.调用start方法.
 *
 * 推荐使用，避免了单继承的局限性，可以实现一个对象被多个线程使用
 */
public class ThreadTest02 implements Runnable {

    @Override
    public void run(){
        //run方法线程体
        for (int i = 0; i < 1000; i++) {
            System.out.println("子线程"+i);
        }
    }

    public static void main(String[] args) {
        //mian 线程，主线程

        //创建runnbale接口的实现类对象
        ThreadTest02 threadTest02 = new ThreadTest02();

        //以下可合并为 new Thread(testThread3).start();
        //创建线程对象,通过线程对象来开启我们的线程,代理
        Thread thread = new Thread(threadTest02);
        //调用start方法开启线程
        thread.start();

        for (int i = 0; i < 2000; i++) {
            System.out.println("主线程=================="+i);
        }
    }
}
