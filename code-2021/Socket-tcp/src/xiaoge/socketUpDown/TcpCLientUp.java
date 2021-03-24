package xiaoge.socketUpDown;

import java.io.*;
import java.net.Socket;

/**
 * Copyright (C), 2021, xiaoge
 * FileName: TcpCLientUp
 * Author:   lin_yunhua
 * Date:     2021/3/24 15:58
 * Description: 客户端上传
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class TcpCLientUp {
    public static void main(String[] args) throws IOException {
        //本地输入流
        FileInputStream fis = new FileInputStream("E:\\1.jpg");
        //网络输出流
        Socket socket = new Socket("localhost", 9000);
        OutputStream os = socket.getOutputStream();
        int len = 0;
        byte[] bytes =new  byte[1024];
        while ((len=fis.read(bytes))!=-1){
            os.write(bytes,0,len);
        }
        //防止read()阻塞
        socket.shutdownOutput();

        //从服务器接收回应
        InputStream in = socket.getInputStream();
        while ((len=in.read(bytes))!=-1){
            System.out.println(new String(bytes,0,len));
        }

        fis.close();
        socket.close();




    }
}
