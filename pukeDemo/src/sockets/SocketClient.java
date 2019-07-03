package sockets;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.Socket;
/**
 * description
 *
 * @author xiaol
 * @version 1.0
 * @date 2019/7/3 22:40
 */
public class SocketClient {
    // 要连接的服务端IP地址和端口
    private String host = "127.0.0.1";
    private int port = 55533;
    // 与服务端建立连接
    Socket socket;
/*
  * 功能描述: <br>
  * 〈向服务器端发送消息〉
  * @methodName: sendMessange
  * @param     : [message]
  * @return    : void
  * @author    : xiaol
  * @date      : 2019/7/3 23:24
  */
    public void sendMessange(String message)throws Exception{
        if (socket == null){
            socket= new Socket(host, port);
        }
        // 建立连接后获得输出流
        OutputStream outputStream = socket.getOutputStream();
//        message = "你好  yiwangzhibujian";
        //首先需要计算得知消息的长度
        byte[] sendBytes = message.getBytes("UTF-8");
        //然后将消息的长度优先发送出去
        outputStream.write(sendBytes.length >>8);
        outputStream.write(sendBytes.length);
        //然后将消息再次发送出去
        outputStream.write(sendBytes);
        outputStream.flush();

        //通过shutdownOutput高速服务器已经发送完数据，后续只能接受数据
        socket.shutdownOutput();


//        //==========此处重复发送一次，实际项目中为多个命名，此处只为展示用法
//        message = "第二条消息";
//        sendBytes = message.getBytes("UTF-8");
//        outputStream.write(sendBytes.length >>8);
//        outputStream.write(sendBytes.length);
//        outputStream.write(sendBytes);
//        outputStream.flush();

//        outputStream.close();
//        socket.close();
    }
    /*
      * 功能描述: <br>
      * 〈从服务器端接受消息〉
      * @methodName: getMessageFromServer
      * @param     : []
      * @return    : void
      * @author    : xiaol
      * @date      : 2019/7/3 23:23
      */
    public void getMessageFromServer()throws Exception{
        if (socket != null){
            InputStream inputStream = socket.getInputStream();
            byte[] bytes = new byte[1024];
            int len;
            StringBuilder sb = new StringBuilder();
            while ((len = inputStream.read(bytes)) != -1) {
                //注意指定编码格式，发送方和接收方一定要统一，建议使用UTF-8
                sb.append(new String(bytes, 0, len,"UTF-8"));
            }
            System.out.println("get message from server: " + sb);
        }
    }
    public void closeSocketClient() throws Exception {
        socket.close();
    }

}
