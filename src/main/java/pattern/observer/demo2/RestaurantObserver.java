package pattern.observer.demo2;

public class RestaurantObserver implements Observer2{
    /**
     * 通知时动作
     */
    @Override
    public void update(ObservedSubject2 observedSubject) {
        System.out.println("RestaurantObserver: 订单已更新！");
    }
}
