package com.yanjian.ai.doubao.demo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

public class MutiThreadChatDemo1 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        String[] messages = {
                "请新增一个产品输入框",
                "请新增一个确认按钮",
                "请新增一个产品列表"
        };

        ExecutorService executor = Executors.newFixedThreadPool(Math.min(messages.length, 5));

        try {
            List<Future<String>> futures = new ArrayList<>();
            for (String message : messages) {
//                Callable<String> task = new DoubaoChatThread1(message);
                Future<String> future = executor.submit(new DoubaoChatThread1(message));
                futures.add(future);
            }

            // 用于保存所有请求返回的结果
            StringBuilder combinedResult = new StringBuilder();

            // 获取每个任务的结果（会阻塞直到任务完成）
            for (Future<String> future : futures) {
                try {
                    String result = future.get(); // 阻塞直到该任务完成
                    combinedResult.append(result).append("\n"); // 拼接结果
                } catch (ExecutionException e) {
                    System.err.println("任务执行出错: " + e.getCause().getMessage());
                } catch (InterruptedException e) {
                    System.err.println("任务被中断: " + e.getMessage());
                    Thread.currentThread().interrupt(); // 恢复中断状态
                }
            }

            // 所有线程都已完成，进行统一的后续处理
            System.out.println("===== 所有请求结果拼接如下 =====");
            System.out.println(combinedResult.toString());

        } finally {
        // 关闭线程池
        executor.shutdown();
        try {
            if (!executor.awaitTermination(60, TimeUnit.SECONDS)) {
                executor.shutdownNow();
            }
        } catch (InterruptedException e) {
            executor.shutdownNow();
            Thread.currentThread().interrupt();
        }
    }
    }

}
