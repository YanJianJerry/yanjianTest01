package com.yan.oop;

public class TestExtends {
    public static void main(String[] args){
        /* 父类不能访问子类属性和方法*/
        Person person = new Person("2018001","鄢健");
        person.setHumanity("睿智");
        person.setWeight(156);
        person.setHeight(179);
        person.setAge(20);
        person.assets = 100.0;
        System.out.println(person);
        System.out.println();

        /* 子类可以访问父类的公有属性和公有方法*/
        //
        Student student = new Student();
        student.setAge(20);
        student.setGPA(4);
        student.setGrade("junior");
        student.assets = 200.0;
        student.setHumanity("呆子");//可以通过 get set 访问父类的私有属性
        System.out.println(student);
        System.out.println(student.getHumanity());
        System.out.println(student.grade);
        //System.out.println(student.GPA); 不能直接访问私有属性
        System.out.println();

        /* */
        /* 编译时类型*/      /* 运行时类型*/
        //自动转型 向上转型
        Person personStu = new Student("1002","2018002");
        System.out.println(personStu);
        personStu.setAge(200);
        personStu.setHeight(200);
        personStu.setHumanity("邪恶");
        personStu.setName("路西法");
        personStu.setWeight(200);

        //personStu.setGrade("4"); 用父类声明，无法调用子类方法（编译会报错，编译器不认）
        System.out.println(personStu);
        // System.out.println(personStu.grade); 有子类的属性和方法，但是无法直接访问

        //强制转型  向下转型 先判断类型再强转，以防运行时报错
        if(personStu instanceof Student){
            Student s = (Student)personStu;
            s.setGrade("4");
            System.out.println(s);
            System.out.println("强制转型对象是否不变 "+s.equals(personStu));
            System.out.println();
        }



        //Student stuPerson = new person(); 子类不可通过父类new


        //test equals
        Student student1 = new Student("001","001");
        Student student2 = new Student("002","001");
        System.out.println(student2.equalsStudent(student1));
        System.out.println(student2.equals(student1));
    }
}
