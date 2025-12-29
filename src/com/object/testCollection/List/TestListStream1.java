package com.object.testCollection.List;

import java.util.ArrayList;
import java.util.Arrays;

public class TestListStream1 {
    public static void main(String[] args) {
        TestListStream1.test1();
    }

    public static void test1() {
        ArrayList<String> arrayList = new ArrayList<>();
        arrayList.add("1");
        arrayList.add("2");
        arrayList.add("6");
        arrayList.add("4");
        arrayList.add("5");
        arrayList.add("1");

        arrayList.stream().filter(s -> s.contains("1")).forEach(System.out::println);

        Integer[] array = arrayList.stream().filter(s -> s.contains("1")).map(Integer::parseInt).toArray(Integer[]::new);
        System.out.println(String.join(",", Arrays.toString(array)));
    }

    public static void test2() {
            System.out.println();
    }
}
