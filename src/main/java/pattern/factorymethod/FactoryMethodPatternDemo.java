package pattern.factorymethod;

// ==========================================
// 1. 产品接口：按钮
// ==========================================
interface Button {
    /**
     * 渲染按钮（模拟点击或显示）
     */
    void render();
    void onClick();
}

// ==========================================
// 2. 具体产品类：Windows 按钮
// ==========================================
class WindowsButton implements Button {
    @Override
    public void render() {
        System.out.println("渲染 [Windows 风格按钮]");
    }

    @Override
    public void onClick() {
        System.out.println("执行 Windows 按钮点击逻辑");
    }
}

// ==========================================
// 3. 具体产品类：Mac 按钮
// ==========================================
class MacButton implements Button {
    @Override
    public void render() {
        System.out.println("渲染 [Mac 风格按钮]");
    }

    @Override
    public void onClick() {
        System.out.println("执行 Mac 按钮点击逻辑");
    }
}

// ==========================================
// 4. 创建者抽象类：对话框（声明工厂方法）
// ==========================================
abstract class Dialog {
    /**
     * 工厂方法：由子类实现，决定创建哪种按钮
     */
    public abstract Button createButton();

    /**
     * 通用业务逻辑（不依赖具体产品类型）
     */
    public void renderDialog() {
        System.out.println("开始渲染对话框...");
        // 调用工厂方法创建产品
        Button button = createButton();
        button.render();
        button.onClick();
        System.out.println("对话框渲染完成。\n");
    }
}

// ==========================================
// 5. 具体创建者类：Windows 对话框
// ==========================================
class WindowsDialog extends Dialog {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }
}

// ==========================================
// 6. 具体创建者类：Mac 对话框
// ==========================================
class MacDialog extends Dialog {
    @Override
    public Button createButton() {
        return new MacButton();
    }
}

// ==========================================
// 7. 客户端：根据操作系统选择对话框类型
// ==========================================
public class FactoryMethodPatternDemo {
    private static Dialog configureApplication() {
        // 模拟获取操作系统类型（实际可通过 System.getProperty("os.name") 判断）
        String osName = System.getProperty("os.name").toLowerCase();
        if (osName.contains("win")) {
            return new WindowsDialog();
        } else if (osName.contains("mac")) {
            return new MacDialog();
        } else {
            // 默认使用 Windows 风格（或抛异常）
            System.out.println("⚠️ 未知操作系统，使用默认 Windows 风格");
            return new WindowsDialog();
        }
    }

    public static void main(String[] args) {
        System.out.println("当前操作系统: " + System.getProperty("os.name"));
        
        // 配置应用（选择具体工厂）
        Dialog dialog = configureApplication();
        
        // 执行业务逻辑
        dialog.renderDialog();
    }
}