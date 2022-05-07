package com.example.distributedmicroservicearchitecture.BIO;

import java.io.*;
import java.net.Socket;

public class Client {
    //默认的端口号
    private static int DEFAULT_SERVER_PORT = 12345;
    //默认服务器IP(本机地址)
    private static String DEFAULT_SERVER_IP = "127.0.0.1";
    //发送消息
    public static void send (String expression){
        send(DEFAULT_SERVER_PORT, expression);
    }
    public synchronized static void send (int port, String expression){
        System.out.println("客户端算数表达式为: " + expression);
        Socket socket = null;
        BufferedReader in = null;
        PrintWriter out = null;

        try {
            socket = new Socket(DEFAULT_SERVER_IP, port);
            //获取套接字的输入流，并包装为BufferedReader对象
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取套接字的输出流，并包装成为PrintWriter对象
            out = new PrintWriter(socket.getOutputStream(), true);
            //往服务器写数据
            out.println(expression);
            //获取服务端返回的数据
            System.out.println("结果为: " + in.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //结束后关闭相关的流
            if (in != null){
                try{
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (out != null){
                try{
                    out.close();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
            if (socket != null){
                try{
                    socket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

    }

}
