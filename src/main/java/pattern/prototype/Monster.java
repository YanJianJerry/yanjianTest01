package pattern.prototype;

// 抽象原型：实现 Cloneable
abstract class Monster implements Cloneable {
    protected String name;
    protected int health;
    protected int attack;
    protected Weapon weapon; // 引用类型 → 需深拷贝

    public Monster(String name, int health, int attack, Weapon weapon) {
        this.name = name;
        this.health = health;
        this.attack = attack;
        this.weapon = weapon;
    }

    // 原型模式核心：克隆方法（深拷贝）
    @Override
    public Monster clone() {
        try {
            Monster cloned = (Monster) super.clone();
            // 深拷贝引用类型字段
            cloned.weapon = this.weapon.clone();
            return cloned;
        } catch (ClassCastException e) {
            throw new AssertionError("Clone failed", e);
        } catch (CloneNotSupportedException e) {
            throw new RuntimeException(e);
        }
    }

    // 通用行为
    public void showInfo() {
        System.out.println("怪物: " + name + 
                          ", 生命: " + health + 
                          ", 攻击: " + attack + 
                          ", 武器: " + weapon);
    }

    // Getter
    public Weapon getWeapon() { return weapon; }
}