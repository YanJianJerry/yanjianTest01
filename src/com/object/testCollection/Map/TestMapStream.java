package com.object.testCollection.Map;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TestMapStream {

    public static void main(String[] args) {
        TestMapStream.test1();
    }

    /**
     * 测试Map的stream
     */
    public static void test1() {
        Map<String, Integer> map = new LinkedHashMap<>();
        map.put("1", 1);
        map.put("2", 2);
        map.put("3", 3);
        map.put("4", 4);
        map.put("5", 5);

        map.entrySet().stream()
                .filter(entry -> entry.getValue() > 3)
                .forEach(entry -> System.out.println(entry.getKey()));

        map.entrySet().stream()
                .filter(entry -> entry.getValue() > 3)
                .map(entry -> entry.getKey())
                .forEach(System.out::println);

        String[] array = map.entrySet().stream()
                .filter(entry -> entry.getValue() > 3)
                .map(Map.Entry::getKey)
                .toArray(String[]::new);
        System.out.println(String.join(",", array));

        List<String> list= map.entrySet().stream()
                .filter(entry -> entry.getValue() > 3)
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
        System.out.println(String.join(",", list));


    }
}
