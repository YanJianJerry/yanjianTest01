package pattern.observer.demo1;

import java.util.Observable;
import java.util.Observer;

// 1. 定义被观察者：订单（继承 Observable 类）
class OrderObservable extends Observable {
    private String orderId;
    private String status; // 订单状态：待支付、已支付、已发货等

    // 订单状态更新方法（核心：状态变更后通知观察者）
    public void updateStatus(String newStatus) {
        this.status = newStatus;
        // 标记状态已变更（必须调用，否则 notifyObservers 不会生效）
        setChanged();
        // 通知所有注册的观察者，可携带自定义数据（这里传订单对象本身）
        notifyObservers(this);
    }

    // getter 方法（供观察者获取订单信息）
    public String getOrderId() {
        return orderId;
    }

    public String getStatus() {
        return status;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }
}

// 2. 定义观察者1：短信通知（实现 Observer 接口）
class SmsObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        // 类型转换，获取被观察者的状态
        OrderObservable order = (OrderObservable) arg;
        System.out.println("【短信通知】订单 " + order.getOrderId() 
                + " 状态已更新为：" + order.getStatus());
    }
}

// 3. 定义观察者2：App 内消息通知
class AppMsgObserver implements Observer {
    @Override
    public void update(Observable o, Object arg) {
        OrderObservable order = (OrderObservable) arg;
        System.out.println("【App消息】订单 " + order.getOrderId() 
                + " 状态已更新为：" + order.getStatus());
    }
}

// 测试代码
public class ObserverPatternWithJdk {
    public static void main(String[] args) {
        // 创建被观察者（订单）
        OrderObservable order = new OrderObservable();
        order.setOrderId("O20260117001");

        // 注册观察者（绑定通知渠道）
        order.addObserver(new SmsObserver());
        order.addObserver(new AppMsgObserver());

        // 模拟订单状态变更，自动触发所有观察者的通知
        System.out.println("=== 订单状态更新为「已支付」 ===");
        order.updateStatus("已支付");

        System.out.println("\n=== 订单状态更新为「已发货」 ===");
        order.updateStatus("已发货");
    }
}