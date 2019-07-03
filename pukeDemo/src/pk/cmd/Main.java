package pk.cmd;

import pk.ui.GameFrame;
import sockets.SocketClient;
import sockets.SocketServer;

public class Main {

	public static void main(String[] args)throws Exception {
		// TODO Auto-generated method stub
//		PukeManager pManager=new PukeManager();
		//pManager.dealCard(2);
//		pManager.sortCard(pManager.getCardList(pManager.dealCard(1)));
		new GameFrame();
		new SocketServer().startSocketServer();
		//线程阻塞，下面执行不到
//		SocketClient client = new SocketClient();
//		client.sendMessange("hello");
	}
}
