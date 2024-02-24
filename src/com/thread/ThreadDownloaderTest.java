package com.thread;

import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.net.URL;

//多线程同步下载图片
public class ThreadDownloaderTest extends Thread{

    //网络图片地址
    private String url;
    //保存的文件名
    private String fileName;

    //
    public ThreadDownloaderTest(String url,String fileName){
        this.url = url;
        this.fileName = fileName;
    }

    @Override
    public void run(){
        WebDownloader webDownloader = new WebDownloader();
        webDownloader.downloader(url,fileName);
        System.out.println("下载了图片："+fileName);
    }

    public static void main(String[] args) {
        String url1 = "https://tse3-mm.cn.bing.net/th/id/OIP-C.lLiNy3hP0wrQ6neQrBbILwHaFj?w=269&h=202&c=7&r=0&o=5&dpr=1.6&pid=1.7";
        String url2 = "https://tse3-mm.cn.bing.net/th/id/OIP-C.0REkWaZtiSpsy4iswFx82AHaE8?w=282&h=188&c=7&r=0&o=5&dpr=1.6&pid=1.7";
        String url3 = "https://tse1-mm.cn.bing.net/th/id/OIP-C.D7W8aAWtCb1KCeaEUQhKcAHaKA?w=186&h=251&c=7&r=0&o=5&dpr=1.6&pid=1.7";

        String fileName1 = "test1.jpg";
        String fileName2 = "test2.jpg";
        String fileName3 = "test3.jpg";

        ThreadDownloaderTest t1 = new ThreadDownloaderTest(url1,fileName1);
        ThreadDownloaderTest t2 = new ThreadDownloaderTest(url2,fileName2);
        ThreadDownloaderTest t3 = new ThreadDownloaderTest(url3,fileName3);

        t1.start();
        t2.start();
        t3.start();
    }
}

class WebDownloader{
    //
    public void downloader(String url,String fileName) {
        try {
            FileUtils.copyURLToFile(new URL(url),new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
