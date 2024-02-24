package com.annotation;

import java.lang.annotation.*;

@YanjianAnnotation01(id = "1001")
@YanjianAnnotation02
public class AnnotationTest01 {

    @YanjianAnnotation01(id = "2001")
    public void test(){

    }
}

/**
 * @author yanjian
 */
@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface YanjianAnnotation01{
    //注解的参数
    String name() default "YJ";
    int age() default -1;
    String id();
    String[] skills() default {"coding","writing"};

}

@Target({ElementType.TYPE,ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Inherited
@interface YanjianAnnotation02{
    //注解的参数
    String value() default "";

}
