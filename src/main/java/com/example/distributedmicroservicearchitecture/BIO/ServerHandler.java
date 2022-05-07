package com.example.distributedmicroservicearchitecture.BIO;

import org.apache.lucene.spatial.spatial4j.Geo3dDistanceCalculator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ServerHandler implements Runnable {
    private Socket socket;
    //构造器接受Socket传参并赋值当前类的Socket
    public ServerHandler(Socket socket){
        this.socket = socket;
    }

    //此处与客户端类似，无非是两端之间数据的读写
    @Override
    public void run() {
        BufferedReader in = null;
        PrintWriter out = null;

        try{
            //获取套接字的输入流，进行读的操作
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            //获取套接字的输入流，进行写的操作
            out = new PrintWriter(socket.getOutputStream(),true);
            String expression;
            int result = 0;
            while (true){
                //in.redLine()返回null如果已经读到输入流的末尾
                /** 先赋值后判断，无论判断结果都会赋值 **/
                if ((expression = in.readLine()) == null){
                    //读完，推出循环
                    break;
                }
                System.out.println("服务器接收到消息: " + expression);
                try {
                    //返回给客户端的字符串
                    char[] chars = expression.toCharArray();
                    if (chars[1] == '+'){
                        result = (Integer.parseInt(String.valueOf(chars[0]))) + (Integer.parseInt(String.valueOf(chars[2])));
                    }
                    if (chars[1] == '*'){
                        result = (Integer.parseInt(String.valueOf(chars[0]))) * (Integer.parseInt(String.valueOf(chars[2])));
                    }
                    if (chars[1] == '/'){
                        result = (Integer.parseInt(String.valueOf(chars[0]))) / (Integer.parseInt(String.valueOf(chars[2])));
                    }
                    if (chars[1] == '-'){
                        result = (Integer.parseInt(String.valueOf(chars[0]))) - (Integer.parseInt(String.valueOf(chars[2])));
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
                out.println(result);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            //关闭相关的流
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
