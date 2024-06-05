package com.object.testEnum;

/**
 * @author yyll
 */
public class EnumTest01 {
    public static void main(String[] args) {
        System.out.println(EnumObj1.MONDAY);

        for (EnumObj1 value : EnumObj1.values()) {
            System.out.println(value);
        }

        System.out.println(EnumObj2.MONDAY);
        System.out.println(EnumObj2.MONDAY.getChineseName());
    }
}
