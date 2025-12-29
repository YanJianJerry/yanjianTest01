package com.object.testCollection.List;

import java.util.Iterator;
import java.util.LinkedList;

public class TestLinkedList01 {
    public static void main(String[] args) {
        TestLinkedList01.test1();
    }

    /**
     * 测试LinkedList
     */
    public static void test1() {
        LinkedList<String> linkedList = new LinkedList<>();
        linkedList.add("3");
        linkedList.add("2");
        linkedList.add("1");
        linkedList.add("2");

        linkedList.forEach(System.out::print);
        System.out.println();

        // 不能在foreach中移除元素 不推荐
//        linkedList.forEach(item -> {
//            if ("2".equals(item)){
//                linkedList.remove(item);
//            }
//        });
        System.out.println(linkedList);

        // 循环移除元素 不推荐
        for (String item : linkedList) {
            if ("1".equals(item)){
                linkedList.remove(item);
            }
        }
        System.out.println(linkedList);

        // 迭代器移除元素
        Iterator<String> iterator = linkedList.iterator();
        while (iterator.hasNext()) {
            String item = iterator.next();
            if ("3".equals(item)){
                iterator.remove();
            }
        }
        System.out.println(linkedList);

        // 移除元素
        linkedList.removeIf("2"::equals);
        System.out.println(linkedList);
    }
}
