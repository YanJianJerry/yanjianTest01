package Test.learning;
/*
 *continue 跳到循环开始的地方（跳出此次循环）
 * break 跳出循环
 */

public class TestBreakAndContinue {
    public static void main(String[] args) {
        int i = 0;
        while (i < 100) {
            i++;
            if(i%10==0){
                System.out.println();
                continue;
            }
            System.out.print(i+"\t");
        }

        System.out.println();
        i  = 0;
        while (i < 1000) {
            i++;
            if(i%10==0){
                System.out.println();
                break;
        }
            System.out.print(i+"\t");
        }
    }
}
