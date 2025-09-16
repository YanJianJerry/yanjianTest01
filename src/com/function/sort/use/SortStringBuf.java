package com.function.sort.use;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class SortStringBuf {

    public static void main(String[] args) {
        String input = "sku01:loc02:traceA:1;sku01:loc01:traceB:2;sku02:loc01:traceC:4;sku02:loc01:traceD:5;sku03:loc02:traceE:3";
        String order = "desc"; // 或者 "asc"

        List<String> sortedResult = sortRecords(input, order);
        System.out.println(String.join(";", sortedResult));

        // 示例1：3个字段
        String input1 = "sku01:loc02:traceA;sku01:loc01:traceB;sku02:loc01:traceC";
        String result1 = sortRecords(input1, ";", ":", 2, "DESC");
        System.out.println("3字段升序: " + result1);

        // 示例2：5个字段
        String input2 = "a:b:c:d:e;x:a:f:g:h;p:c:m:n:o";
        String result2 = sortRecords(input2, ";", ":", 4, "desc"); // 按第2个字段（b,a,c）降序
        System.out.println("5字段降序: " + result2);

        // 示例3：2个字段（兼容原始场景）
        String input3 = "sku01:loc02;sku01:loc01;sku02:loc01";
        String result3 = sortRecords(input3, ";", ":", 1, "asc");
        System.out.println("2字段升序: " + result3);
    }

    public static List<String> sortRecords(String input, String order) {
        // 1. 按分号分割
        String[] records = input.split(";");

        // 2. 使用 List 存储每个字段组，便于排序
        List<String[]> dataList = new ArrayList<>();

        for (String record : records) {
            String[] parts = record.split(":", -1); // 最多分3段
            if (parts.length > 0) {
                dataList.add(parts); // parts[0]=sku, parts[1]=loc, parts[2]=trace
            }
        }

        // 3. 根据 order 参数进行排序
        Comparator<String[]> comparator;
        if ("desc".equalsIgnoreCase(order)) {
            comparator = Comparator.comparing(arr -> arr[0], Comparator.reverseOrder());
        } else { // 默认升序
            comparator = Comparator.comparing(arr -> arr[0]);
        }

        dataList.sort(comparator);

        // 4. 重新组合成字符串列表
        return dataList.stream()
                .map(arr -> arr[0] + ":" + arr[1] + ":" + arr[2]+ ":" + arr[3])
                .collect(Collectors.toList()); // 在Java 16+ 中使用 toList()，否则使用 collect(Collectors.toList())
    }


    /**
     * 根据指定字段索引进行排序
     * @param input 输入字符串，格式如 "a:b:c;d:e:f"
     * @param delimiter 主分隔符，如 ";"
     * @param subDelimiter 子字段分隔符，如 ":"
     * @param sortByIndex 按第几个字段排序（从 0 开始）
     * @param order 排序顺序："asc" 或 "desc"
     * @return 排序后的字符串
     */
    public static String sortRecords(
            String input,
            String delimiter,
            String subDelimiter,
            int sortByIndex,
            String order) {

        if (input == null || input.trim().isEmpty()) {
            return input;
        }

        // 1. 按主分隔符分割（如 ;）
        String[] records = input.split(delimiter);

        // 2. 解析每条记录为字段数组
        List<String[]> dataList = new ArrayList<>();
        for (String record : records) {
            // 使用 limit = -1 确保保留尾部空字段（可选）
            String[] fields = record.split(subDelimiter, -1);
            dataList.add(fields);
        }

        // 3. 构建比较器，按指定索引排序
        Comparator<String[]> comparator = (arr1, arr2) -> {
            // 处理索引越界：缺失字段视为最小值（可按需调整）
            String val1 = sortByIndex < arr1.length ? arr1[sortByIndex] : "";
            String val2 = sortByIndex < arr2.length ? arr2[sortByIndex] : "";
            return val1.compareTo(val2);
        };

        if ("desc".equalsIgnoreCase(order)) {
            comparator = comparator.reversed();
        }

        // 4. 排序
        dataList.sort(comparator);

        // 5. 使用 String.join 动态拼接每个记录的所有字段
        return dataList.stream()
                .map(fields -> String.join(subDelimiter, fields))
                .collect(Collectors.joining(delimiter));
    }
}
