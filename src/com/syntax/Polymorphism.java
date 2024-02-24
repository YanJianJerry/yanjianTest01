package com.syntax;

/**
 * 多态
 * 同一个方法的调用，不同的对象行为不同
 * 子类重写父类方法
 */
public class Polymorphism {
    public static void main(String[] args) {

        /* */
        test1();

        /* */
        Polymorphism polymorphism = new Polymorphism();
        polymorphism.test2();
    }

    //
    public static void test1(){
        //声明类型 是编译类型    new 类型 是运行时类型
        Animal animal = new Cat();//向上转型(自动) 子 -> 父  范围窄->宽
        animal.shout();
        System.out.println(animal);

        //
        if(animal instanceof Cat){
            Cat cat = (Cat)animal;//向下转型(强转)
            cat.Climb();
            System.out.println(cat.hashCode());
            System.out.println(animal.hashCode());
            System.out.println();
        }


    }

    //
    public void test2(){
        //
        Animal animal = new Dog(1,"旺财","clever");
        animal.shout();
        System.out.println(animal);

        //
        if(animal instanceof Dog){
            Dog dog =  (Dog)animal;
            dog.seeHome();
            System.out.println(dog.hashCode());
            System.out.println(animal);
        }

        //如果强转的类型和运行时不一致，编译不报错但是运行时报错
        //Cat cat = (Cat)animal;//com.syntax.Dog cannot be cast to com.syntax.Cat


    }
}

 class Animal {
    private int age;
    private String name;

     //构造器
     public Animal() {
         this.age = 0;
         this.name = "";
     }

     public Animal(int age, String name) {
         this.age = age;
         this.name = name;
     }

     //get set
     public int getAge() {
         return age;
     }

     public void setAge(int age) {
         this.age = age;
     }

     public String getName() {
         return name;
     }

     public void setName(String name) {
         this.name = name;
     }

     public void shout(){
         System.out.println("动物叫声");
     }
 }

class Cat extends Animal{
    String character="cute";

    //构造器
    public Cat() {
        super();
    }

    public Cat(int age, String name, String character) {
        super(age, name);
        this.character = character;
    }

    //
    @Override
    public String toString() {
        int age = this.getAge();
        String name = this.getName();
        return "Cat{" +
                "age='" + age + '\'' +
                "name='" + name + '\'' +
                "character='" + character + '\'' +
                '}';
    }

    //
    @Override
    public void shout() {
        System.out.println("喵喵喵");
    }

    //
    public void Climb(){
        System.out.println("上天入地");
    }
}

class Dog extends Animal{
    String character="honest";

    //构造器
    public Dog() {
    }

    public Dog(int age, String name, String character) {
        super(age, name);
        this.character = character;
    }

    //
    @Override
    public String toString() {
        int age = this.getAge();
        String name = this.getName();
        return "Dog{" +
                "age='" + age + '\'' +
                "name='" + name + '\'' +
                "character='" + character + '\'' +
                '}';
    }

    //
    @Override
    public void shout() {
        System.out.println("汪汪汪");
    }

    //
    public void seeHome(){
        System.out.println("看家");
    }
}