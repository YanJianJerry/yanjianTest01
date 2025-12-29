package pattern.prototype;

public class PrototypePatternDemo {
    public static void main(String[] args) {
        // 创建原型对象（昂贵操作，只做一次）
        Weapon axe = new Weapon("战斧", 50);
        Weapon bow = new Weapon("精灵弓", 40);

        Orc orcPrototype = new Orc("兽人战士", 100, 30, axe);
        Elf elfPrototype = new Elf("森林精灵", 80, 35, bow);

        // 注册到原型管理器
        MonsterRegistry registry = new MonsterRegistry();
        registry.register("orc", orcPrototype);
        registry.register("elf", elfPrototype);

        // 快速克隆多个实例
        System.out.println("=== 生成怪物军团 ===");
        Monster orc1 = registry.create("orc");
        Monster orc2 = registry.create("orc");
        Monster elf1 = registry.create("elf");

        orc1.showInfo();
        orc2.showInfo();
        elf1.showInfo();

        // 验证是否为深拷贝（修改一个不影响另一个）
        System.out.println("\n=== 验证深拷贝 ===");
        orc1.getWeapon().setName("🔥火焰战斧"); // 修改克隆体的武器
        orc1.showInfo();
        orc2.showInfo(); // 武器名称应不变！
    }
}