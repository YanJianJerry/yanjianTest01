package com.thread.multiThread.completableFuture;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class CompletableFutureRequestExample {

    // 模拟一个带参数的请求任务，返回 String
    public static class RequestTask {
        private final String param;

        public RequestTask(String param) {
            this.param = param;
        }

        // 模拟请求处理，返回结果
        public String call() throws Exception {
            // 模拟网络请求或耗时操作
            Thread.sleep(1000); 
            return "Response for param: " + param;
        }
    }

    public static void main(String[] args) {
        // 模拟一组请求参数，比如 ["A", "B", "C", "D", "E"]
        List<String> params = Arrays.asList("A", "B", "C", "D", "E");

        // 创建一个固定大小的线程池（根据需求调整线程数）
        ExecutorService executor = Executors.newFixedThreadPool(Math.min(params.size(), 10));

        // 构建一组 CompletableFuture<String>
        List<CompletableFuture<String>> futures = params.stream()
                .map(param -> CompletableFuture.supplyAsync(() -> {
                    try {
                        RequestTask task = new RequestTask(param);
                        return task.call(); // 执行任务并返回结果
                    } catch (Exception e) {
                        e.printStackTrace();
                        return "Error for param: " + param + " - " + e.getMessage();
                    }
                }, executor))
                .collect(Collectors.toList());

        // 使用 CompletableFuture.allOf 等待所有任务完成
        CompletableFuture<Void> allFutures = CompletableFuture.allOf(
                futures.toArray(new CompletableFuture[0])
        );

        // 当所有任务完成时，收集它们的结果
        CompletableFuture<List<String>> allResultsFuture = allFutures.thenApply(v ->
                futures.stream()
                        .map(CompletableFuture::join) // 不会阻塞，因为 allOf 已确保完成
                        .collect(Collectors.toList())
        );

        // 主线程继续，当所有结果都 ready 后，处理最终结果
        allResultsFuture.whenComplete((results, throwable) -> {
            if (throwable != null) {
                System.err.println("发生异常: " + throwable.getMessage());
            } else {
                // 拼接所有结果
                StringBuilder combinedResult = new StringBuilder();
                for (String result : results) {
                    combinedResult.append(result).append("\n");
                }

                // 打印拼接后的结果（或者做其他统一处理）
                System.out.println("===== 所有请求结果拼接如下 =====");
                System.out.println(combinedResult.toString());

                // 你可以在这里调用统一处理方法，比如：
                // processUnifiedResponse(combinedResult.toString());
            }

            // 关闭线程池
            executor.shutdown();
            System.out.println("所有任务已完成，线程池已关闭。");
        });

        // 注意：main 线程不会阻塞，程序会继续执行并结束。
        // 如果你希望 main 线程等待所有任务完成后再退出，可以加上下面的代码：
        try {
            // 等待所有任务完成（可选，仅用于演示，实际可能不需要）
            Thread.sleep(6000); // 等待足够时间让异步任务完成
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}