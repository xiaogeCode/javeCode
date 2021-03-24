package xiaoge.socketBS;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Copyright (C), 2021, xiaoge
 * FileName: TcpServerBS
 * Author:   lin_yunhua
 * Date:     2021/3/24 18:04
 * Description: 模拟服务器接收浏览器请求
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class TcpServerBS {
    public static void main(String[] args) throws IOException {
        //服务器端
        ServerSocket server=new ServerSocket(9000);
        while (true){
            Socket socket = server.accept();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream is = socket.getInputStream();
                        BufferedReader breader= new BufferedReader(new InputStreamReader(is));
                        //获取第一行 GET /Socket-tcp/web/1.html HTTP/1.1
                        String str = breader.readLine();
                        //空格处分成俩段
                        String[] arr = str.split(" ");
                        //获取 Socket-tcp/web/1.html
                        String filePath = arr[1].substring(1);
                        filePath = "D:\\workspace\\java\\code-2021\\" +filePath;

                        System.out.println("filePath: "+filePath);

                        //上传html
                        //本地输入流读取
                        FileInputStream fis=new FileInputStream(filePath);
                        //网络流上传
                        OutputStream os = socket.getOutputStream();

                        //写入http协议响应头，固定写法
                        os.write("HTTP/1.1 200 OK\r\n".getBytes());
                        os.write("Content-Type:text/html\r\n".getBytes());
                        //必须写入空行，否则浏览器不响应
                        os.write("\r\n".getBytes());

                        byte[] bytes = new byte[1024];
                        int len = 0;
//                        os.write("cool".getBytes());
                        while ((len=fis.read(bytes))!=-1){
                            os.write(bytes,0,len);
                        }
                        System.out.println("上传结束");

                        fis.close();
                        socket.close();

                    }catch (Exception e ){
                        e.printStackTrace();
                    }
                }
            }).start();


        }


        //server.close();


    }
}
