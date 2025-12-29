package com.yan.myProxy;

public class Character1 implements ObjAction1, ObjAction2{
        public Character1() {
        }
        @Override
        public void save() {
            System.out.println("执行保存");
        }

        @Override
        public void update() {
            System.out.println("执行更新");
        }
    }