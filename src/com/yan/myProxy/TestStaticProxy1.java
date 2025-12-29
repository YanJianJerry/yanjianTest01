package com.yan.myProxy;

public class TestStaticProxy1 {
    public static void main(String[] args) {
        //
        Character1 character = new Character1();
        CharacterProxy1 proxy = new CharacterProxy1(character);
        proxy.save();

        proxy.update();
    }


    /**
     * 静态代理类
     */
    public static class CharacterProxy1 implements ObjAction1, ObjAction2{
        private Character1 character;
        public CharacterProxy1(Character1 character) {
            this.character = character;
        }

        @Override
        public void save() {
            System.out.println("开始事务");
            character.save();
            System.out.println("提交事务");
        }

        @Override
        public void update() {
            System.out.println("开始事务");
            character.update();
            System.out.println("提交事务");
        }
    }
}




