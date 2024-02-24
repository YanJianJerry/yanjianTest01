package com.syntax;

import java.util.ArrayList;
import java.util.List;

import static java.lang.Math.PI;//静态导入
import static java.lang.Math.random;

public class Package {

    public static void main(String[] args) {
        System.out.println(PI);
        System.out.println(Math.random());
        System.out.println(random());

        List list = new ArrayList();
    }
}
