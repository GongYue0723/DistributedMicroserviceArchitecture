package com.example.distributedmicroservicearchitecture.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * 伪异步I/O编程模型
 */
public class pseudoAsynchronousIO {
    public static int DEFAULT_PORT = 12345;

    public static ServerSocket serverSocket;

    public static ExecutorService executorService;

    public static void start() {
        start(DEFAULT_PORT);
    }

    public static void start (int port){
        //指定线程数量
        executorService = Executors.newFixedThreadPool(20);
        try{
            serverSocket = new ServerSocket(port);

            while (true){
                Socket socket = serverSocket.accept();

                executorService.execute(new ServerHandler(socket));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

    }
}
