package io.file;

import java.io.File;
import java.io.IOException;
import java.util.Date;

public class TestFile01 {
    public static void main(String[] args) throws IOException {
        //
        test1();
    }

    public static void test1() throws IOException {
        boolean isCreate;
        boolean isDelete;

        // 默认路径
        System.out.println(System.getProperty("user.dir"));

        // 默认路径(相对路径)，创建文件
//        File file1 = new File("readMe.txt");
//        isCreate = file1.createNewFile();

        // 绝对路径，创建文件
        File file2 = new File("d:/codeFile/readMe.txt");
        isCreate = file2.createNewFile();
        isDelete = file2.delete();

        // 创建目录（多级的文件夹）
        File file3 = new File("d:/codeFile/note");
        // file3.mkdir();// 目录不存在会失败
        // 目录不存在则会创建目录
        isCreate = file3.mkdirs();

        //
        File f = new File("readMe.txt");
        isCreate = f.createNewFile();
        System.out.println(f);
        System.out.println("文件是否存在："+f.exists());
        System.out.println("是否文件："+f.isFile());
        System.out.println("是否路径："+f.isDirectory());
        System.out.println("获取绝对路径："+f.getAbsolutePath());
        System.out.println("获取相对路径："+f.getPath());
        System.out.println("文件的上次修改时间："+new Date(f.lastModified()));
        System.out.println("文件是否可修改："+f.canWrite());
        System.out.println("文件大小："+f.length());
        System.out.println("文件名："+f.getName());

    }
}
