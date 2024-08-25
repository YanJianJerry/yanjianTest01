package yan.oop;

import java.util.Objects;

public class PersonOop {
    //私有属性无法被继承（子类拥有但是无法访问）
    private String humanity;
    private int MAX_AGE = 200;;

    protected double assets;

    //公有属性可被继承
    public String identityId;
    public String name;
    public int age;
    public int height;
    public int weight;


    /*
     构造函数
     */
    public PersonOop() {
        humanity = "光辉";
        identityId = "000000";
        age = 0;
        height = 0;
        weight = 0;
    }

    public PersonOop(String identityId) {
        this.identityId = identityId;
        humanity = "光辉";
        age = 0;
        height = 0;
        weight = 0;
    }

    public PersonOop(String identityId, String name){
        this.name = name;
        this.identityId = identityId;
        humanity = "光辉";
        age = 0;
        height = 0;
        weight = 0;
    }

    public PersonOop(String humanity, String identityId, String name, int age, int height, int weight) {
        this.humanity = humanity;
        this.identityId = identityId;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public PersonOop(String humanity, String identityId, String name, int age, int height, int weight, double assets) {
        this.humanity = humanity;
        this.identityId = identityId;
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
        this.assets = assets;
    }

    /*
     get and set
     */

    public String getHumanity() {
        return humanity;
    }

    public void setHumanity(String humanity) {
        this.humanity = humanity;
    }

    public String getIdentityId() {
        return identityId;
    }

    public void setIdentityId(String identityId) {
        this.identityId = identityId;
    }

    public int getAge() {
        return age;
    }

    @Override
    public String toString() {
        return "Person{" +
                "humanity='" + humanity + '\'' +
                ", MAX_AGE=" + MAX_AGE +
                ", name='" + name + '\'' +
                ", assets=" + assets +
                ", identityId='" + identityId + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", weight=" + weight +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        PersonOop personOop = (PersonOop) o;
        return Objects.equals(humanity, personOop.humanity) && Objects.equals(identityId, personOop.identityId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(humanity, identityId);
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAssets() {
        return assets;
    }

    public void setAssets(double assets) {
        this.assets = assets;
    }
}
