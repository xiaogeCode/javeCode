package asy.main;

import java.nio.channels.AsynchronousCloseException;

import asy.netUtil.*;


public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		AsyNetUtil.connWithMethod("http://www.80txt.com/txtml_75764.html", "aa", "GET");
		//AsyNetUtil.connWithMethod("http://www.80txt.com/txtml_75764.html", "aa", "POST");
		
	}

}
