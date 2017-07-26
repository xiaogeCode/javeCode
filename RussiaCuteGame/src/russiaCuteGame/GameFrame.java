package russiaCuteGame;
import java.awt.*;

import javax.swing.*;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter; 
import java.awt.event.WindowEvent;

import java.util.Random;

import java.util.TimerTask;
import java.util.Timer;  

//import java.awt.Dimension;
//import java.awt.FlowLayout;
import java.awt.FontMetrics;
//import java.util.concurrent.TimeUnit;

public class GameFrame implements KeyListener{

	JFrame frame = new JFrame();
	public Container container  = frame.getContentPane();
	int map_width,map_height,cute_len; 	//��ͼ��С������ı߳�
	int current_cuteArray[][];		  	//��ǰ�ķ�����
	int next_cuteArray[][];			 	//Ԥ�����ɵķ�����
	int map_array[][];					//��ͼ����
	int current_x;						//��ǰ����������λ��
	int current_y;
	int current_cute_type;
	int next_cute_type;					//Ԥ�����ɵķ���������
	boolean	isQuickMove;				//�Ƿ����ڿ����ƶ�
	Timer timer=new Timer(); 			//��ʱ�ƶ�
	int score=0;						//����
	int time_interver=1000;				//�����ƶ����
	boolean	isPause=false;					//�Ƿ���ͣ��Ϸ
	
	public GameFrame(int width,int heigh){
		this.map_width = width;
		this.map_height = heigh;
		this.cute_len = 30;
		
		current_x=(width-4)/2;
		current_y=0;
		
		current_cuteArray = new int[4][4];
		next_cuteArray = new int[4][4];
		setFrame();
		//Ԥ�����ɷ�����
		randomCreatCuteArray();
		//������ʾ����
		setInfoForm();
		
	}
	//���õ�ͼ
	public void reSetView(){
		//��ʼ����ͼ����
		this.map_array = new int[this.map_width][this.map_height];
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				map_array[i][j]=0;
			}
		}
		//container.removeAll();
		//������ɷ�����
		creatCuteArray();
		refleshView();
		
		score=0;
		time_interver=1000;
		if(timer!=null){
			timer.cancel();
			timer = null;
		}

		isPause=false;
		
      //��ʼ����ʱ��
      	timer();
		//container.repaint();		
	}
	//ˢ�½���
	public void refleshView(){
    	System.out.println("refleshView");
		container.removeAll();
			
			//setFrame();
		setMapForm();
		setCurteArrayForm();
		setInfoForm();	
		
		int k = score/1000;
		int tmp_interver = time_interver;
		time_interver=1000-100*k;
		if(tmp_interver>time_interver){
			System.out.println("���趨ʱ��");
			AlertDialog alert = new AlertDialog();  
			alert.showDialog(frame, "��"+k+"��", 1);
			//�ٶȱ��ˣ����趨ʱ��
			timer.cancel();
			timer = null;
			
	     //��ʼ����ʱ��
	     	timer();
			//container.repaint();	
			container.repaint();
		}
			
		}
	public void setFrame(){
		System.out.println("setFrame");
		container.setLayout(null);
		frame.setSize((cute_len+1)*(this.map_width+5) , (cute_len+1)*(this.map_height+1));
		frame.setLocation(400, 50);
		frame.setResizable(false);
		
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		frame.addKeyListener(this);
		frame.addWindowListener(new WindowAdapter() {  
		public void windowClosing(WindowEvent arg0) {  
			System.exit(1);// ϵͳ�˳�  
		}  
	    }); 

	}
	//����ͼ����
	public void setMapForm(){
		System.out.println("setMapForm");
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				if(map_array[i][j]==1){
					BasicCute cute=new BasicCute(i,j,this.cute_len );
						container.add(cute);
					}
					
				}
			}
		}
	//����ʾ����
	public void setInfoForm(){
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(next_cuteArray[i][j]==1){
					BasicCute cute=new BasicCute(i+map_width+1,j+1,this.cute_len );
					container.add(cute);
				}
				
			}
		}
		JLabel nameLabel = new JLabel("next:");
		nameLabel.setLocation((map_width+1)*(cute_len+1), 0);
		nameLabel.setSize(100, 30);
		
		JLabel nameLabe2 = new JLabel("next:");
		nameLabe2.setLocation((map_width+1)*(cute_len+1), 5*(cute_len+1));
		nameLabe2.setSize(100, 50*5);
		JlabelSetText(nameLabe2,"score:   "+
				score+
				"------------------------------------- ENTER�� ��ʼ" +
				"---------- A�� ���� " +
				"----------- D��  ����" +
				"----------- S��  ����" +
				"----SPACE��  ��ת "+
				"------- W��  ��ͣ/�ָ�" 
				
				);
		JButton nameLabe3 = new JButton("next:");
		nameLabe3.setLocation((map_width)*(cute_len+1), 0);
		nameLabe3.setSize(5, (map_height+1)*(cute_len+1));
		//ImageIcon image = new ImageIcon(getClass().getResource("/image/ѩ.jpg"));
		nameLabe3.setBackground(Color.blue);
		//nameLabe3.setIcon(image);

		container.add(nameLabel);
		container.add(nameLabe2);
		container.add(nameLabe3);
		container.repaint();
		
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
	//�����������
	public void setCurteArrayForm(){
		System.out.println("setCurteArrayForm");
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(current_cuteArray[i][j]==1){
					BasicCute cute=new BasicCute(i+current_x,j+current_y,this.cute_len );
					container.add(cute);
				}
				
			}
		}
	}
	//�������Ƿ������ת
	public boolean canCuteRote(){
		if(current_cute_type==8){
			//���β���
			return false;
		}
		
		int switchLen = 3;
		if(current_cute_type==1 || current_cute_type==2){
			//1�κ� һ�� �ķ��齻�� 4*4
			switchLen=4;
		}
		int[][] rote_array = new int[4][4];
		//��ת
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
						//Խ��
						return false;
					}
					if(map_array[tmp_x][tmp_y]==1){
						//��ײ
						return false;
					}
				}
			}
		}
		return true;
		
	}
	//��ת������
	public void roteCuteArray(){
		if(current_cute_type==8){
			// ���β���
			return;
		}
		//������ת ����
		if(!canCuteRote()){
			return;
		}
		int switchLen = 3;
		if(current_cute_type==1 || current_cute_type==2){
			//1�κ� һ�� �ķ��齻�� 4*4
			switchLen=4;
		}
		int[][] rote_array = new int[4][4];
		//��ת
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
	//�����·���
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
	//������ɷ�����
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
			//1��
			next_cuteArray = new int[][]{{0, 0, 0, 0},{1, 1, 1, 1},{0, 0, 0, 0},{0, 0, 0, 0}};
			break;
		case 2:
			//һ��
			next_cuteArray =new int[][] {{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0},{0, 1, 0, 0}};
			break;
		case 3:
			//��������
			next_cuteArray =new int[][]{ {1, 0, 0, 0}, {1, 0, 0, 0}, {1, 1, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 4:
			//��������
			next_cuteArray =new int[][]{{ 1, 1, 0, 0}, {1, 0, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 5:
			//N��
			next_cuteArray =new int[][]{ {0, 1, 1, 0},{ 1, 1, 0, 0}, {0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		case 6:
			//��N��
			next_cuteArray =new int[][]{{ 1, 1, 0, 0}, {0, 1, 1, 0}, {0, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 7:
			//T��
			next_cuteArray =new int[][]{{ 1, 0, 0, 0},{ 1, 1, 0, 0}, {1, 0, 0, 0}, {0, 0, 0, 0 }};
			break;
		case 8:
			//����
			next_cuteArray =new int[][]{ {1, 1, 0, 0}, {1, 1, 0, 0},{ 0, 0, 0, 0}, {0, 0, 0, 0} };
			break;
		default:
				break;
		}
	}
	//�����Ƿ���Կ��ƶ�
	public boolean canCuteMove (int direction){
		//direction 0�� 1��  2 ��
		int a[]=new int[]{-1,1,0};
		int b[]=new int[]{0,0,1};
		
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				if(current_cuteArray[i][j]==1){
					int new_x =i+a[direction]+this.current_x;
					int new_y =j+b[direction]+this.current_y;
					System.out.println(new_y);
					if(new_x<0 || new_x>this.map_width-1 || new_y<0 || new_y>this.map_height-1){
						//Խ��
						return false;
					}
					if(this.map_array[new_x][new_y]==1){
						//��ײ
						return false;
					}
				}
			}
		}
		return true;
	}
	//�������Ƿ񵽵���
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
						//��ײ
						return true;
					}
				}
			}
		}
		return false;
	}
	//�����鵽�׺�Ĵ���
	public void handleToCuteToBotom (){
		//����Ѿ�����
		if(isCuteToBotom()){
			//ˢ�µ�ͼ����
			for(int i=0;i<4;i++){
				for(int j=0;j<4;j++){
					if(current_cuteArray[i][j]==1){
						this.map_array [i+current_x ][j+current_y]=1;
					}
				}
			}
			System.out.println("222");
			//�����·���
			//refleshView();
			//��ȥ��������
			dismissCutes();
			creatCuteArray();
			//refleshView();
		}
	}
	//�ƶ�������
	public void moveCute(int direction){
		if(canCuteMove(direction)){
			System.out.println("111");
			
			//direction 0�� 1��  2 ��
			int a[]=new int[]{-1,1,0};
			int b[]=new int[]{0,0,1};
			this.current_x = this.current_x +a[direction];
			this.current_y = this.current_y +b[direction];
			//����Ѿ�����
			if(isCuteToBotom()){
				if(!(canCuteMove(0) ||canCuteMove(1))){
					//�������¼�
					handleToCuteToBotom();
				}else{
					//�ƶ����׺� �ӳ�һ�ᴦ�����¼�
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
	//��ȥ�����������ض�������
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
	//��ȥ���������ķ�����
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
		//��ʼ����ʱ��
     	timer();
	}
	// �趨ָ������task��ָ���ӳ�delay����й̶��ӳ�peroid��ִ��
	  // schedule(TimerTask task, long delay, long period)
	  public  void timer() {
		  
		  TimerTask task = new TimerTask() {  
			             @Override  
			            public void run() {  
			                 // task to run goes here  
			            	 moveCute(2);
			            	//ˢ�½���
			            	refleshView();
			              }  
			         }; 
			        
			          timer = new Timer();
			          long delay = 0;  
			          long intevalPeriod = time_interver;  
			          // schedules the task to be run in an interval  
			         timer.scheduleAtFixedRate(task, delay, intevalPeriod);  
		 
	  }
	  //�����鵽�׺���ʱ����
	  public  void cuteBotomtimer() {
		  Timer timer2 = new Timer();
		    timer2.schedule(new TimerTask() {
		      public void run() {
		    	  handleToCuteToBotom();
		    	  this.cancel();//ִ��һ��
		      }
		    }, 250, 500);
	  }
public void keyPressed(KeyEvent e) {  
	        //text.append("����" + KeyEvent.getKeyText(e.getKeyCode()) + "������\n"); 
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
	case "�ո�":
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
	//ˢ�½���
	refleshView();
	
	
}  
		  
public void keyReleased(KeyEvent e) {  
		     //  text.append("����" + KeyEvent.getKeyText(e.getKeyCode()) + "���ɿ�\n");  
 }  
		 
public void keyTyped(KeyEvent e) {  
		       // text.append("�����������" + e.getKeyChar() + "\n");  
}  

}
