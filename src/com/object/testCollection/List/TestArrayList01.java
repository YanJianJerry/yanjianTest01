package com.object.testCollection.List;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class TestArrayList01 {
    public static void main(String[] args) {
        // 声明
        TestArrayList01 testArrayList01 = new TestArrayList01();

        // 执行方法
        testArrayList01.test01();
    }

    //修改ArrayList<Map> 中的数据
    private void test01() {
        // 声明
        ArrayList<Map> list1 = new ArrayList<>();
        ArrayList<Map> list2 = new ArrayList<>();
        ArrayList<Map> list3 = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        Map<String, String> map2 = new HashMap<>();

        // 数据处理
        map1.put("QTY","1.0");
        map1.put("NAME","YAN");
        list1.add(map1);
        System.out.println(list1);
        System.out.println(map1.hashCode());

        map2 = list1.get(0);
        map2.put("QTY","0");
        System.out.println(list1);
        // 测试

        System.out.println("==========================");
        System.out.println(list1.hashCode());
        //
        list2.add(list1.get(0));
        System.out.println(list2);
        System.out.println(list2.hashCode());
        map2.put("NAME","YANJIAN");
        System.out.println(list2);
        System.out.println(list2.hashCode());

        list3.addAll(list1);
        System.out.println(list3);
        System.out.println(list3.hashCode());

        System.out.println(list1.hashCode());
        System.out.println(map1.hashCode());

        System.out.println("####################");
        Map<String, String> map3 = new HashMap<>();
        map3.put("age","18");
        list1.add(map3);
        list1.get(0).put("job","IT");
        list1.get(0).put("NAME","JERRY");
        System.out.println(list1);
        System.out.println(list2);
    }
}


