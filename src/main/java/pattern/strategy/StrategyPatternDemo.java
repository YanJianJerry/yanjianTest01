package pattern.strategy;

// ==========================================
// 1. 策略接口：定义支付行为
// ==========================================
interface PaymentStrategy {
    /**
     * 执行支付操作
     * @param amount 支付金额（单位：元）
     * @return 是否支付成功
     */
    boolean pay(double amount);
}

// ==========================================
// 2. 具体策略类：支付宝支付
// ==========================================
class AlipayStrategy implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("正在使用【支付宝】支付 ¥" + amount + "...");
        // 模拟支付逻辑（实际可能调用第三方 API）
        boolean success = Math.random() > 0.1; // 90% 成功率
        if (success) {
            System.out.println("✅ 支付宝支付成功！");
        } else {
            System.out.println("❌ 支付宝支付失败，请重试。");
        }
        return success;
    }
}

// ==========================================
// 3. 具体策略类：微信支付
// ==========================================
class WechatPayStrategy implements PaymentStrategy {
    @Override
    public boolean pay(double amount) {
        System.out.println("正在使用【微信支付】支付 ¥" + amount + "...");
        boolean success = Math.random() > 0.2; // 80% 成功率
        if (success) {
            System.out.println("✅ 微信支付成功！");
        } else {
            System.out.println("❌ 微信支付失败，请重试。");
        }
        return success;
    }
}

// ==========================================
// 4. 具体策略类：信用卡支付
// ==========================================
class CreditCardStrategy implements PaymentStrategy {
    private final String cardNumber;

    public CreditCardStrategy(String cardNumber) {
        // 简单校验卡号（实际应更严格）
        if (cardNumber == null || cardNumber.length() < 12) {
            throw new IllegalArgumentException("无效的信用卡号");
        }
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean pay(double amount) {
        System.out.println("正在使用【信用卡】（尾号 " + cardNumber.substring(cardNumber.length() - 4) + "）支付 ¥" + amount + "...");
        boolean success = Math.random() > 0.15; // 85% 成功率
        if (success) {
            System.out.println("✅ 信用卡支付成功！");
        } else {
            System.out.println("❌ 信用卡支付失败，余额不足或网络异常。");
        }
        return success;
    }
}

// ==========================================
// 5. 上下文类：购物车 / 支付服务
// ==========================================
class PaymentContext {
    private PaymentStrategy strategy;

    /**
     * 设置支付策略
     */
    public void setPaymentStrategy(PaymentStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * 执行支付
     * @param amount 支付金额
     * @return 是否成功
     */
    public boolean executePayment(double amount) {
        if (strategy == null) {
            throw new IllegalStateException("未设置支付策略！");
        }
        return strategy.pay(amount);
    }
}

// ==========================================
// 6. 演示类
// ==========================================
public class StrategyPatternDemo {
    public static void main(String[] args) {
        double orderAmount = 99.99;

        // 创建上下文
        PaymentContext paymentContext = new PaymentContext();

        // 使用支付宝支付
        System.out.println("=== 尝试支付宝支付 ===");
        paymentContext.setPaymentStrategy(new AlipayStrategy());
        paymentContext.executePayment(orderAmount);

        System.out.println("\n=== 尝试微信支付 ===");
        paymentContext.setPaymentStrategy(new WechatPayStrategy());
        paymentContext.executePayment(orderAmount);

        System.out.println("\n=== 尝试信用卡支付 ===");
        paymentContext.setPaymentStrategy(new CreditCardStrategy("1234-5678-9012-3456"));
        paymentContext.executePayment(orderAmount);
    }
}