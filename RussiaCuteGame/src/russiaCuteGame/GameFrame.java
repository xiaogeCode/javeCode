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

	int map_width,map_height,cute_len; 	//地图大小，方块的边长
	int current_cuteArray[][];		  	//当前的方块组
	int next_cuteArray[][];			 	//预先生成的方块组
	int map_array[][];					//地图数据
	int current_x;						//当前方块组所在位置
	int current_y;
	int current_cute_type;
	int next_cute_type;					//预先生成的方块组类型
	boolean	isQuickMove;				//是否正在快速移动
	Timer timer=new Timer(); 			//定时移动
	int score=0;						//分数
	int time_interver=1000;				//方块移动间隔
	boolean	isPause=false;					//是否暂停游戏
	
	public GameFrame(int width,int heigh){
		this.map_width = width;
		this.map_height = heigh;
		this.cute_len = 30;
		
		current_x=(width-4)/2;
		current_y=0;
		
		current_cuteArray = new int[4][4];
		next_cuteArray = new int[4][4];
		setFrame();
		
		//预先生成方块组
		randomCreatCuteArray();
		
		reSetView();
		
				
	}
	//重置地图
	public void reSetView(){
		//初始化地图数据
		this.map_array = new int[this.map_width][this.map_height];
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				map_array[i][j]=0;
			}
		}
		//随机生成方块组
		creatCuteArray();
		
		score=0;
		time_interver=1000;
		if(timer!=null){
			timer.cancel();
			timer = null;
		}

		isPause=false;
		
      //初始化定时器
      	timer();
	}
	
	public void paint(Graphics g) {  
        //利用双缓冲画图
        BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
        Graphics big =bi.getGraphics();  
        //big.drawImage(bg.img, bg.x, bg.y, null);  
        for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				if(map_array[i][j]==1){//已经堆积的方块
					//BasicCute cute=new BasicCute(i,j,this.cute_len );
				    //this.setLocation(x*(len+1), y*(len+1));
						Color c =big.getColor();  
			            big.setColor(Color.red);  
			            big.fillRect(i*(cute_len+1), j*(cute_len+1), cute_len, cute_len);  
			            big.setColor(c); 
					}
					
				}
			}  
        for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(current_cuteArray[i][j]==1){//当前方块组
					//BasicCute cute=new BasicCute(i+current_x,j+current_y,this.cute_len );
					//container.add(cute);
					//this.setLocation(x*(len+1), y*(len+1));
					Color c =big.getColor();  
		            big.setColor(Color.red);  
		            big.fillRect((i+current_x)*(cute_len+1), (j+current_y)*(cute_len+1), cute_len, cute_len);  
		            big.setColor(c); 
				}
				
			}
		}
        for(int i=0;i<4;i++){//下一个将要出现的方块组
			for(int j=0;j<4;j++){
				if(next_cuteArray[i][j]==1){
					//BasicCute cute=new BasicCute(i+map_width+1,j+1,this.cute_len );
					//container.add(cute);
					Color c =big.getColor();  
		            big.setColor(Color.blue);  
		            big.fillRect((i+map_width+1)*(cute_len+1), (j+1)*(cute_len+1)+10, cute_len, cute_len);  
		            big.setColor(c); 
				}
				
			}
		}
		//分隔线
		Color c =big.getColor();  
        big.setColor(Color.green);  
        big.fillRect((map_width)*(cute_len+1), 0, 2, (map_height+1)*(cute_len+1));  
        big.setColor(c); 
		
       //生成提示界面
		
        big.drawString("score:   "+score, (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*0);
        big.drawString("ENTER： 重新开始" , (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*1);
        big.drawString("	A： 左移 ", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*2);
        big.drawString("	D： 右移", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*3);
        big.drawString("	S： 快移", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*4);
        big.drawString("SPACE： 翻转 ", (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*5);
        big.drawString("	W： 暂停/恢复" , (map_width+1)*(cute_len+1), 6*(cute_len+1)+50*6);

        g.drawImage(bi,0,0,null);  
                  
    }  
	public void setFrame(){
		this.setTitle("俄罗斯方块_xiaoge");
		this.setSize((cute_len+1)*(this.map_width+6) , (cute_len+1)*(this.map_height));
		this.setLocation(400, 50);
		this.setResizable(false);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		this.addKeyListener(this);
		this.addWindowListener(new WindowAdapter() {  
		public void windowClosing(WindowEvent arg0) {  
			System.exit(1);// 系统退出  
		}  
	    }); 

	}
	
	//jlable自动换行显示内容
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
	
	//方块组是否可以旋转
	public boolean canCuteRote(){
		if(current_cute_type==8){
			//田形不动
			return false;
		}
		
		int switchLen = 3;
		if(current_cute_type==1 || current_cute_type==2){
			//1形和 一形 的方块交换 4*4
			switchLen=4;
		}
		int[][] rote_array = new int[4][4];
		//旋转
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
						//越界
						return false;
					}
					if(map_array[tmp_x][tmp_y]==1){
						//碰撞
						return false;
					}
				}
			}
		}
		return true;
		
	}
	//旋转方块组
	public void roteCuteArray(){
		if(current_cute_type==8){
			// 田形不动
			return;
		}
		//不可旋转 返回
		if(!canCuteRote()){
			return;
		}
		int switchLen = 3;
		if(current_cute_type==1 || current_cute_type==2){
			//1形和 一形 的方块交换 4*4
			switchLen=4;
		}
		int[][] rote_array = new int[4][4];
		//旋转
		for(int i=0;i<switchLen;i++){
			for(int j=0;j<switchLen;j++){
				rote_array[i][j]= current_cuteArray[j][switchLen-1-i];
			}
		}
		//赋值
		for(int i1=0;i1<4;i1++){
			for(int j1=0;j1<4;j1++){
				 current_cuteArray[i1][j1]=rote_array[i1][j1];
			}
		}
		
		
	}
	//生成新方块
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
	//随机生成方块组
	public void randomCreatCuteArray (){
		Random random = new Random();
		//int k = (int) (Math.random()*7)+1;
		int i =random.nextInt(8)+1;
		//for(;k<1;){
			//k =(int) (Math.random()*7)+1;
		//}
		next_cute_type = i;
		switch(i){
		case 1:
			//1形
			next_cuteArray = new int[][]{{0, 0, 0, 0},{1, 1, 1, 1},{0, 0, 0, 0},{0, 0, 0, 0}};
			break;
		case 2:
			//一形
			next_cuteArray =new int[][] {{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0}};
			break;
		case 3:
			//横右折形
			next_cuteArray =new int[][]{ {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 4:
			//横左折形
			next_cuteArray =new int[][]{{ 1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 5:
			//N形
			next_cuteArray =new int[][]{ {0, 1, 1, 0},{ 1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 6:
			//倒N形
			next_cuteArray =new int[][]{{ 1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 7:
			//T形
			next_cuteArray =new int[][]{{ 1, 0, 0, 0},{ 1, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 8:
			//田形
			next_cuteArray =new int[][]{ {1, 1, 0, 0}, {1, 1, 0, 0},{ 0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		default:
				break;
		}
	}
	//方块是否可以可移动
	public boolean canCuteMove (int direction){
		//direction 0左 1右  2 下
		int a[]=new int[]{-1,1,0};
		int b[]=new int[]{0,0,1};
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(current_cuteArray[i][j]==1){
					int new_x =i+a[direction]+this.current_x;
					int new_y =j+b[direction]+this.current_y;
					//System.out.println(new_y);
					if(new_x<0 || new_x>this.map_width-1 || new_y<0 || new_y>this.map_height-1){
						//越界
						return false;
					}
					if(this.map_array[new_x][new_y]==1){
						//碰撞
						return false;
					}
				}
			}
		}
		return true;
	}
	//方块组是否到底了
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
						//碰撞
						return true;
					}
				}
			}
		}
		return false;
	}
	//方块组到底后的处理
	public void handleToCuteToBotom (){
		//如果已经到底
		if(isCuteToBotom()){
			//刷新地图数据
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(current_cuteArray[i][j]==1){
						this.map_array [i+current_x ][j+current_y]=1;
					}
				}
			}
			//生成新方块
			//消去可消的行
			dismissCutes();
			repaint();
			//达到一定分数重置定时器速度
			restTimer();
			
			creatCuteArray();
		}
	}
	//移动方块组
	public void moveCute(int direction){
		if(canCuteMove(direction)){
			
			//direction 0左 1右  2 下
			int a[]=new int[]{-1,1,0};
			int b[]=new int[]{0,0,1};
			this.current_x = this.current_x +a[direction];
			this.current_y = this.current_y +b[direction];
			//如果已经到底
			if(isCuteToBotom()){
				if(!(canCuteMove(0) ||canCuteMove(1))){
					//处理到底事件
					handleToCuteToBotom();
				}else{
					//移动到底后 延迟一会处理到底事件
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
	//消去满足条件的特定方块行
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
	//消去满足条件的方块行
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
		//初始化定时器
     	timer();
	}
	// 设定指定任务task在指定延迟delay后进行固定延迟peroid的执行
	  // schedule(TimerTask task, long delay, long period)
	  public  void timer() {
		  
		  TimerTask task = new TimerTask() {  
			             @Override  
			            public void run() {  
			                 // task to run goes here  
			            	 moveCute(2);
			            	//刷新界面
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
			//速度变了，重设定时器
			timer.cancel();
			timer = null;
				
		    //初始化定时器
		    timer();
			}
		
	}
	  //方块组到底后延时处理
	  public  void cuteBotomtimer() {
		  Timer timer2 = new Timer();
		    timer2.schedule(new TimerTask() {
		      public void run() {
		    	  handleToCuteToBotom();
		    	  this.cancel();//执行一次
		      }
		    }, 250, 500);
	  }
public void keyPressed(KeyEvent e) {  
	        //text.append("键盘" + KeyEvent.getKeyText(e.getKeyCode()) + "键向下\n"); 
	//System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
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
	//刷新界面
	//refleshView();
	repaint();
	
	
}  
		  
public void keyReleased(KeyEvent e) {  
		     //  text.append("键盘" + KeyEvent.getKeyText(e.getKeyCode()) + "键松开\n");  
 }  
		 
public void keyTyped(KeyEvent e) {  
		       // text.append("输入的内容是" + e.getKeyChar() + "\n");  
}  

}
