package com.common.publicmethod;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class PM {

    private PM() {
        throw new IllegalStateException("Utility class");
    }

    /**
     * Array toString
     */
    public static <T> void toString(T... elements) {
        // 如果元素为null或者长度为0，直接返回，不打印任何内容
        if (elements == null || elements.length == 0) {
            System.out.println("传参为空串");
        }
        //
        StringBuilder builder = new StringBuilder();

        // 遍历可变参数数组
        if (elements != null ) {
            builder.append("长度:").append(elements.length).append(" 数组元素:[");
        }else{
            builder.append(" 数组元素:[");
        }
        // 遍历可变参数数组，除了最后一个元素外都添加逗号
        for (int i = 0; i < elements.length; i++) {
            builder.append(elements[i]);
            // 如果不是最后一个元素，添加逗号
            if (i < elements.length - 1) {
                builder.append(",");
            }
        }
        // 遍历可变参数数组，排除null元素
//        assert elements != null;
//        for (T element : elements) {
//            // 只在element不为null时进行处理
//            if (element != null) {
//                // 如果builder中已经有内容，先添加逗号
//                if (builder.length() > 0) {
//                    builder.append(",");
//                }
//                // 添加元素，自动处理基本类型和包装类型的转换
//                builder.append(element);
//            }
//        }
        builder.append("]");

        // 输出拼接后的字符串
        System.out.println(builder.toString().trim());
    }

    /**
     * getDateTime 指定格式
     */
    public static String getDateTime(String pattern) {

        if (PM.isNull(pattern)){
            pattern = "yyyy-MM-dd HH:mm:ss";
        }

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

        // 格式化当前时间
        String formattedDateTime = now.format(formatter);

        // 输出格式化后的时间
        return formattedDateTime;
    }

    /**
     * getDateTime 默认返回yyyy-MM-dd HH:mm:ss
     */
    public static String getDateTime() {

        // 获取当前时间
        LocalDateTime now = LocalDateTime.now();

        // 定义时间格式
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

        // 格式化当前时间
        // 输出格式化后的时间
        return now.format(formatter);
    }

    /**
     * 判断是否为空
     *
     * @author
     * @param string
     * @return
     */
    public static boolean isNull(String string) {
        return string == null || string.isEmpty();
    }

    /**
     * 判断不为空
     *
     * @author
     * @param string
     * @return
     */
    public static boolean isNotNull(String string) {
        return !isNull(string);
    }
}
