package test.temp;

import java.util.ArrayList;
import java.util.List;

public class TempTest01 {
    public static void main(String[] args) {
        test();
    }

    public static void test() {
        StringBuilder strb = new StringBuilder();
        strb.append("select");

        List<Object> list = new ArrayList<>();
        list.add("");

        getSql(strb, list);

        System.out.println(strb.toString());
        System.out.println(list.toString());

    }

    private static void getSql(StringBuilder strb, List<Object> list) {
        strb.append(" where");
        list.add(" 1=1");
    }

}
