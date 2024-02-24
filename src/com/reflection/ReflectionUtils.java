package com.reflection;

import java.lang.reflect.Field;
import java.util.HashMap;
import java.util.Map;

/**
 * @Author: yanjian
 * @CreateDate: 2023/11/16 10:03
 * @Description:
 * @Version: 1.0
 * @Since: 1.8
 * @UpdateDate:
 * @UpdateUser:
 */
public class ReflectionUtils {
    /**
     * bean 转 Map
     * @param object bean对象
     * @return
     */
    public Map<String,Object> beanToMap(Object object) {
        HashMap<String, Object> map = new HashMap<>();
        Class<?> objectClass = object.getClass();

        for (Field field : objectClass.getDeclaredFields()){
            try {
                field.setAccessible(true);
                map.put(field.getName(),field.get(object));
            } catch (SecurityException | IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
            }
        }
        return map;
    }
}
