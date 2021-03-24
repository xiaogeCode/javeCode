package xiaoge.socket;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;

/**
 * Copyright (C), 2021, xiaoge
 * FileName: TcpClient
 * Author:   lin_yunhua
 * Date:     2021/3/24 4:44
 * Description: socket通信，客户端
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class TcpClient {
    public static void main(String[] args) throws IOException {
        Socket socket=new Socket("localhost",9000);
        //获取输出流
        OutputStream os = socket.getOutputStream();
        os.write("你好".getBytes());

        //从服务器端接收数据 获取输出流
        InputStream in = socket.getInputStream();
        byte[] bytes = new byte[1024];
        int len = in.read(bytes);
        System.out.println(new String(bytes,0,len));



        socket.close();
    }
}
