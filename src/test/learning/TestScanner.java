package test.learning;

import java.util.Scanner;

public class TestScanner {
    public static void main(String[] args) {
        //开始，从键盘接收数据
        Scanner scanner = new Scanner(System.in);
        System.out.println("输入字符串：");
        //判断用户是否输入
        if(scanner.hasNext()){
            String str1 = scanner.next();
            System.out.println("输出内容为："+str1);
        }else{
            System.out.println("哈哈哈");
        }


        System.out.println("再次输入字符串：");
        //判断用户是否输入
        if(scanner.hasNextLine()){
            String str2 = scanner.nextLine();
            System.out.println("输出内容为："+str2);
        }else{
            System.out.println("hehe");
        }
        //凡是IO流的类不关闭会一直占用资源
        scanner.close();

        /*Scanner s = new Scanner(System.in);
        int i = 0;
        float f = 0.0f;
        System.out.println("Input Integer");
        if(scanner.hasNextInt()){
            i = scanner.nextInt();
            System.out.println("Int is :"+i);
        }else{
            System.out.println("not Int");
        }

        System.out.println("Input float");
        if(scanner.hasNextFloat()){
            f = scanner.nextFloat();
            System.out.println("Float is:"+f);
        }else{
            System.out.println("not float");
        }
        s.close();*/

    }
}
