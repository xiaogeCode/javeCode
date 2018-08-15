package russiaCuteGame;
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

//import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.FontMetrics;
//import java.util.concurrent.TimeUnit;

public class GameFrame extends JFrame implements KeyListener{

	int map_width,map_height,cute_len; 	//
	int current_cuteArray[][];
	int next_cuteArray[][];			 	//
	int map_array[][];					//
	int current_x;						//
	int current_y;
	int current_cute_type;
	int next_cute_type;					//
	boolean	isQuickMove;
	Timer timer=new Timer();
	int score=0;						//
	int time_interver=1000;				//
	boolean	isPause=false;					//
	
	public GameFrame(int width,int heigh){
		this.map_width = width;
		this.map_height = heigh;
		this.cute_len = 30;
		
		current_x=(width-4)/2;
		current_y=0;
		
		current_cuteArray = new int[4][4];
		next_cuteArray = new int[4][4];
		setFrame();
		
		randomCreatCuteArray();
		
		reSetView();
		
				
	}
	public void reSetView(){
		this.map_array = new int[this.map_width][this.map_height];
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				map_array[i][j]=0;
			}
		}
		creatCuteArray();
		
		score=0;
		time_interver=1000;
		if(timer!=null){
			timer.cancel();
			timer = null;
		}

		isPause=false;
		
      	timer();
	}
	
	@Override
    public void paint(Graphics g) {

        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
        Graphics big =bi.getGraphics();  

        for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				if(map_array[i][j]==1){

						Color c =big.getColor();  
			            big.setColor(Color.red);  
			            big.fillRect(i*(cute_len+1), j*(cute_len+1), cute_len, cute_len);  
			            big.setColor(c); 
					}
					
				}
			}  
        for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(current_cuteArray[i][j]==1){
					Color c =big.getColor();  
		            big.setColor(Color.red);  
		            big.fillRect((i+current_x)*(cute_len+1), (j+current_y)*(cute_len+1), cute_len, cute_len);  
		            big.setColor(c); 
				}
				
			}
		}
        for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(next_cuteArray[i][j]==1){

					Color c =big.getColor();  
		            big.setColor(Color.blue);  
		            big.fillRect((i+map_width+1)*(cute_len+1), (j+1)*(cute_len+1)+10, cute_len, cute_len);  
		            big.setColor(c); 
				}
				
			}
		}

		Color c =big.getColor();
        big.setColor(Color.green);  
        big.fillRect((map_width)*(cute_len+1), 0, 2, (map_height+1)*(cute_len+1));  
        big.setColor(c); 
		


        big.drawString("score:   "+score, (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*0);
        big.drawString("ENTER：重新开始ʼ" , (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*1);
        big.drawString("	A：左移 ", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*2);
        big.drawString("	D：右移动", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*3);
        big.drawString("	S：快速向下", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*4);
        big.drawString("SPACE：翻转 ", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*5);
        big.drawString("	W：暂停/开始" , (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*6);

        g.drawImage(bi,0,0,null);  
                  
    }  
	public void setFrame(){
		this.setTitle("russia_xiaoge");
		this.setSize((cute_len+1)*(this.map_width+6) , (cute_len+1)*(this.map_height));
		this.setLocation(400, 50);
		this.setResizable(false);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addKeyListener(this);
		this.addWindowListener(new WindowAdapter() {  
		public void windowClosing(WindowEvent arg0) {  
			System.exit(1);//
		}  
	    }); 

	}
	

	void JlabelSetText(JLabel jLabel, String longString)  {
        StringBuilder builder = new StringBuilder("<html>");
        char[] chars = longString.toCharArray();
        FontMetrics fontMetrics = jLabel.getFontMetrics(jLabel.getFont());
        int start = 0;
        int len = 0;
        while (start + len < longString.length()) {
            while (true) {
                len++;
                if (start + len > longString.length())break;
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
	

	public boolean canCuteRote(){
		if(current_cute_type==8){

			return false;
		}
		
		int switchLen = 3;
		if(current_cute_type==1 || current_cute_type==2){

			switchLen=4;
		}
		int[][] rote_array = new int[4][4];

		for(int i=0;i<switchLen;i++){
			for(int j=0;j<switchLen;j++){
				rote_array[i][j]= current_cuteArray[j][switchLen-1-i];
			}
		}
		for(int i1=0;i1<4;i1++){
			for(int j1=0;j1<4;j1++){
				if(rote_array[i1][j1]==1){
					int tmp_x = i1+current_x;
					int tmp_y = j1+current_y;
					if(tmp_x<0 || tmp_x>map_width-1 ||tmp_y<0 || tmp_y>map_height-1 ){

						return false;
					}
					if(map_array[tmp_x][tmp_y]==1){

						return false;
					}
				}
			}
		}
		return true;
		
	}

	public void roteCuteArray(){
		if(current_cute_type==8){

			return;
		}

		if(!canCuteRote()){
			return;
		}
		int switchLen = 3;
		if(current_cute_type==1 || current_cute_type==2){

			switchLen=4;
		}
		int[][] rote_array = new int[4][4];

		for(int i=0;i<switchLen;i++){
			for(int j=0;j<switchLen;j++){
				rote_array[i][j]= current_cuteArray[j][switchLen-1-i];
			}
		}
		//��ֵ
		for(int i1=0;i1<4;i1++){
			for(int j1=0;j1<4;j1++){
				 current_cuteArray[i1][j1]=rote_array[i1][j1];
			}
		}
		
		
	}

	public void creatCuteArray (){
		isQuickMove=false;
		current_x=(map_width-4)/2;
		current_y=0;
		current_cute_type=next_cute_type;
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				this.current_cuteArray[i][j]=this.next_cuteArray[i][j];
			}
		}
		randomCreatCuteArray();
	}

	public void randomCreatCuteArray (){
		Random random = new Random();
		int i =random.nextInt(8)+1;

		next_cute_type = i;
		switch(i){
		case 1:
			//1
			next_cuteArray = new int[][]{{0, 0, 0, 0},{1, 1, 1, 1},{0, 0, 0, 0},{0, 0, 0, 0}};
			break;
		case 2:
			//
			next_cuteArray =new int[][] {{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0}};
			break;
		case 3:
			//
			next_cuteArray =new int[][]{ {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 4:
			//
			next_cuteArray =new int[][]{{ 1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 5:
			//N
			next_cuteArray =new int[][]{ {0, 1, 1, 0},{ 1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 6:
			//N
			next_cuteArray =new int[][]{{ 1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 7:
			//T
			next_cuteArray =new int[][]{{ 1, 0, 0, 0},{ 1, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 8:
			//
			next_cuteArray =new int[][]{ {1, 1, 0, 0}, {1, 1, 0, 0},{ 0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		default:
				break;
		}
	}

	public boolean canCuteMove (int direction){

		int a[]=new int[]{-1,1,0};
		int b[]=new int[]{0,0,1};
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(current_cuteArray[i][j]==1){
					int new_x =i+a[direction]+this.current_x;
					int new_y =j+b[direction]+this.current_y;

					if(new_x<0 || new_x>this.map_width-1 || new_y<0 || new_y>this.map_height-1){

						return false;
					}
					if(this.map_array[new_x][new_y]==1){

						return false;
					}
				}
			}
		}
		return true;
	}

	public boolean isCuteToBotom (){
		//
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(current_cuteArray[i][j]==1){
					int new_x =i+this.current_x;
					int new_y =j+1+this.current_y;
					if(j+this.current_y ==map_height-1){
						return true;
					}
					if(map_array[new_x][new_y]==1){

						return true;
					}
				}
			}
		}
		return false;
	}

	public void handleToCuteToBotom (){

		if(isCuteToBotom()){

			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(current_cuteArray[i][j]==1){
						this.map_array [i+current_x ][j+current_y]=1;
					}
				}
			}

			dismissCutes();
			repaint();

			restTimer();
			
			creatCuteArray();
		}
	}
	//
	public void moveCute(int direction){
		if(canCuteMove(direction)){
			
			//direction 0  1  2
			int a[]=new int[]{-1,1,0};
			int b[]=new int[]{0,0,1};
			this.current_x = this.current_x +a[direction];
			this.current_y = this.current_y +b[direction];

			if(isCuteToBotom()){
				if(!(canCuteMove(0) ||canCuteMove(1))){

					handleToCuteToBotom();
				}else{

					cuteBotomtimer();
				}
				
				
				
				
			}
			
			
		}
	}
	
	public boolean canCuteMiss(int row){
		if(row<0 || row>map_height-1){
			return false;
		}
		for(int i=0;i<map_width;i++){
			if(map_array[i][row]==0){
				return false;
			}
		}
		return true;
	}
	//
	public void dismissCute(int row){
		if(row>map_height-1){
			return;
		}
		for(int i=0;i<map_width;i++){
			for(int j=row;j>0;j--){
				map_array[i][j]=map_array[i][j-1];
			}
		}
	}

	public void dismissCutes(){
		int k=0;
		for(int i=current_y;i<map_height;i++){
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
	public void pauseGame(){
		if(timer !=null){
			timer.cancel();
			timer = null;
		}
		
		
	}
	public void resumeGame(){
		if(timer !=null){
			timer.cancel();
			timer = null;
		}

     	timer();
	}


	  public  void timer() {
		  
		  TimerTask task = new TimerTask() {  
			             @Override  
			            public void run() {  
			                 // task to run goes here  
			            	 moveCute(2);

			            	 repaint();
			              }  
			         }; 
			        
			          timer = new Timer();
			          long delay = 0;  
			          long intevalPeriod = time_interver;  
			          // schedules the task to be run in an interval  
			         timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
		 
	  }
	  public void  restTimer() {
		  int k = score/1000;
		  int tmp_interver = time_interver;
		  time_interver=(1000-100*k)>0?(1000-100*k):50;
		  if(tmp_interver>time_interver){
			AlertDialog alert = new AlertDialog();  
			alert.showDialog(this, "第"+k+"关", 1);

			timer.cancel();
			timer = null;
				
		    timer();
			}
		
	}

    public  void cuteBotomtimer() {
        Timer timer2 = new Timer();
        timer2.schedule(new TimerTask() {
            public void run() {
                handleToCuteToBotom();
                this.cancel();//ִ
            }
        }, 250, 500);
    }
@Override
public void keyPressed(KeyEvent e) {
    System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
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
