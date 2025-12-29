package com.object.testCollection.Map;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.Map;

public class TestLinkedHashMap1 {
    public static void main(String[] args) {
        TestLinkedHashMap1.test1();
    }

    /**
     * 测试LinkedHashMap
     */
    public static void test1() {
        Map<String, String[]> allocationDetailsIdMap = new LinkedHashMap<>();
        // 添加数据时顺序即为后续遍历顺序
        allocationDetailsIdMap.put("key1", new String[]{"A", "B"});
        allocationDetailsIdMap.put("key2", new String[]{"C", "D"});

        // 流处理转成String[]
        String[] resultArray = allocationDetailsIdMap.values().stream()
                .map(arr -> String.join(":", arr))
                .toArray(String[]::new);

        System.out.println(String.join(";", resultArray));

        // 顺序遍历
        resultArray = allocationDetailsIdMap.entrySet().stream()
                .sorted(Map.Entry.comparingByKey()) // 确保顺序一致
                .map(e -> String.join(":", e.getValue()))
                .toArray(String[]::new);
        System.out.println(String.join(";", resultArray));

        // Lambda遍历，函数式接口Iterator.forEach()
        allocationDetailsIdMap.entrySet().forEach(entry -> {
            if ("key1".equalsIgnoreCase(entry.getKey())) {
                System.out.println(Arrays.toString(entry.getValue()));
            } else if ("key2".equalsIgnoreCase(entry.getKey())) {
                entry.setValue(new String[]{"E", "F"});
            }
        });
        System.out.println(Arrays.toString(allocationDetailsIdMap.get("key2")));

        // Iterator 遍历
        Iterator<Map.Entry<String, String[]>> it = allocationDetailsIdMap.entrySet().iterator();
        while (it.hasNext()) {
            Map.Entry<String, String[]> entry = it.next();
            if ("key1".equalsIgnoreCase(entry.getKey())) {
                System.out.println(Arrays.toString(entry.getValue()));
            } else if ("key2".equalsIgnoreCase(entry.getKey())) {
                entry.setValue(new String[]{"2", "2"});
            }
        }
        System.out.println(Arrays.toString(allocationDetailsIdMap.get("key2")));

        // 增强for遍历
        for (Map.Entry<String, String[]> entry : allocationDetailsIdMap.entrySet()) {
            if ("key1".equalsIgnoreCase(entry.getKey())) {
                System.out.println(Arrays.toString(entry.getValue()));
            } else if ("key2".equalsIgnoreCase(entry.getKey())) {
                entry.setValue(new String[]{"1", "1"});
            }
        }
        System.out.println(Arrays.toString(allocationDetailsIdMap.get("key2")));

    }
}
