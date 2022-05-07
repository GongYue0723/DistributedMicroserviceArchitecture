package com.example.distributedmicroservicearchitecture.BIO;

import java.util.Random;

public class BioTest{
    public static void main (String[] args) throws InterruptedException {
        //启动服务器
        new Thread(new Runnable() {
            @Override
            public void run() {
                try{
                    pseudoAsynchronousIO.start();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        }).start();

        //主线程休眠1秒，避免客户端在服务端启动前发送请求
        Thread.sleep(1000);

        char operators[] = {'+', '-', '*', '/'};
        Random random = new Random(System.currentTimeMillis());
        new Thread(new Runnable() {
            @Override
            public void run() {
                while (true){
                    //随机产生表达式
                    String expression = random.nextInt(10) + "" + operators[random.nextInt(4)] + (random.nextInt(10) + 1);
                    //客户端发送表达式到服务端
                    Client.send(expression);
                    try{
                        //让出cpu的资源，保证不会在高并发的情况下电脑TCP/IP端口被快速消耗完
                        Thread.currentThread().sleep(random.nextInt(1000));
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }).start();
    }
}
