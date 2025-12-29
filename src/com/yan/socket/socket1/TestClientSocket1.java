package com.yan.socket.socket1;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;

public class TestClientSocket1 {
    public static void main(String[] args) {
        try {
            //
            TestClientSocket1.test1();
        } catch (UnknownHostException e) {
            //
            e.printStackTrace();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    public static void test1() throws IOException {
        Socket s = new Socket(InetAddress.getLocalHost(), 8888);
        System.out.println("客户端发起socket");

        // 发送数据
        SocketContent1 socketContent1 = new SocketContent1("hello", "world", 1, null, null, null);
//        s.getOutputStream().write(socketContent1.toString().getBytes());
        ObjectOutputStream oos = new ObjectOutputStream(s.getOutputStream());
        oos.writeObject(socketContent1);
        System.out.println("数据发送完毕");
//        oos.flush();
        oos.close();
        s.close();
    }
}
