package pattern.abstractfactory;

// ==========================================
// 1. 抽象产品 A：按钮接口
// ==========================================
interface Button {
    void paint(); // 渲染按钮
    void onClick(); // 按钮点击行为
}

// ==========================================
// 2. 抽象产品 B：复选框接口
// ==========================================
interface Checkbox {
    void paint(); // 渲染复选框
    boolean isChecked(); // 获取选中状态
}

// ==========================================
// 3. 具体产品：Windows 风格按钮
// ==========================================
class WindowsButton implements Button {
    @Override
    public void paint() {
        System.out.println("绘制 [Windows 风格按钮]");
    }

    @Override
    public void onClick() {
        System.out.println("执行 Windows 按钮点击逻辑");
    }
}

// ==========================================
// 4. 具体产品：Windows 风格复选框
// ==========================================
class WindowsCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void paint() {
        System.out.println("绘制 [Windows 风格复选框] (状态: " + (checked ? "✓" : "□") + ")");
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    public void toggle() {
        checked = !checked;
    }
}

// ==========================================
// 5. 具体产品：Mac 风格按钮
// ==========================================
class MacButton implements Button {
    @Override
    public void paint() {
        System.out.println("绘制 [Mac 风格按钮]");
    }

    @Override
    public void onClick() {
        System.out.println("执行 Mac 按钮点击逻辑");
    }
}

// ==========================================
// 6. 具体产品：Mac 风格复选框
// ==========================================
class MacCheckbox implements Checkbox {
    private boolean checked = false;

    @Override
    public void paint() {
        System.out.println("绘制 [Mac 风格复选框] (状态: " + (checked ? "✓" : "□") + ")");
    }

    @Override
    public boolean isChecked() {
        return checked;
    }

    public void toggle() {
        checked = !checked;
    }
}

// ==========================================
// 7. 抽象工厂：声明创建所有 UI 组件的方法
// ==========================================
interface GUIFactory {
    /**
     * 创建按钮
     */
    Button createButton();

    /**
     * 创建复选框
     */
    Checkbox createCheckbox();
}

// ==========================================
// 8. 具体工厂：Windows 风格工厂
// ==========================================
class WindowsFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new WindowsButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new WindowsCheckbox();
    }
}

// ==========================================
// 9. 具体工厂：Mac 风格工厂
// ==========================================
class MacFactory implements GUIFactory {
    @Override
    public Button createButton() {
        return new MacButton();
    }

    @Override
    public Checkbox createCheckbox() {
        return new MacCheckbox();
    }
}

// ==========================================
// 10. 客户端代码：使用抽象工厂创建一致风格的 UI
// ==========================================
class Application {
    private Button button;
    private Checkbox checkbox;

    // 通过抽象工厂注入，完全解耦具体实现
    public Application(GUIFactory factory) {
        this.button = factory.createButton();
        this.checkbox = factory.createCheckbox();
    }

    public void paint() {
        button.paint();
        checkbox.paint();
    }

    public void interact() {
        button.onClick();
        ((WindowsCheckbox) checkbox).toggle(); // 仅演示，实际应避免强转
        checkbox.paint();
    }
}

// ==========================================
// 11. 演示类
// ==========================================
public class AbstractFactoryPatternDemo {
    private static GUIFactory getFactory() {
        String os = System.getProperty("os.name").toLowerCase();
        if (os.contains("win")) {
            return new WindowsFactory();
        } else if (os.contains("mac")) {
            return new MacFactory();
        } else {
            System.out.println("未知系统，使用默认 Windows 风格");
            return new WindowsFactory();
        }
    }

    public static void main(String[] args) {
        System.out.println("当前操作系统: " + System.getProperty("os.name"));
        
        // 根据系统选择具体工厂
        GUIFactory factory = getFactory();
        
        // 创建应用（所有组件风格一致）
        Application app = new Application(factory);
        
        // 渲染 UI
        app.paint();
        System.out.println();
        
        // 用户交互
        app.interact();
    }
}