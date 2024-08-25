package io.file;

import java.io.FileReader;
import java.io.IOException;

public class TestFileReader01 {
    public static void main(String[] args) {
        test1();
    }

    public static void test1(){
        FileReader reader = null;
        try {
            reader = new FileReader("readMe.txt");
            int data;
            // 循环读取文件
            while((data = reader.read()) != -1){
                System.out.print((char)data);
            }
            System.out.println("\n");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            if (reader != null){
                try {
                    reader.close();
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        }
    }
}
