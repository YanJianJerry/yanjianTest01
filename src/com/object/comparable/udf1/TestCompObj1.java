package com.object.comparable.udf1;

import com.bean.NewInstance;
import com.bean.pojo.Person;

import java.util.*;
import java.util.stream.Collectors;

public class TestCompObj1 {
    public static void main(String[] args) {
        Person[] persons =(Person[]) new NewInstance().getPerson();
        Arrays.stream(persons).forEach(System.out::println);
        System.out.println(Arrays.toString(persons));

        System.out.println("根据年龄顺序");
        // 根据年龄顺序排序，匿名类
        Arrays.sort(persons, new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getAge()-o2.getAge();
            }
        });
        Arrays.stream(persons).forEach(System.out::println);

        // 根据年龄逆序排序 lambda
        Arrays.sort(persons, (o1, o2) -> o2.getAge()-o1.getAge());
        Arrays.stream(persons).forEach(System.out::println);

        Arrays.sort(persons, Comparator.comparingInt(Person::getAge).reversed());
        Arrays.sort(persons, (p1, p2) -> Integer.compare(p1.getAge(), p2.getAge()));

        System.out.println("根据名字排序");
        // 根据名字排序 lambda
        Arrays.sort(persons, (o1, o2) -> o1.getName().compareTo(o2.getName()));
        Arrays.stream(persons).forEach(System.out::println);

        // 根据名字排序 方法引用
        Arrays.sort(persons, Comparator.comparing(Person::getName));
        // 复杂排序，忽略null，忽略大小写
        Arrays.sort(persons, Comparator.comparing(Person::getName,Comparator.nullsFirst(String.CASE_INSENSITIVE_ORDER)));
        Arrays.stream(persons).forEach(System.out::println);

        // 先按年龄排序，再按名字排序
        System.out.println("先按年龄排序，再按名字排序");
        Arrays.sort(persons, Comparator.comparingInt(Person::getAge).thenComparing(Person::getName));
        Arrays.stream(persons).forEach(System.out::println);

        // 通过streamApi
        System.out.println("通过streamApi");
        Arrays.stream(persons).sorted(Comparator.comparingInt(Person::getAge).thenComparing(Person::getName)).forEach(System.out::println);
        Arrays.stream(persons).sorted(Comparator.comparing(Person::getId)).map(Person::getName).forEach(System.out::println);

        // 定义比较器
        System.out.println("定义比较器");
        Comparator<Person> comparator = Comparator.comparing(Person::getId);
        Comparator<Person> comparator1 = Comparator.comparing(Person::getName).thenComparing(Person::getId).reversed();
        List<Person> list = Arrays.asList(persons);
        LinkedList<Person> list1 = Arrays.stream( persons).collect(Collectors.toCollection(LinkedList::new));

        list.stream().sorted(comparator).map(Person::getName).forEach(System.out::println);

        list1.sort(comparator1);
        list1.forEach((a)->System.out.println(a.getId()));
    }

}

class CompObj1 {
    private String name;
    private int age;

    public CompObj1(String name, int age) {
        this.name = name;
        this.age = age;
    }

    @Override
    public String toString() {
        return "CompObj1{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }
}
