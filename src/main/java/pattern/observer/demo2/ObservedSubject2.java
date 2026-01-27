package pattern.observer.demo2;

/**
 * 被观察者接口
 */
public interface ObservedSubject2 {
    /**
     * 注册观察者
     */
    void registerObserver(Observer2 observer);

    /**
     * 移除观察者
     */
    void removeObserver(Observer2 observer);

    /**
     * 通知所有观察者
     */
    void notifyObservers();
}
