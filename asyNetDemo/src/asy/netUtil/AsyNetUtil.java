package asy.netUtil;

import java.util.concurrent.ExecutorService;
/*
 * 异步请求及回调
 * */
import java.util.concurrent.Executors;
 

public class AsyNetUtil {

	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);  

	//回调接口
	public interface CallBack{
		void onResponse(String res);
	}
	public  static ExecutorService getThreadPool() {
		return fixedThreadPool;
	}
	public static void post(final String url,final String content,final CallBack callBack) {
		
		//加入线程池
		AsyNetUtil.getThreadPool().execute(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String response = NetUtils.post(url,content);
				callBack.onResponse(response);
			}
		});
		/*
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String response = NetUtils.post(url,content);
				callBack.onResponse(response);
				
			}
		}).start();
		*/
	}
	public static void get(final String url,final CallBack callBack) {
		//final Handler handler = new Handler();

		//加入线程池
		AsyNetUtil.getThreadPool().execute(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String response = NetUtils.get(url);
				callBack.onResponse(response);
			}
		});
		/*
		new Thread(new Runnable() {
			@Override
			public void run() {
				// TODO Auto-generated method stub
				String response = NetUtils.get(url);
				callBack.onResponse(response);
				
			}
		}).start();
		*/
	}	
	//对连接方法进行整理
	public static void connWithMethod(final String url,final String content,String method) {
		switch (method) {
		case "POST":
		{
			AsyNetUtil.post(url,"aa", new AsyNetUtil.CallBack() {
				
				@Override
				public void onResponse(String res) {
					// TODO Auto-generated method stub
					System.out.println("post:"+res);
				}
			});
		}
			break;
		case "GET":
		{
			AsyNetUtil.get(url, new AsyNetUtil.CallBack() {
				
				@Override
				public void onResponse(String res) {
					// TODO Auto-generated method stub
					System.out.println("get:"+res);
				}
			});
		}
			break;

		default:
			break;
		}
		
	}
	
}
