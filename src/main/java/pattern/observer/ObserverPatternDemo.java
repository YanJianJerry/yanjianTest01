package pattern.observer;

import java.util.ArrayList;
import java.util.List;

// ==========================================
// 观察者接口：定义更新行为
// ==========================================
interface Observer {
    /**
     * 当被观察者状态发生变化时，此方法被调用
     * @param news 最新的新闻内容
     */
    void update(String news);
}

// ==========================================
// 被观察者（主题）接口：管理观察者并通知他们
// ==========================================
interface Subject {
    /**
     * 注册一个观察者
     */
    void registerObserver(Observer observer);

    /**
     * 移除一个观察者
     */
    void removeObserver(Observer observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();
}

// ==========================================
// 具体的主题类：新闻发布机构
// ==========================================
class NewsAgency implements Subject {
    private List<Observer> observers; // 观察者列表
    private String latestNews;        // 最新新闻内容

    public NewsAgency() {
        observers = new ArrayList<>();
    }

    /**
     * 注册观察者
     */
    @Override
    public void registerObserver(Observer observer) {
        observers.add(observer);
        System.out.println(observer + " 已订阅新闻。");
    }

    /**
     * 移除观察者
     */
    @Override
    public void removeObserver(Observer observer) {
        observers.remove(observer);
        System.out.println(observer + " 已取消订阅。");
    }

    /**
     * 通知所有观察者
     */
    @Override
    public void notifyObservers() {
        for (Observer observer : observers) {
            observer.update(latestNews);
        }
    }

    /**
     * 设置最新新闻并通知所有订阅者
     * @param news 新的新闻内容
     */
    public void setNews(String news) {
        this.latestNews = news;
        System.out.println("【新闻发布】" + news);
        notifyObservers(); // 触发通知
    }
}

// ==========================================
// 具体的观察者类：新闻频道
// ==========================================
class NewsChannel implements Observer {
    private String channelName;
    private String receivedNews;

    public NewsChannel(String name) {
        this.channelName = name;
    }

    /**
     * 接收来自主题的通知并更新本地状态
     */
    @Override
    public void update(String news) {
        this.receivedNews = news;
        System.out.println(channelName + " 收到新闻: " + receivedNews);
    }

    @Override
    public String toString() {
        return "频道[" + channelName + "]";
    }
}

// ==========================================
// 演示类：运行观察者模式示例
// ==========================================
public class ObserverPatternDemo {
    public static void main(String[] args) {
        // 创建新闻发布机构（主题）
        NewsAgency agency = new NewsAgency();

        // 创建多个新闻频道（观察者）
        NewsChannel channel1 = new NewsChannel("CCTV");
        NewsChannel channel2 = new NewsChannel("BBC");
        NewsChannel channel3 = new NewsChannel("CNN");

        // 订阅新闻
        agency.registerObserver(channel1);
        agency.registerObserver(channel2);

        // 发布第一条新闻
        agency.setNews("今天天气晴朗，适合出行。");

        System.out.println("------------------------");

        // CNN 临时订阅
        agency.registerObserver(channel3);
        // 发布第二条新闻
        agency.setNews("股市大涨，投资者信心增强。");

        System.out.println("------------------------");

        // BBC 取消订阅
        agency.removeObserver(channel2);
        // 发布第三条新闻
        agency.setNews("全球气候峰会即将召开。");
    }
}