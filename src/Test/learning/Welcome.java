package Test.learning;

import java.util.Scanner;

/*
 *我的第一个代码
 */
public class Welcome {

    public static void main(String[] args) {
        System.out.println("舒服了！");
        Scanner scanner = new Scanner(System.in);
        String s = scanner.nextLine();

        if (s.equals("good")) {
            System.out.println(s);
        } else {
            System.out.println("not");
        }
        scanner.close();

    }
}
