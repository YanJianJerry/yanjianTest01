package com.yan.socket.socket1;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class TestServerSocket1 {
    public static void main(String[] args) {
        try {
            //
            TestServerSocket1.test1();
        } catch (IOException | ClassNotFoundException e) {
            //
            e.printStackTrace();
        }
    }

    public static void test1() throws IOException, ClassNotFoundException {
        ServerSocket ss = new ServerSocket(8888);
        System.out.println("服务器启动");
        while (true) {
            //
            System.out.println("等待客户端连接");
            //
            Socket s = ss.accept();
            System.out.println("一个客户端连接"+ss);

            ObjectInputStream ois = new ObjectInputStream(s.getInputStream());
            //
            Object obj = ois.readObject();

            System.out.println("服务器接收到数据："+ obj);

            ois.close();
            s.close();

        }

//        ss.close();
//        System.out.println("服务器关闭");

    }
}
