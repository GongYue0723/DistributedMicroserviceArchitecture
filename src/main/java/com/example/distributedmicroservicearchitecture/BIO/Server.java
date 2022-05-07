package com.example.distributedmicroservicearchitecture.BIO;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * 传统BIO式编程
 * 服务端的启动类，具体客户端的监听和返回数据有ServerHandler实现
 * 一个请求对应一个线程，在高并发的情况下缺乏弹性，容易导致服务器崩溃
 */
public class Server {
    private static int DEFAULT_PORT = 12345;
    /** Socket的服务端 **/
    private static ServerSocket server;
    //入参未配置端口，则使用默认的端口
    public static void start() {
        start(DEFAULT_PORT);
    }
    //默认不会有大量的并发访问，直接进行方法同步
    public synchronized static void start(int port){
        //每次调用必须保server被清空过，即上一个请求已完成
        if (server != null){
            return;
        } try{
            //通过构造函数创建ServerSocket， 如果端口合法且空闲，服务端就监听成功
            server = new ServerSocket(port);
            System.out.println("服务器已启动, 端口号: " + port);
            //无限循环保持监听的状态
            while (true){
                //每当有新的客户端接入时，创建一个新的线程处理这条socket链路
                Socket socket = server.accept();
                /**
                 * BIO的实现原理: 服务端的线程个数与客户端的并发访问呈1:1的关系
                 */
                new Thread(new ServerHandler(socket)).start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭服务器，并清理相关的流
            if (server != null){
                try {
                    System.out.println("服务器已关闭。");
                    server.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
