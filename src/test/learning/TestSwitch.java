package Test.learning;/*
 * 测试switch的语句用法
 * 以及随机数的使用
 */

import java.util.Random;

public class TestSwitch {
    public static void main(String[] args) {
        //int grade = 1;//大学的年级
        int grade = (int) (Math.random() * 4) + 1;
        switch (grade) {
            case 1:
                System.out.println("大一不要迷茫，差距是从大一开始。");
                break;
            case 2:
                System.out.println("大二别玩游戏了，开始加油吧！");
                break;
            case 3:
                System.out.println("大三真快啊！");
                break;
            default:
                System.out.println("大四到了！");
                break;
        }

        Random rnd = new Random();
        int u_grade = rnd.nextInt(4) + 1;
        if (u_grade == 1) {
            System.out.println("大一 ");

        } else if (u_grade == 2) {
            System.out.println("大二");

        } else if (u_grade == 3) {
            System.out.println("大三");
        } else {
            System.out.println("大四");
        }

        //根据月份判断季节
        int month = (int)(Math.random()*12)+1;
        if(month<4){
            System.out.println(month+"月，是春季！");
        }else if(month<7&month>3){
            System.out.println(month+"月，是夏季！");
        }else if(month<10&month>6){
            System.out.println(month+"月，是秋季！");
        }else{
            System.out.println(month+"月，是冬季！");
        }


        switch (month){
            case 1:
                System.out.println(month+"月，是春季！");
                break;
            case 2:
                System.out.println(month+"月，是春季！");
                break;
            case 3:
                System.out.println(month+"月，是春季！");
                break;

        }

    }
}

