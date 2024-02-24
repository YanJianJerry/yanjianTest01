package com.syntax;

/**
 * @Author: yanjian
 * @CreateDate: 2023/07/28 1:12
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */

/**
 *接口
 * 接口的作用
 * 1.接口是比抽象类还要抽象的抽象类，可以更加规范地对子类进行约束，达到：规范和具体实现的分离（接口里面只能有抽象方法和常量）
 * 2.接口和接口的实现类，并不是父子关系，而是规则实现关系
 */

/**
 * 定义接口
 * 1.访问修饰符：只能是 public 或不写（默认为public）
 * 2.接口名：和类名采用相同的命名机制
 * 3.extends：接口可以多继承
 * 4.常量：接口中的属性只能是常量，必须public static final修饰，默认也是这个
 * 5.方法：接口中的方法只能是：public abstract ,默认也会是这个
 */

import java.text.SimpleDateFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import java.util.Calendar;
import java.util.Date;

/**
 * 1.类通过 implements 来实现接口中的规范
 * 2.接口不能创建实例，但是可以用于声明引用变量类型
 * 3.一个类实现了接口，必须实现接口中的所有方法，并且这些方法只能是public
 * 4.jdk1.8（不含8）之前，接口中只能包含静态常量和抽象方法，不能有普通属性、构造方法、普通方法
 * 5.jdk1.8后，接口中可以有普通的静态方法、默认方法(可以不重写)
 *            接口中的静态方法，可以通过接口名进行调用。要注意，实现子类如果定义了同名的静态方法，通过子类名调用，这两个个完全不同的静态方法。
 */

/**
 * 接口的继承
 * 接口支持多继承，即子接口可以继承多个父接口
 * 子接口继承父接口，可以获取父接口的一切
 */
public class Interface {
    public static void main(String[] args) {
        //
        InterfaceImpl anInterface = new InterfaceImpl();

        anInterface.getDateTime();
        anInterface.getTime();
        anInterface.getDate();
        System.out.println(anInterface.getClass());
        System.out.println(InterfaceInterface.MAX_NUM);
        System.out.println(InterfaceInterface.TYPE);

        //
        InterfaceInterface.getcurrentTimeMillis();
        InterfaceImpl.getcurrentTimeMillis();

    }
}

//
interface InterfaceInterface{
    int MAX_NUM = 65535;
    public static final String TYPE = "interface";

    //抽象方法
    void getTime();
    public abstract void getDate();

    //默认方法
    default void getDateTime(){
        LocalDateTime dateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        System.out.println(dateTime.format(formatter));

        //
        InterfaceInterface.getcurrentTimeMillis();
    }

    static void getcurrentTimeMillis(){
        System.out.println(System.currentTimeMillis());
    }
}

class InterfaceImpl implements InterfaceInterface{

    @Override
    public void getTime() {
        //
        LocalTime time = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println(time.format(formatter));

        //
    }

    @Override
    public void getDate() {
        LocalDate date = LocalDate.now(); // get the current date
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        System.out.println(date.format(formatter));
    }

    public static void getcurrentTimeMillis(){
        //
        Date date = new Date();
        Calendar calendar = Calendar.getInstance(); // get current instance of the calendar

        //日期格式化
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        //输出
        System.out.println(System.currentTimeMillis());
        System.out.println(formatter.format(date));
        System.out.println(formatter.format(calendar.getTime()));
    }
}

//
interface Study {
    int MAX_SCORE = 100;
    void learnMath();
    void learnMath(int grade,String type);
}

interface Drive{
    int MAX_AGE = 80;
    void drivingCar();
}

interface Driver extends Study,Drive{
    void rest();
}

class Driver1 implements Study,Drive{

    @Override
    public void learnMath() {

    }

    @Override
    public void learnMath(int grade, String type) {
        System.out.println();
    }

    @Override
    public void drivingCar() {
        System.out.println("开跑车");
    }
}

//
class Driver2 implements Driver{

    @Override
    public void learnMath() {

    }

    @Override
    public void learnMath(int grade, String type) {

    }

    @Override
    public void drivingCar() {

    }

    @Override
    public void rest() {

    }
}