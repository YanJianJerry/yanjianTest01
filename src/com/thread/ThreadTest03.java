package com.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.concurrent.*;

/**
 * 创建
 */
public class ThreadTest03 implements Callable<Boolean> {

    //网络图片地址
    private String url;
    //保存的文件名
    private String fileName;

    //
    public ThreadTest03(String url,String fileName){
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public Boolean call() {
        WebDownloader2 webDownloader = new WebDownloader2();
        webDownloader.downloader(url,fileName);
        System.out.println("下载了图片："+fileName);
        return true;
    }

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        String url1 = "https://tse3-mm.cn.bing.net/th/id/OIP-C.lLiNy3hP0wrQ6neQrBbILwHaFj?w=269&h=202&c=7&r=0&o=5&dpr=1.6&pid=1.7";
        String url2 = "https://tse3-mm.cn.bing.net/th/id/OIP-C.0REkWaZtiSpsy4iswFx82AHaE8?w=282&h=188&c=7&r=0&o=5&dpr=1.6&pid=1.7";
        String url3 = "https://tse1-mm.cn.bing.net/th/id/OIP-C.D7W8aAWtCb1KCeaEUQhKcAHaKA?w=186&h=251&c=7&r=0&o=5&dpr=1.6&pid=1.7";

        String fileName1 = "test1.jpg";
        String fileName2 = "test2.jpg";
        String fileName3 = "test3.jpg";

        ThreadTest03 t1 = new ThreadTest03(url1,fileName1);
        ThreadTest03 t2 = new ThreadTest03(url2,fileName2);
        ThreadTest03 t3 = new ThreadTest03(url3,fileName3);

        //创建执行服务
        ExecutorService ser = Executors.newFixedThreadPool(3);

        //提交执行
        Future<Boolean> r1 = ser.submit(t1);
        Future<Boolean> r2 = ser.submit(t2);
        Future<Boolean> r3 = ser.submit(t3);

        //获取结果
        boolean rs1 = r1.get();
        System.out.println(rs1);
        boolean rs2 = r2.get();
        System.out.println(rs2);
        boolean rs3 = r3.get();
        System.out.println(rs3);

        //关闭服务
        ser.shutdown();
    }
}

class WebDownloader2{
    //
    public void downloader(String url,String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
