package ks.source;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


//异步处理
public class AsyUtil {

	static ExecutorService fixedThreadPool = Executors.newFixedThreadPool(3);
	public interface CallBack{
		void onResponse(String res);

	}
	public  static ExecutorService getThreadPool() {
		return fixedThreadPool;
	}

}
