package pattern.observer.demo2;

import java.util.ArrayList;
import java.util.List;

public class DeliveryRiderSubject2 implements ObservedSubject2{
    /**
     * 配送员名称
     */
    private String name;
    /**
     * 配送状态
     */
    private String status;
    /**
     * 观察者列表
     */
    private List<Observer2> observers = new ArrayList<>();

    /**
     * 注册观察者
     */
    @Override
    public void registerObserver(Observer2 observer) {
        observers.add(observer);
    }

    /**
     * 移除观察者
     */
    @Override
    public void removeObserver(Observer2 observer) {
        observers.remove(observer);
    }

    /**
     * 通知所有观察者
     */
    @Override
    public void notifyObservers() {
        for (Observer2 observer : observers) {
            observer.update(this);
        }
    }

    public void setStatus(String status) {
        this.status = status;
        notifyObservers();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String name, String status) {
        this.name = name;
        this.status = status;
        notifyObservers();
    }

    @Override
    public String toString() {
        return "DeliveryRiderSubject2{" +
                "name='" + name + '\'' +
                ", status='" + status + '\'' +
                '}';
    }

    /**
     * 显示已经有哪些观察者
     */
    public void showObservers(){
        System.out.println("已添加如下观察者");
        for (Observer2 observer : observers) {
            System.out.println(observer.getClass().getName());
        }
    }
}
