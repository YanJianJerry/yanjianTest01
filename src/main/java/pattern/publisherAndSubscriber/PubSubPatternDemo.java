package pattern.publisherAndSubscriber;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

// ==========================================
// 事件基类（所有事件应继承此类或实现标记接口）
// ==========================================
abstract class Event {
    // 可以包含通用字段，如时间戳、来源等
    private final long timestamp = System.currentTimeMillis();

    public long getTimestamp() {
        return timestamp;
    }
}

// ==========================================
// 具体事件示例
// ==========================================
class NewsEvent extends Event {
    private final String content;

    public NewsEvent(String content) {
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    @Override
    public String toString() {
        return "NewsEvent{content='" + content + "'}";
    }
}

class WeatherEvent extends Event {
    private final String weather;

    public WeatherEvent(String weather) {
        this.weather = weather;
    }

    public String getWeather() {
        return weather;
    }
}

// ==========================================
// 订阅者接口：定义如何处理事件
// ==========================================
@FunctionalInterface
interface Subscriber<T extends Event> {
    void onEvent(T event);
}

// ==========================================
// 事件总线（核心：发布-订阅的中介）
// ==========================================
class EventBus {
    // 使用 ConcurrentHashMap 保证线程安全
    // Key: 事件类型（Class），Value: 该类型的所有订阅者列表
    private final Map<Class<? extends Event>, List<Subscriber<?>>> subscribers = new ConcurrentHashMap<>();

    // 可选：使用线程池异步处理事件（实现异步发布）
    private final ExecutorService executor = Executors.newCachedThreadPool();

    /**
     * 订阅某个类型的事件
     * @param eventType 感兴趣的事件类型
     * @param subscriber 处理该事件的订阅者
     * @param <T> 事件类型
     */
    public <T extends Event> void subscribe(Class<T> eventType, Subscriber<T> subscriber) {
        subscribers.computeIfAbsent(eventType, k -> new CopyOnWriteArrayList<>())
                   .add(subscriber);
        System.out.println("✅ 订阅成功: " + eventType.getSimpleName() + " -> " + subscriber);
    }

    /**
     * 取消订阅（简化版，实际中可增加唯一ID或使用弱引用避免内存泄漏）
     */
    public <T extends Event> void unsubscribe(Class<T> eventType, Subscriber<T> subscriber) {
        List<Subscriber<?>> list = subscribers.get(eventType);
        if (list != null) {
            list.remove(subscriber);
            System.out.println("❌ 取消订阅: " + eventType.getSimpleName() + " -> " + subscriber);
        }
    }

    /**
     * 发布事件（同步或异步）
     * @param event 要发布的事件
     */
    public void publish(Event event) {
        Class<? extends Event> eventType = event.getClass();
        List<Subscriber<?>> subscriberList = subscribers.get(eventType);

        if (subscriberList == null || subscriberList.isEmpty()) {
            System.out.println("📢 无人订阅事件: " + eventType.getSimpleName());
            return;
        }

        // 异步分发事件（可改为同步：直接调用 handle）
        for (Subscriber<?> subscriber : subscriberList) {
            executor.submit(() -> {
                try {
                    // 类型安全转换
                    @SuppressWarnings("unchecked")
                    Subscriber<Event> safeSubscriber = (Subscriber<Event>) subscriber;
                    safeSubscriber.onEvent(event);
                } catch (Exception e) {
                    System.err.println("⚠️ 处理事件时出错: " + e.getMessage());
                }
            });
        }
    }

    /**
     * 关闭线程池（优雅关闭）
     */
    public void shutdown() {
        executor.shutdown();
    }
}

// ==========================================
// 演示类
// ==========================================
public class PubSubPatternDemo {
    public static void main(String[] args) throws InterruptedException {
        EventBus eventBus = new EventBus();

        // 创建订阅者
        Subscriber<NewsEvent> newsReader1 = event ->
            System.out.println("📰[读者A] 收到新闻: " + event.getContent());

        Subscriber<NewsEvent> newsReader2 = event ->
            System.out.println("📰[读者B] 正在阅读: " + event.getContent());

        Subscriber<WeatherEvent> weatherApp = event ->
            System.out.println("🌦️[天气App] 当前天气: " + event.getWeather());

        // 订阅事件
        eventBus.subscribe(NewsEvent.class, newsReader1);
        eventBus.subscribe(NewsEvent.class, newsReader2);
        eventBus.subscribe(WeatherEvent.class, weatherApp);

        System.out.println("\n--- 开始发布事件 ---\n");

        // 发布新闻事件
        eventBus.publish(new NewsEvent("Java 22 正式发布！"));
        Thread.sleep(100); // 等待异步任务执行（仅用于演示）

        // 发布天气事件
        eventBus.publish(new WeatherEvent("晴，25°C"));
        Thread.sleep(100);

        // 再发一条新闻
        eventBus.publish(new NewsEvent("观察者模式 vs 发布订阅模式？"));
        Thread.sleep(100);

        // 取消一个订阅
        eventBus.unsubscribe(NewsEvent.class, newsReader1);
        eventBus.publish(new NewsEvent("最后一条新闻..."));
        Thread.sleep(100);

        // 关闭资源
        eventBus.shutdown();
    }
}