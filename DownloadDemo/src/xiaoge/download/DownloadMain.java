package xiaoge.download;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class DownloadMain {

	
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		//String photoUrl = "https://ss0.bdstatic.com/5aV1bjqh_Q23odCf/static/superman/img/logo/bd_logo1_31bdc765.png";                                      
        String photoUrl = "http://47.94.248.85:8000/init/2017/09/28/0000002567.png";
		String fileName = photoUrl.substring(photoUrl.lastIndexOf("/"));   
        System.out.println("fileName---->"+fileName);  
        String filePath = "f:";    
        File file = saveUrlAs(photoUrl, filePath + fileName,"GET");    
        System.out.println("Run ok!/n<BR>Get URL file " + file); 
	}
	/**  
	 * @���� ������ʱ�زĽӿ�  
	 * @param filePath �ļ���Ҫ�����Ŀ¼  
	 * @param method ���󷽷�������POST��GET  
	 * @param url �����·��  
	 * @return  
	 */  
	  
	public static File saveUrlAs(String url,String filePath,String method){  
	     //System.out.println("fileName---->"+filePath);  
	     //������ͬ���ļ���Ŀ¼  
	     File file=new File(filePath);  
	     //�ж��ļ����Ƿ����  
	     if (!file.exists())  
	    {  
	        //����ļ��в����ڣ��򴴽��µĵ��ļ���  
	         file.mkdirs();  
	    }  
	     FileOutputStream fileOut = null;  
	     HttpURLConnection conn = null;  
	     InputStream inputStream = null;  
	     try  
	    {  
	         // ��������  
	         URL httpUrl=new URL(url);  
	         conn=(HttpURLConnection) httpUrl.openConnection();  
	         //��Post��ʽ�ύ����Ĭ��get��ʽ  
	         conn.setRequestMethod(method);  
	         conn.setDoInput(true);    
	         conn.setDoOutput(true);  
	         // post��ʽ����ʹ�û���   
	         conn.setUseCaches(false);  
	         //����ָ������Դ   
	         conn.connect();  
	         //��ȡ����������  
	         inputStream=conn.getInputStream();  
	         BufferedInputStream bis = new BufferedInputStream(inputStream);  
	         //�ж��ļ��ı���·�������Ƿ���/��β  
	         if (!filePath.endsWith("/")) {  
	  
	             filePath += "/";  
	  
	             }  
	         //д�뵽�ļ���ע���ļ�����·���ĺ���һ��Ҫ�����ļ������ƣ�  
	         fileOut = new FileOutputStream(filePath+"123.png");  
	         BufferedOutputStream bos = new BufferedOutputStream(fileOut);  
	           
	         byte[] buf = new byte[4096];  
	         int length = bis.read(buf);  
	         //�����ļ�  
	         while(length != -1)  
	         {  
	             bos.write(buf, 0, length);  
	             length = bis.read(buf);  
	         }  
	         bos.close();  
	         bis.close();  
	         conn.disconnect();  
	    } catch (Exception e)  
	    {  
	         e.printStackTrace();  
	         System.out.println("�׳��쳣����");  
	    }  
	       
	     return file;  
	       
	 } 

}
