package com.syntax;

/**
 * @Author: yanjian
 * @CreateDate: 2023/07/22 15:15
 * @Description: 抽象
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */

/**
 * 抽象
 * 抽象方法：
 * 1.使用abstract修饰的方法，没有方法体，是有声明
 * 2.定义的是一种规范，告诉子类必须给抽象方法提供具体实现
 *
 * 抽象类：
 * 1.包含抽象方法的类就是抽象类
 * 2.通过抽象类，可以严格限制子类的设计，（使子类间更加通用）
 *
 * 要点
 * 1.有抽象方法的类只能定义成抽象类
 * 2.抽象类不能实例化，即不能用new来给抽象类创建对象
 * 3.抽象类可以包含 属性、普通方法、抽象方法，但是构造方法不能用new来实例，只能被用来被子类调用
 * 4.抽象类只能用来被继承
 * 5.抽象方法必须被子类实现
 */
public abstract class Abstract {

    int a;
    String str;

    public abstract void method1();

    public abstract void method2();

    public abstract void method3();

}

class SubClass1 extends Abstract{

    @Override
    public void method1() {

    }

    @Override
    public void method2() {

    }

    @Override
    public void method3() {

    }
}


