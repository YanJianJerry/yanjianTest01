package pattern.prototype;

// 装备类：需要支持克隆（深拷贝）
class Weapon implements Cloneable {
    private String name;
    private int damage;

    public Weapon(String name, int damage) {
        this.name = name;
        this.damage = damage;
    }

    // 自定义克隆方法（深拷贝）
    @Override
    protected Weapon clone() {
        try {
            return (Weapon) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError("Clone not supported", e);
        }
    }

    // Getter & toString
    public String getName() { return name; }
    public int getDamage() { return damage; }
    @Override
    public String toString() {
        return "Weapon{name='" + name + "', damage=" + damage + "}";
    }

    public void setName(String name) {
        this.name = name;
    }
}