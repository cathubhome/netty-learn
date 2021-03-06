package com.maxus.netty.io;

import java.io.IOException;
import java.net.Socket;
import java.util.Date;

/**
 * Created with IDEA
 * Author:catHome
 * Description: io编程实现通信（摒弃）
 * Time:Create on 2018/10/8 17:14
 */
@SuppressWarnings("all")
public class IOClient {

    public static void main(String[] args) throws Exception {

        new Thread(() -> {
            try {
                Socket socket = new Socket("127.0.0.1", 8000);
                while (true) {
                    try {
                        socket.getOutputStream().write((new Date() + ": hello world").getBytes());
                        Thread.sleep(2000);
                    } catch (Exception e) {
                    }
                }
            } catch (IOException e) {
            }
        }).start();
    }
}
