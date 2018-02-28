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
			conn.setRequestMethod("POST");//�������󷽷�Ϊpost
			conn.setReadTimeout(5000);// ���ö�ȡ��ʱΪ5��
			conn.setConnectTimeout(10000);// �����������糬ʱΪ10��
			conn.setDoOutput(true);// ���ô˷���,������������������
			
			// post����Ĳ���
            String data = content;
            // ���һ�������,�������д����,Ĭ�������,ϵͳ��������������������
            OutputStream out = conn.getOutputStream();// ���һ�������,�������д����
            out.write(data.getBytes()); 
            out.flush();
            out.close();

            int responseCode = conn.getResponseCode();// ���ô˷����Ͳ�����ʹ��conn.connect()����
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
				conn.disconnect();// �ر�����

			}
		}
		return null;
		
	}
	public static String get(String url) {
		HttpURLConnection conn = null;
		try {
			URL mUrl = new URL(url); 
			conn = (HttpURLConnection) mUrl.openConnection();
			conn.setRequestMethod("GET");//�������󷽷�Ϊpost
			conn.setReadTimeout(5000);// ���ö�ȡ��ʱΪ5��
			conn.setConnectTimeout(10000);// �����������糬ʱΪ10��
			conn.setDoOutput(true);// ���ô˷���,������������������
			
            int responseCode = conn.getResponseCode();// ���ô˷����Ͳ�����ʹ��conn.connect()����
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
				conn.disconnect();// �ر�����

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
        String state = os.toString();// �����е�����ת�����ַ���,
        state = new String(state.getBytes(), "UTF-8");//���õı�����utf-8
        os.close();
        return state;
	}
	
}
