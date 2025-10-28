package com.thread.multiThread.Runnable.test01;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class MultiRequestMain {
    public static void main(String[] args) {
        try {
//            test01();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * 创建线程池
     * 创建三个线程，模拟三个请求
     * @throws ExecutionException
     * @throws InterruptedException
     */
    public static void test01() throws ExecutionException, InterruptedException {

//        ExecutorService ser = Executors.newFixedThreadPool(3);
//            for (int i = 0; i < 10; i++) {
//                MultiRequest1 multiRequest1 = new MultiRequest1("1号",i);
//                Future<Integer> r = ser.submit(multiRequest1);
//                System.out.println(r.get());
//            }

        MultiRequest1 multiRequest1 = new MultiRequest1("1号",1);
        MultiRequest1 multiRequest2 = new MultiRequest1("2号",1);
        MultiRequest1 multiRequest3 = new MultiRequest1("3号",1);

        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Integer> r1 = ser.submit(multiRequest1);
        Future<Integer> r2 = ser.submit(multiRequest2);
        Future<Integer> r3 = ser.submit(multiRequest3);

        // 等待线程全部处理完成
        while (!r1.isDone() || !r2.isDone() || !r3.isDone()) {}

        //获取结果
        Integer rs1 = r1.get();
        System.out.println(rs1);
        Integer rs2 = r2.get();
        System.out.println(rs2);
        Integer rs3 = r3.get();
        System.out.println(rs3);

        //关闭服务
        ser.shutdown();
    }
}
