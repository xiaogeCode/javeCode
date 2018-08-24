package russiaCuteGame;

public class RussiaCuteGame {

	public static void main(String[] args) {
		try
		{
			org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
		}
		catch(Exception e)
		{
			//TODO exception
		}
		new GameFrame(10,20);
	}

}
