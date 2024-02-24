package com.thread;

/**
 * @author yyll
 * 创建线程方式一：继承Thread类，重写run()方法，调用start开启线程
 *
 * 注意：线程开启并不一定立即执行，由CPU调度执行
 *
 * 不推荐使用，避免面向对象单继承的局限性
 */
public class ThreadTest01 extends Thread{


    @Override
    public void run(){
        //run方法线程体
        for (int i = 0; i < 1000; i++) {
            System.out.println("子线程"+i);
        }
    }

    public static void main(String[] args) {
        //mian 线程，主线程

        //创建一个线程对象
        ThreadTest01 threadTest01 = new ThreadTest01();
        //调用start方法开启线程
        threadTest01.start();

        for (int i = 0; i < 200; i++) {
            System.out.println("主线程=================="+i);
        }
    }
}
