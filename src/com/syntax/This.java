package com.syntax;

/**
 * this 关键字的用法
 * 1.普通方法中，this总是指向调用该方法的对象
 * 2.构造方法中，this总是指向正要初始化的对象
 * 3. this() 调用重载的构造方法，减少重复初始化代码。但是只能在构造方法中用，并且需要位于构造方法的第一行
 * 4. this不能用于static方法中
 * 5. this是作为普通方法的隐藏式参数，由系统传入方法中
 */

/**
 * 创建对象的四步
 * 1. 分配对象空间，并将对象成员初始化为0或空
 * 2. 执行属性值的显示初始化
 * 3. 执行构造方法
 * 4. 返回对象的地址给相关变量
 */
public class This {
    int a,b,c;

    //无参构造器
    This(){
        System.out.println("无参构造器初始化时的this："+this);
    }

    //含参构造方法
    This(int a,int b){
        //This(); 无法这样直接调用无参构造器
        this();//调用无参构造器必须位于第一行
        a=a;//
        this.a = a;
        this.b = a+b;
    }

    This(int a,int b,int c){
        this(a,b);
        this.c = b+c;
    }

    void sing(){

    }

    void talk(){
        System.out.println("this当前类的对象："+this);
        this.sing();
        System.out.println("talk and sing");
    }

    public static void main(String[] args) {
        This ThisMan = new This(1,2,3);
        ThisMan.talk();
        System.out.println(ThisMan instanceof This);
    }
}
