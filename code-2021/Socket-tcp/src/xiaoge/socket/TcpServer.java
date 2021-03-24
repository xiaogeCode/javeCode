package xiaoge.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Copyright (C), 2021, xiaoge
 * FileName: TcpServer
 * Author:   lin_yunhua
 * Date:     2021/3/24 4:52
 * Description: socket通信 服务器端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class TcpServer {
    public static void main(String[] args) throws IOException {
        ServerSocket serSocket=new ServerSocket(9000);
        //获取客户端对象
        Socket socket = serSocket.accept();

        //获取客户端输入流
        InputStream in = socket.getInputStream();
        byte[] bytes =new byte[1024];
        int len = in.read(bytes);
        System.out.println(new String(bytes,0,len));

        //向客户端发送数据 获取客户端输出流
        OutputStream os = socket.getOutputStream();
        os.write("收到，谢谢".getBytes());



        socket.close();
        serSocket.close();



    }



}
