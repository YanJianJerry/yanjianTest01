package pattern.prototype;

class Orc extends Monster {
    public Orc(String name, int health, int attack, Weapon weapon) {
        super(name, health, attack, weapon);
    }

    @Override
    public Orc clone() {
        return (Orc) super.clone();
    }
}

class Elf extends Monster {
    public Elf(String name, int health, int attack, Weapon weapon) {
        super(name, health, attack, weapon);
    }

    @Override
    public Elf clone() {
        return (Elf) super.clone();
    }
}