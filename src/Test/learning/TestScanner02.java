package Test.learning;

import java.util.Scanner;

public class TestScanner02 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        double sum = 0;
        int i = 0;
        System.out.println("Input double:");
        while (scanner.hasNextDouble()) {
            double x = scanner.nextDouble();
            i++;
            sum += x;
            System.out.println("第" + i + "个数据为" + x + ",sum is" + sum);
        }
        System.out.println("sum is " + i);
        System.out.println("m is " + i);

        scanner.close();
    }
}

