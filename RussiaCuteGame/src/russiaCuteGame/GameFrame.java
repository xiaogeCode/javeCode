package russiaCuteGame;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

import java.awt.*;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;

import java.util.Random;

import java.util.TimerTask;
import java.util.Timer;  
import java.awt.FontMetrics;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class GameFrame extends JFrame implements KeyListener{

	private int mapWidth, mapHeight, cuteLen;
    private int[][] currentCuteArray;
    private int[][] nextCuteArray;
    private int[][] mapArray;
    private int currentX;
    private int currentY;
    private int currentCuteType;
    private int nextCuteType;
    private boolean	isQuickMove;
    private Timer timer=new Timer();
	/**
	 *使用ScheduledExecutorService代替Timer
	 */
    private ScheduledExecutorService executorService = null;
    private int score=0;
    private int timeInterver =1000;
    private boolean	isPause=false;
	
	public GameFrame(int width,int heigh){
		this.mapWidth = width;
		this.mapHeight = heigh;
		this.cuteLen = 30;
		
		currentX =(width-4)/2;
		currentY =0;
		
		currentCuteArray = new int[4][4];
		nextCuteArray = new int[4][4];
		setFrame();
		
		randomCreatCuteArray();
		
		reSetView();
		
				
	}
	private void reSetView(){
		this.mapArray = new int[this.mapWidth][this.mapHeight];
		for(int i = 0; i< mapWidth; i++){
			for(int j = 0; j< mapHeight; j++){
				mapArray[i][j]=0;
			}
		}
		creatCuteArray();
		
		score=0;
		timeInterver =1000;
		isPause=false;
		

		//初始化定时器
        closeExecutorService();
      	setExecutorServices();
	}



	@Override
    public void paint(Graphics g) {

        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
        Graphics big =bi.getGraphics();  

        for(int i = 0; i< mapWidth; i++){
			for(int j = 0; j< mapHeight; j++){
				if(mapArray[i][j]==1){

						Color c =big.getColor();  
			            big.setColor(Color.red);  
			            big.fillRect(i*(cuteLen +1), j*(cuteLen +1), cuteLen, cuteLen);
			            big.setColor(c); 
					}
					
				}
			}  
        for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(currentCuteArray[i][j]==1){
					Color c =big.getColor();  
		            big.setColor(Color.red);  
		            big.fillRect((i+ currentX)*(cuteLen +1), (j+ currentY)*(cuteLen +1), cuteLen, cuteLen);
		            big.setColor(c); 
				}
				
			}
		}
        for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(nextCuteArray[i][j]==1){

					Color c =big.getColor();  
		            big.setColor(Color.blue);  
		            big.fillRect((i+ mapWidth +1)*(cuteLen +1), (j+1)*(cuteLen +1)+10, cuteLen, cuteLen);
		            big.setColor(c); 
				}
				
			}
		}

		Color c =big.getColor();
        big.setColor(Color.green);  
        big.fillRect((mapWidth)*(cuteLen +1), 0, 2, (mapHeight +1)*(cuteLen +1));
        big.setColor(c); 
		


        big.drawString("score:   "+score, (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*0);
        big.drawString("ENTER：重新开始ʼ" , (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*1);
        big.drawString("	A：左移 ", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*2);
        big.drawString("	D：右移动", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*3);
        big.drawString("	S：快速向下", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*4);
        big.drawString("SPACE：翻转 ", (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*5);
        big.drawString("	W：暂停/开始" , (mapWidth +1)*(cuteLen +1), 6*(cuteLen +1)+50*6);

        g.drawImage(bi,0,0,null);  
                  
    }
    private void setFrame(){
		this.setTitle("russia_xiaoge");
		this.setSize((cuteLen +1)*(this.mapWidth +6) , (cuteLen +1)*(this.mapHeight));
		this.setLocation(400, 50);
		this.setResizable(false);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		this.addKeyListener(this);
		this.addWindowListener(new WindowAdapter() {  
		@Override
        public void windowClosing(WindowEvent arg0) {
			System.exit(1);
		}  
	    }); 

	}


    private void jlabelSetText(JLabel jLabel, String longString)  {
        StringBuilder builder = new StringBuilder("<html>");
        char[] chars = longString.toCharArray();
        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len++;
                if (start + len > longString.length()) {
                    break;
                }
                if (fontMetrics.charsWidth(chars, start, len) 
                        > jLabel.getWidth()) {
                    break;
                }
            }
            builder.append(chars, start, len-1).append("<br/>");
            start = start + len - 1;
            len = 0;
        }
        builder.append(chars, start, longString.length()-start);
        builder.append("</html>");
        jLabel.setText(builder.toString());
    }


    private boolean canCuteRote(){
		if(currentCuteType ==8){

			return false;
		}
		
		int switchLen = 3;
		if(currentCuteType ==1 || currentCuteType ==2){

			switchLen=4;
		}
		int[][] roteArray = new int[4][4];

		for(int i=0;i<switchLen;i++){
			for(int j=0;j<switchLen;j++){
                roteArray[i][j]= currentCuteArray[j][switchLen-1-i];
			}
		}
		for(int i1=0;i1<4;i1++){
			for(int j1=0;j1<4;j1++){
				if(roteArray[i1][j1]==1){
					int tmpX = i1+ currentX;
					int tmpY = j1+ currentY;
					if(tmpX<0 || tmpX> mapWidth -1 ||tmpY<0 || tmpY> mapHeight -1 ){

						return false;
					}
					if(mapArray[tmpX][tmpY]==1){

						return false;
					}
				}
			}
		}
		return true;
		
	}

    private void roteCuteArray(){
		if(currentCuteType ==8){

			return;
		}

		if(!canCuteRote()){
			return;
		}
		int switchLen = 3;
		if(currentCuteType ==1 || currentCuteType ==2){

			switchLen=4;
		}
		int[][] roteArray = new int[4][4];

		for(int i=0;i<switchLen;i++){
			for(int j=0;j<switchLen;j++){
                roteArray[i][j]= currentCuteArray[j][switchLen-1-i];
			}
		}
		//
		for(int i1=0;i1<4;i1++){
            System.arraycopy(roteArray[i1], 0, currentCuteArray[i1], 0, 4);
		}
		
		
	}

	private void creatCuteArray (){
		isQuickMove=false;
		currentX =(mapWidth -4)/2;
		currentY =0;
		currentCuteType = nextCuteType;
		
		for(int i=0;i<4;i++){
            System.arraycopy(this.nextCuteArray[i], 0, this.currentCuteArray[i], 0, 4);
		}
		randomCreatCuteArray();
	}

    private void randomCreatCuteArray (){
		Random random = new Random();
		int i =random.nextInt(8)+1;

		nextCuteType = i;
		switch(i){
		case 1:
			//1
			nextCuteArray = new int[][]{{0, 0, 0, 0},{1, 1, 1, 1},{0, 0, 0, 0},{0, 0, 0, 0}};
			break;
		case 2:
			//
			nextCuteArray =new int[][] {{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0}};
			break;
		case 3:
			//
			nextCuteArray =new int[][]{ {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 4:
			//
			nextCuteArray =new int[][]{{ 1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 5:
			//N
			nextCuteArray =new int[][]{ {0, 1, 1, 0},{ 1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 6:
			//N
			nextCuteArray =new int[][]{{ 1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 7:
			//T
			nextCuteArray =new int[][]{{ 1, 0, 0, 0},{ 1, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 8:
			//
			nextCuteArray =new int[][]{ {1, 1, 0, 0}, {1, 1, 0, 0},{ 0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		default:
				break;
		}
	}

	private boolean canCuteMove (int direction){

		int[] a=new int[]{-1,1,0};
		int[] b=new int[]{0,0,1};
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(currentCuteArray[i][j]==1){
					int newX =i+a[direction]+this.currentX;
					int newY =j+b[direction]+this.currentY;

					if(newX<0 || newX>this.mapWidth -1 || newY<0 || newY>this.mapHeight -1){

						return false;
					}
					if(this.mapArray[newX][newY]==1){

						return false;
					}
				}
			}
		}
		return true;
	}

    private boolean isCuteToBotom (){

		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(currentCuteArray[i][j]==1){
					int newX =i+this.currentX;
					int newY =j+1+this.currentY;
					if(j+this.currentY == mapHeight -1){
						return true;
					}
					if(mapArray[newX][newY]==1){
						return true;
					}
				}
			}
		}
		return false;
	}

    private void handleToCuteToBotom (){

		if(isCuteToBotom()){

			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(currentCuteArray[i][j]==1){
						this.mapArray[i+ currentX][j+ currentY]=1;
					}
				}
			}

			dismissCutes();
			repaint();

			//重置定时器
			resetExecutorService();
			
			creatCuteArray();
		}
	}

    private void moveCute(int direction){
		if(canCuteMove(direction)){
			
			//direction 0  1  2
			int[] a=new int[]{-1,1,0};
			int[] b=new int[]{0,0,1};
			this.currentX = this.currentX +a[direction];
			this.currentY = this.currentY +b[direction];

			if(isCuteToBotom()){
				if(!(canCuteMove(0) ||canCuteMove(1))){

					handleToCuteToBotom();
				}else{

					cuteBotomtimer();
				}
				
				
				
				
			}
			
			
		}
	}

    private boolean canCuteMiss(int row){
		if(row<0 || row> mapHeight -1){
			return false;
		}
		for(int i = 0; i< mapWidth; i++){
			if(mapArray[i][row]==0){
				return false;
			}
		}
		return true;
	}

    private void dismissCute(int row){
		if(row> mapHeight -1){
			return;
		}
		for(int i = 0; i< mapWidth; i++){
            System.arraycopy(mapArray[i], 0, mapArray[i], 1, row);
		}
	}

    private void dismissCutes(){
		int k=0;
		for(int i = currentY; i< mapHeight; i++){
			if(canCuteMiss(i)){
				k=k+1;
				dismissCute(i);
			}
		}
		switch(k){
		case 1:
			score= score+100;
			break;
		case 2:
			score= score+200;
			break;
		case 3:
			score= score+500;
			break;
		case 4:
			score= score+1500;
			break;
		default:
			break;
		}
			
	}
    private void pauseGame(){
		closeExecutorService();
	}
    private void resumeGame(){
		closeExecutorService();
		setExecutorServices();
	}

	private void setExecutorServices() {
	    long initialDelay = 0;
        long period = timeInterver;
        executorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        //首次延迟initialDelay秒 周期time_interver 执行
        executorService.scheduleAtFixedRate(() -> {
            //do something
            moveCute(2);
            repaint();

        },initialDelay,period, TimeUnit.MILLISECONDS);
	}
	private void closeExecutorService() {
        if (executorService != null) {
            executorService.shutdown();
            executorService = null;
        }
    }

    private  void timer() {
		  
		  TimerTask task = new TimerTask() {  
			             @Override  
			            public void run() {  
			                 // task to run goes here  
			            	 //moveCute(2);

			            	 //repaint();
			              }  
			         }; 
			        
			          timer = new Timer();
			          long delay = 0;  
			          long intevalPeriod = timeInterver;
			          // schedules the task to be run in an interval  
			         timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
		 
	  }
    private void  resetExecutorService() {
        int k = score/1000;
        int tmpInterver = timeInterver;
        timeInterver =(1000-100*k)>0?(1000-100*k):50;

        if(tmpInterver> timeInterver){

            AlertDialog alert = new AlertDialog();
            alert.showDialog(this, "第"+k+"关", 1);

            closeExecutorService();
            setExecutorServices();
        }
    }
    private void  restTimer() {
		  int k = score/1000;
		  int tmp_interver = timeInterver;
		  timeInterver =(1000-100*k)>0?(1000-100*k):50;
		  if(tmp_interver> timeInterver){
			AlertDialog alert = new AlertDialog();
			alert.showDialog(this, "第"+k+"关", 1);

			timer.cancel();
			timer = null;
				
		    timer();
			}
		
	}

    private  void cuteBotomtimer() {
        /*Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                handleToCuteToBotom();
                this.cancel();//ִ
            }
        }, 250, 500);*/

        long initialDelay = 250;
        ScheduledExecutorService cuteBotomScheexecutorService = new ScheduledThreadPoolExecutor(1,
                new BasicThreadFactory.Builder().namingPattern("example-schedule-pool-%d").daemon(true).build());
        //首次延迟initialDelay秒  执行
        //do something
        cuteBotomScheexecutorService.schedule(this::handleToCuteToBotom,initialDelay, TimeUnit.MILLISECONDS);
    }
@Override
public void keyPressed(KeyEvent e) {
    switch(KeyEvent.getKeyText(e.getKeyCode())){
        case "A":{
            this.moveCute(0);
            break;
        }

        case "D":
            this.moveCute(1);
            break;
        case "S":{
            isQuickMove=true;
            for(int i=0;i<20;i++){
                if(isQuickMove){
                    this.moveCute(2);
                }
            }
        }

        break;
        case "空格":
            roteCuteArray();
            break;
        case "Enter":
        {
            reSetView();
            break;
        }
        case "W":
        {
            if(!isPause){
                pauseGame();
            }else{
                resumeGame();
            }
            isPause = !isPause;


        }
        break;
        default:
            break;
    }
    repaint();


}

    @Override
public void keyReleased(KeyEvent e) {
 }
		 
@Override
public void keyTyped(KeyEvent e) {
}

}
