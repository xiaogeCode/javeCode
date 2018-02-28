package asy.netUtil;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import javax.swing.text.ChangedCharSetException;

public class NetUtils {

	public static String post(String url,String content) {
		HttpURLConnection conn = null;
		try {
			URL mUrl = new URL(url); 
			conn = (HttpURLConnection) mUrl.openConnection();
			conn.setRequestMethod("POST");//设置请求方法为post
			conn.setReadTimeout(5000);// 设置读取超时为5秒
			conn.setConnectTimeout(10000);// 设置连接网络超时为10秒
			conn.setDoOutput(true);// 设置此方法,允许向服务器输出内容
			
			// post请求的参数
            String data = content;
            // 获得一个输出流,向服务器写数据,默认情况下,系统不允许向服务器输出内容
            OutputStream out = conn.getOutputStream();// 获得一个输出流,向服务器写数据
            out.write(data.getBytes()); 
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();// 调用此方法就不必再使用conn.connect()方法
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            }else {
                //throw new NetworkErrorException("response status is "+responseCode);
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if (conn!=null) {
				conn.disconnect();// 关闭连接

			}
		}
		return null;
		
	}
	public static String get(String url) {
		HttpURLConnection conn = null;
		try {
			URL mUrl = new URL(url); 
			conn = (HttpURLConnection) mUrl.openConnection();
			conn.setRequestMethod("GET");//设置请求方法为post
			conn.setReadTimeout(5000);// 设置读取超时为5秒
			conn.setConnectTimeout(10000);// 设置连接网络超时为10秒
			conn.setDoOutput(true);// 设置此方法,允许向服务器输出内容
			
            int responseCode = conn.getResponseCode();// 调用此方法就不必再使用conn.connect()方法
            if (responseCode == 200) {
                InputStream is = conn.getInputStream();
                String response = getStringFromInputStream(is);
                return response;
            }else {
                //throw new NetworkErrorException("response status is "+responseCode);
            }
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			if (conn!=null) {
				conn.disconnect();// 关闭连接

			}
		}
		return null;
	}
	private static String getStringFromInputStream(InputStream is) throws IOException {
		// TODO Auto-generated method stub
        ByteArrayOutputStream os = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int len = -1;
        while ((len = is.read(buffer)) != -1) {
            os.write(buffer, 0, len);
        }
        is.close();
        String state = os.toString();// 把流中的数据转换成字符串,
        state = new String(state.getBytes(), "UTF-8");//采用的编码是utf-8
        os.close();
        return state;
	}
	
}
