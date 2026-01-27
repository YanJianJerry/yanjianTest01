package pattern.observer.demo2;

public class CustomerObserver implements Observer2{
    /**
     * 通知时动作
     */
    @Override
    public void update(ObservedSubject2 observedSubject) {
        System.out.println("CustomerObserver: 订单已更新！");
    }
}
