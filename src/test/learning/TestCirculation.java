package test.learning;

/*
        * 测试循环语句
        * while循环 和 for循环
        *
 * */
public class TestCirculation {
    public static void main(String[] args) {

        //while 循环
        int a = 0;
        while (a < 3) {//布尔表达式
            System.out.println("I love you!");//循环体
            a++;//迭代因子
        }

        int i = 0;
        int sum = 0;
        while (i <= 100) {
            sum += i;
            i++;
        }
        System.out.println("while循环从1加到100的和为：" + sum);


        //for循环
        sum = 0;
        int oddSum = 0;
        int evenSum = 0;


        for (int j = 0; j <= 100; j++) {//int j中的j 仅在for循环内使用
            sum += j;
            if (j % 2 == 0) {
                evenSum += j;
            } else {
                oddSum += j;
            }
        }
        System.out.println("for循环从1加到100的和为：" + sum);
        System.out.println("100内偶数和：" + evenSum);
        System.out.println("100内奇数和：" + oddSum);

        System.out.println("1~90中能被3整除的数：");
        for (int j = 90; j > 0; j -= 3) {
            System.out.print(j + "\t");
            System.out.print("");
        }


        //计算
        i = 1;
        int count = 0;
        do {
            System.out.print(i + "\t");
            i++;
            if (i % 5 == 0) {
                System.out.println();
            }
        } while (i <= 130);

        //計數器
        i = 1;
        count = 0;
        do {
            System.out.print(i + "\t");
            i++;
            count++;
            if (count % 5 == 0) {
                System.out.println();
                count = 0;
            }
        } while (i <= 130);


    }
}
