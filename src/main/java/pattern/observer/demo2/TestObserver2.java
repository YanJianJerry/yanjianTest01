package pattern.observer.demo2;

public class TestObserver2 {
    public static void main(String[] args) {
        DeliveryRiderSubject2 deliveryRiderSubject = new DeliveryRiderSubject2();
        CustomerObserver customerObserver = new CustomerObserver();
        RestaurantObserver restaurantObserver = new RestaurantObserver();
        deliveryRiderSubject.registerObserver(customerObserver);
        deliveryRiderSubject.registerObserver(restaurantObserver);
        deliveryRiderSubject.showObservers();
        deliveryRiderSubject.setStatus("正在配送中");
        deliveryRiderSubject.setStatus("已送达");

    }

    public static void test1() {
    }

    public static void test2() {
    }
}
