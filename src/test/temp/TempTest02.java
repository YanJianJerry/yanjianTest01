package test.temp;


import org.apache.commons.collections4.map.CaseInsensitiveMap;

import java.util.HashMap;

public class TempTest02 {
    public static void main(String[] args) {
        String str = "1.0000";
        double d = Double.parseDouble(str);
        System.out.println(d);
        System.out.println(String.format("%.2f",d));

        HashMap<String, String> map = new HashMap<>();
        map.put("sku", "1");
        map.put("serialNo", "2");

        CaseInsensitiveMap map2 = new CaseInsensitiveMap(map);
        System.out.println( map2.get("SKU"));

    }
}
