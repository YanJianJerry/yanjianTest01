package yan.oop;

public class TestExtends {
    public static void main(String[] args){
        /* 父类不能访问子类属性和方法*/
        PersonOop PersonOop = new PersonOop("2018001","鄢健");
        PersonOop.setHumanity("睿智");
        PersonOop.setWeight(156);
        PersonOop.setHeight(179);
        PersonOop.setAge(20);
        PersonOop.assets = 100.0;
        System.out.println(PersonOop);
        System.out.println();

        /* 子类可以访问父类的公有属性和公有方法*/
        //
        StudentOop StudentOop = new StudentOop();
        StudentOop.setAge(20);
        StudentOop.setGPA(4);
        StudentOop.setGrade("junior");
        StudentOop.assets = 200.0;
        StudentOop.setHumanity("呆子");//可以通过 get set 访问父类的私有属性
        System.out.println(StudentOop);
        System.out.println(StudentOop.getHumanity());
        System.out.println(StudentOop.grade);
        //System.out.println(StudentOop.GPA); 不能直接访问私有属性
        System.out.println();

        /* */
        /* 编译时类型*/      /* 运行时类型*/
        //自动转型 向上转型
        PersonOop PersonOopStu = new StudentOop("1002","2018002");
        System.out.println(PersonOopStu);
        PersonOopStu.setAge(200);
        PersonOopStu.setHeight(200);
        PersonOopStu.setHumanity("邪恶");
        PersonOopStu.setName("路西法");
        PersonOopStu.setWeight(200);

        //PersonOopStu.setGrade("4"); 用父类声明，无法调用子类方法（编译会报错，编译器不认）
        System.out.println(PersonOopStu);
        // System.out.println(PersonOopStu.grade); 有子类的属性和方法，但是无法直接访问

        //强制转型  向下转型 先判断类型再强转，以防运行时报错
        if(PersonOopStu instanceof StudentOop){
            StudentOop s = (StudentOop)PersonOopStu;
            s.setGrade("4");
            System.out.println(s);
            System.out.println("强制转型对象是否不变 "+s.equals(PersonOopStu));
            System.out.println();
        }



        //StudentOop stuPersonOop = new PersonOop(); 子类不可通过父类new


        //test equals
        StudentOop StudentOop1 = new StudentOop("001","001");
        StudentOop StudentOop2 = new StudentOop("002","001");
        System.out.println(StudentOop2.equalsStudent(StudentOop1));
        System.out.println(StudentOop2.equals(StudentOop1));
    }
}
