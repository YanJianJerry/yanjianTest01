package com.javaTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * @Author: yanjian
 * @CreateDate: 2022/12/02 15:38
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class JavaTest01 {

    public static void main(String[] args) {
        StringBuilder sql2=new StringBuilder();
        ArrayList<String> sql2ParamObjectsList=new ArrayList<String>();

        double double1 = 10.01;
        String strqty1 = "10.01";
        BigDecimal decimal1 = new BigDecimal(strqty1);
        System.out.println(decimal1.doubleValue());

        String strQty = "12.10";
        BigDecimal decimal2 = new BigDecimal(strQty);
        Double doubleQty = decimal2.doubleValue();
        System.out.println(doubleQty);
        Double doubleQty2 = Double.parseDouble(strQty);
        System.out.println(doubleQty2+"");

        int intQty = decimal2.intValue();
        System.out.println(intQty);

        double doubleqty = (new BigDecimal("12")).doubleValue();
        System.out.println(doubleqty);

        System.out.println(doubleQty2-12.01);

        System.out.println(decimal2.add(decimal1));
        System.out.println(double1 - doubleQty);


        Map<String,Object> dataMap = new HashMap<>();
        List<Map<String, Object>> datas = new ArrayList<>();
        ArrayList<Map<String, Object>> datas2 = new ArrayList<Map<String,Object>>();

        dataMap.put("bigDecimal",new BigDecimal("12.0000"));
        dataMap.put("name","YanJian");
        dataMap.replace("name","Yan Jian","Jerry Yan");
        dataMap.replace("bigDecimal",new BigDecimal("1000.0000"));
        System.out.println(dataMap.get("name"));

        Map<String, Object> dataMap1 = new HashMap<>(dataMap);
        dataMap1.putAll(dataMap);
        System.out.println(dataMap1.toString());
        dataMap.put("","");

        datas.add(dataMap);
        datas.add(dataMap);
        datas.remove(dataMap);
        System.out.println(datas.contains(dataMap));
        System.out.println(datas.toString());
        System.out.println(datas2.toString());


        //
        double double01 = 0.0;
        double01 = double01 + 1.2300;
        System.out.println(double01);

        //Person person =  new Person();


    }
}
