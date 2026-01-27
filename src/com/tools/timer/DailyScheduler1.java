package com.tools.timer;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

public class DailyScheduler1 {
    
    private static final ScheduledExecutorService scheduler = Executors.newScheduledThreadPool(1);
    private static final ScheduledExecutorService countdownScheduler = Executors.newScheduledThreadPool(1);
    private static LocalTime executionTime = LocalTime.of(22, 0, 0);
    private static final boolean showCountdown = true; // 是否显示倒计时
    
    public static void main(String[] args) {
        // 可以通过命令行参数设置执行时间
        if (args.length >= 2) {
            executionTime = LocalTime.parse(args[0] + ":" + args[1], DateTimeFormatter.ofPattern("HH:mm"));
        }
        
        scheduleDailyTask(scheduler, executionTime);
        
        // 如果需要显示倒计时，启动倒计时显示
        if (showCountdown) {
            startCountdownDisplay(executionTime);
        }
    }
    
    private static void scheduleDailyTask(ScheduledExecutorService scheduler, 
                                        LocalTime executionTime) {
        // 计算首次执行的延迟时间
        long initialDelay = calculateInitialDelay(executionTime);
        
        System.out.println("=== 每日任务调度器启动 ===");
        System.out.println("执行时间: " + executionTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("首次执行延迟: " + formatDuration(initialDelay));
        System.out.println("首次执行时间: " + 
            LocalTime.now().plusSeconds(initialDelay).format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        System.out.println("执行窗口: ±10分钟");
        System.out.println("=========================");
        
        // 安排定时任务，每天执行一次
        scheduler.scheduleAtFixedRate(() -> {
            try {
                executeDailyTaskWithWindowCheck();
            } catch (Exception e) {
                System.err.println("任务执行失败: " + e.getMessage());
            }
        }, initialDelay, TimeUnit.DAYS.toSeconds(1), TimeUnit.SECONDS);
    }
    
    private static void executeDailyTaskWithWindowCheck() {
        LocalTime now = LocalTime.now();
        long minutesFromTarget = Math.abs(ChronoUnit.MINUTES.between(executionTime, now));
        
        if (minutesFromTarget <= 10) {
            System.out.println("\n=== 进入执行窗口，开始执行任务 ===");
            executeDailyLogic();
            System.out.println("=== 任务执行完成 ===\n");
        } else {
            System.out.println("当前时间 " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")) + 
                             " 不在执行窗口内（距离目标时间 " + minutesFromTarget + " 分钟），跳过执行");
        }
    }
    
    /**
     * 启动倒计时显示
     */
    private static void startCountdownDisplay(LocalTime executionTime) {
        countdownScheduler.scheduleAtFixedRate(() -> {
            try {
                displayCountdown(executionTime);
            } catch (Exception e) {
                System.err.println("倒计时显示异常: " + e.getMessage());
            }
        }, 0, 1, TimeUnit.SECONDS); // 每秒更新一次倒计时
    }
    
    /**
     * 显示倒计时信息
     */
    private static void displayCountdown(LocalTime executionTime) {
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        
        LocalDateTime todayTarget = LocalDateTime.of(today, executionTime);
        LocalDateTime tomorrowTarget = LocalDateTime.of(tomorrow, executionTime);
        
        LocalDateTime nextExecution;
        String dayDescription;
        
        if (now.isBefore(executionTime)) {
            // 今天执行
            nextExecution = todayTarget;
            dayDescription = "今天";
        } else {
            // 明天执行
            nextExecution = tomorrowTarget;
            dayDescription = "明天";
        }
        
        // 计算距离下次执行的秒数
        long secondsRemaining = Duration.between(LocalDateTime.of(today, now), nextExecution).getSeconds();
        
        if (secondsRemaining < 0) {
            secondsRemaining = 0;
        }
        
        // 格式化倒计时显示
        String countdownText = formatCountdown(secondsRemaining);
        String nextExecutionTimeStr = nextExecution.format(DateTimeFormatter.ofPattern("MM-dd HH:mm:ss"));
        
        // 清屏效果（可选）
        if (System.console() != null) {
            System.out.print("\033[2J\033[H"); // ANSI 清屏命令
        }
        
        System.out.println("=== 每日任务倒计时 ===");
        System.out.println("执行时间: " + executionTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("下次执行: " + dayDescription + " " + nextExecutionTimeStr);
        System.out.println("剩余时间: " + countdownText);
        System.out.println("执行窗口: " + executionTime.minusMinutes(10).format(DateTimeFormatter.ofPattern("HH:mm")) + 
                          " ~ " + executionTime.plusMinutes(10).format(DateTimeFormatter.ofPattern("HH:mm")));
        
        // 显示当前是否在窗口内
        long minutesFromTarget = Math.abs(ChronoUnit.MINUTES.between(executionTime, now));
        if (minutesFromTarget <= 10) {
            System.out.println("🟢 当前在执行窗口内 (" + minutesFromTarget + " 分钟)");
        } else {
            System.out.println("🔴 当前不在执行窗口内 (" + minutesFromTarget + " 分钟)");
        }
        
        System.out.println("当前时间: " + now.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
        System.out.println("=========================");
        System.out.println("按 Ctrl+C 退出");
    }
    
    /**
     * 格式化倒计时显示
     */
    private static String formatCountdown(long totalSeconds) {
        if (totalSeconds <= 0) {
            return "00:00:00 (即将执行)";
        }
        
        long hours = totalSeconds / 3600;
        long minutes = (totalSeconds % 3600) / 60;
        long seconds = totalSeconds % 60;
        
        return String.format("%02d:%02d:%02d", hours, minutes, seconds);
    }
    
    /**
     * 格式化持续时间显示
     */
    private static String formatDuration(long seconds) {
        if (seconds <= 0) {
            return "0秒";
        }
        
        long hours = seconds / 3600;
        long minutes = (seconds % 3600) / 60;
        long secs = seconds % 60;
        
        StringBuilder sb = new StringBuilder();
        if (hours > 0) {
            sb.append(hours).append("小时");
        }
        if (minutes > 0) {
            sb.append(minutes).append("分钟");
        }
        if (secs > 0 || (hours == 0 && minutes == 0)) {
            sb.append(secs).append("秒");
        }
        
        return sb.toString();
    }
    
    private static long calculateInitialDelay(LocalTime executionTime) {
        LocalTime now = LocalTime.now();
        LocalDate today = LocalDate.now();
        LocalDate tomorrow = today.plusDays(1);
        
        LocalDateTime todayExecution = LocalDateTime.of(today, executionTime);
        LocalDateTime tomorrowExecution = LocalDateTime.of(tomorrow, executionTime);
        
        long initialDelay;
        
        if (now.isBefore(executionTime)) {
            // 如果当前时间还没到执行时间，就在今天执行
            initialDelay = Duration.between(now, executionTime).getSeconds();
        } else {
            // 如果已经过了执行时间，就在明天执行
            initialDelay = Duration.between(
                LocalDateTime.of(today, now), 
                tomorrowExecution
            ).getSeconds();
        }
        
        return Math.max(initialDelay, 0);
    }
    
    private static void executeDailyLogic() {
        System.out.println("执行每日任务 - " + 
            DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss").format(java.time.LocalDateTime.now()));
        
        // 模拟业务逻辑执行时间
        try {
            Thread.sleep(2000); // 模拟2秒执行时间
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        
        // 这里写你的业务逻辑
        // 例如：数据备份、报表生成、清理临时文件等
        System.out.println("✅ 业务逻辑执行完成");
        
        // 显示下次执行时间
        long nextExecutionDelay = TimeUnit.DAYS.toSeconds(1);
        System.out.println("下次执行将在: " + formatDuration(nextExecutionDelay) + " 后");
    }
    
    /**
     * 优雅关闭
     */
    public static void shutdown() {
        System.out.println("\n正在关闭调度器...");
        scheduler.shutdown();
        countdownScheduler.shutdown();
        try {
            if (!scheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                scheduler.shutdownNow();
            }
            if (!countdownScheduler.awaitTermination(5, TimeUnit.SECONDS)) {
                countdownScheduler.shutdownNow();
            }
        } catch (InterruptedException e) {
            scheduler.shutdownNow();
            countdownScheduler.shutdownNow();
            Thread.currentThread().interrupt();
        }
        System.out.println("调度器已关闭");
    }
}