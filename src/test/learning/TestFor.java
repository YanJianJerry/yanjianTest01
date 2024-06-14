package test.learning;

public class TestFor {
    public static void main(String[] args) {
        //打印乘法口诀表
        for (int i = 1; i <= 9; i++)
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "\t");
                if (i == j) {
                    System.out.println();
                }
            }

        for (int i = 1; i <= 9; i++) {
            for (int j = 1; j <= i; j++) {
                System.out.print(j + "*" + i + "=" + i * j + "\t");

            }
            System.out.println();
        }

        //打印三角形
        for (int i = 0; i < 5; i++) {
            for (int j = 5; j > i; j--) {
                System.out.print(" ");
            }
            for (int j = 0; j < i; j++) {
                System.out.print("*");
            }
            for (int j = 0; j <= i; j++) {
                System.out.print("*");
            }

            System.out.println();
        }
    }
}
