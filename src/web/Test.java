package web;//package com;
//
//import java.math.BigDecimal;
//import java.util.HashMap;
//
///**
// * @Author: yanjian
// * @CreateDate: 2023/04/15 18:05
// * @Description:
// * @Version: 1.0
// * @Since: 1.8
// * @UpdateDate:
// * @UpdateUser:
// */
//public class Test {
//
//    @org.junit.jupiter.api.Test
//    public void test(){
//        BigDecimal bigDecimal = new BigDecimal(2.000);
//        int i = bigDecimal.intValue();
//        System.out.println("直接转" + i);
//        HashMap<String, Object> stringObjectHashMap = new HashMap<>();
//        stringObjectHashMap.put("qty",bigDecimal);
//        System.out.println(new Integer(bigDecimal.toString()));
//        try {
//            stringObjectHashMap.put("qty",new Integer(bigDecimal.toString()));
//        } catch (RuntimeException e) {
//            System.out.println("存放cuow");
//            e.printStackTrace();
//        }
//        stringObjectHashMap.remove("qty");
//        stringObjectHashMap.put("qty",new Integer(bigDecimal.toString()));
//        stringObjectHashMap.clear();
//        System.out.println(stringObjectHashMap.size());
//        System.out.println(stringObjectHashMap.get("qty"));
//    }
//}
