package com.tools.timer;

import com.fasterxml.jackson.databind.ObjectMapper;
import okhttp3.*;

import java.io.IOException;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailyScheduler {

    private static final MediaType JSON = MediaType.get("application/json; charset=utf-8");
    private static final OkHttpClient client = new OkHttpClient();
    
    public static void main(String[] args) {
        ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
        
        // 设置每天执行的时间点（例如：每天 10:30:00）
        LocalTime executionTime = LocalTime.of(11, 0, 0);
        
        scheduleDailyTask(scheduler, executionTime);
    }
    
    private static void scheduleDailyTask(ScheduledExecutorService scheduler, 
                                        LocalTime executionTime) {
        // 计算首次执行的延迟时间
        long initialDelay = calculateInitialDelay(executionTime);
        
        System.out.println("首次执行延迟: " + initialDelay + " 秒");
        System.out.println("将在 " + 
            LocalTime.now().plusSeconds(initialDelay).format(DateTimeFormatter.ofPattern("HH:mm:ss")) + 
            " 开始执行");
        
        // 安排定时任务，每天执行一次
        scheduler.scheduleAtFixedRate(() -> {
            try {
                // 在执行时检查是否在约定时间的±10分钟内
                LocalTime now = LocalTime.now();
                long minutesFromTarget = Math.abs(ChronoUnit.MINUTES.between(executionTime, now));
                
                if (minutesFromTarget <= 10) {
                    executeDailyLogic();
                } else {
                    System.out.println("当前时间 " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + 
                                     " 不在执行窗口内（距离目标时间 " + minutesFromTarget + " 分钟），跳过执行");
                }
            } catch (Exception e) {
                System.err.println("任务执行失败: " + e.getMessage());
            }
        }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
    
    private static long calculateInitialDelay(LocalTime executionTime) {
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        
        // 将LocalTime转换为当天的LocalDateTime来计算持续时间
        LocalDateTime todayExecution = LocalDateTime.of(today, executionTime);
        LocalDateTime tomorrowExecution = LocalDateTime.of(tomorrow, executionTime);
        
        long initialDelay;
        
        if (now.isBefore(executionTime)) {
            // 如果当前时间还没到执行时间，就在今天执行
            initialDelay = Duration.between(now, executionTime).getSeconds();
        } else {
            // 如果已经过了执行时间，就在明天执行
            // 不能直接用executionTime.plusHours(24)，因为LocalTime会保持不变
            initialDelay = Duration.between(now, tomorrowExecution.toLocalTime()).getSeconds();
            // 更准确的方式：计算到明天同一时间点的秒数
            initialDelay = Duration.between(
                LocalDateTime.of(today, now), 
                tomorrowExecution
            ).getSeconds();
        }
        
        // 确保延迟不为负数
        return Math.max(initialDelay, 0);
    }
    
    private static void executeDailyLogic() {
        System.out.println("执行每日任务 - " + 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(java.time.LocalDateTime.now()));

        try {
            ObjectMapper mapper = new ObjectMapper();
            // 构造 JSON 请求体
            Map<String, Object> jsonMap = new HashMap<>();
            HashMap<String, Object> textMap = new HashMap<>();
            ArrayList<String> mentioned_mobile_list = new ArrayList<>();
            mentioned_mobile_list.add("@all");
            textMap.put("mentioned_mobile_list", mentioned_mobile_list);
            textMap.put("content", "");
            jsonMap.put("text", textMap);
            jsonMap.put("msgtype", "text");

            String json = mapper.writeValueAsString(jsonMap);

            RequestBody body = RequestBody.create(json, JSON);

            // 构建请求
            Request request = new Request.Builder()
                    .url("") // 替换为你自己的 API 地址
                    .post(body)
                    .build();

            // 发送请求并获取响应
            try (Response response = client.newCall(request).execute()) {
                if (!response.isSuccessful()) {
                    throw new IOException("Unexpected code " + response);
                }
                assert response.body() != null;
                System.out.println(response.body().string());
            }
        } catch (IOException e){
            e.printStackTrace();
        }

        System.out.println("业务逻辑执行完成");
    }

    private static long calculateInitialDelay1(LocalTime executionTime) {
        LocalTime now = LocalTime.now();
        long initialDelay;

        if (now.isBefore(executionTime)) {
            // 今天执行
            initialDelay = Duration.between(now, executionTime).getSeconds();
        } else {
            // 明天执行：计算到明天同一时间点的秒数
            // 当天剩余秒数 + 明天到执行时间的秒数
            long secondsUntilMidnight = Duration.between(now, LocalTime.MAX).getSeconds() + 1;
            long secondsFromMidnightToExecution = Duration.between(LocalTime.MIN, executionTime).getSeconds();
            initialDelay = secondsUntilMidnight + secondsFromMidnightToExecution;
        }

        return Math.max(initialDelay, 0);
    }

    private static long calculateInitialDelay2(LocalTime executionTime) {
        ZonedDateTime now = ZonedDateTime.now();
        LocalDate today = now.toLocalDate();
        LocalDate tomorrow = today.plusDays(1);

        ZonedDateTime todayTarget = ZonedDateTime.of(today, executionTime, now.getZone());
        ZonedDateTime tomorrowTarget = ZonedDateTime.of(tomorrow, executionTime, now.getZone());

        ZonedDateTime nextExecution = now.isBefore(todayTarget) ? todayTarget : tomorrowTarget;

        return Duration.between(now, nextExecution).getSeconds();
    }
}