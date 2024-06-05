package com.object.testEnum;

public enum EnumObj2 {
    MONDAY("星期一"),
    TUESDAY("星期二"),
    THIRSDAY("星期三"),
    FORTHDAY("星期四"),
    FRIDAY("星期五"),
    SATURDAY("星期六"),
    SUNDAY("星期日");

    private final String chineseName;

    EnumObj2(String chineseName) {
        this.chineseName = chineseName;
    }

    @Override
    public String toString() {
        return "EnumObj2{" +
                "chineseName='" + chineseName + '\'' +
                '}';
    }

    public String getChineseName() {
        return chineseName;
    }
}
