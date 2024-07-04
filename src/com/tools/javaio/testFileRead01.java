package com.tools.javaio;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class testFileRead01 {
    public static void main(String[] args) {
        //
        test1();
    }

    //
    public static void test1(){
        // 文件路径，这里假设文件在项目根目录下
        String filePath = "src/main/resources/file/test1.txt"; // 根据实际情况修改文件路径

        StringBuilder contentBuilder = new StringBuilder();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String sCurrentLine;

            while ((sCurrentLine = br.readLine()) != null) {
                contentBuilder.append(sCurrentLine).append("\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        String content = contentBuilder.toString(); // 这就是文件的内容
        System.out.println(content);
    }
}
