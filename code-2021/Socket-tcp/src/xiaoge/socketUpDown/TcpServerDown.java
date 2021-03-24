package xiaoge.socketUpDown;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

/**
 * Copyright (C), 2021, xiaoge
 * FileName: TcpServerDown
 * Author:   lin_yunhua
 * Date:     2021/3/24 16:12
 * Description: 服务器端下载
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class TcpServerDown {
    public static void main(String[] args) throws IOException {
        //网络输入流
        ServerSocket server = new ServerSocket(9000);

        while (true) {
            Socket socket = server.accept();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    try {
                        InputStream in = socket.getInputStream();


                        File file = new File("D:\\down");
                        if (!file.exists()) {
                            file.mkdir();
                        }

                        String fileName = System.currentTimeMillis() + ".jpg";

                        //本地输出流
                        FileOutputStream fos = new FileOutputStream(file + "\\" + fileName);

                        System.out.println("begin download");

                        //将网络流写入本地
                        int len = 0;
                        byte[] bytes = new byte[1024];
                        while ((len = in.read(bytes)) != -1) {
                            fos.write(bytes);
                        }

                        System.out.println("download completed");

                        //向客户端返回上传成功
                        OutputStream os = socket.getOutputStream();
                        os.write("上传成功".getBytes());

                        socket.shutdownOutput();

                        fos.close();

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }).start();


        }


//        server.close();

    }

}
