package xiaoge;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.Locale;
import java.util.Random;
import java.util.Stack;

import javax.swing.*;

public class PuzzleGameFrame extends JFrame implements KeyListener,MouseListener{

	int[][] map;
	int map_width;
	int map_height;
	int cute_width;
	boolean isMapCreate;   //��ͼ�Ѿ����ɣ�����Ҫ��������
	Point[] roadArray = new Point[10];
	Stack<Point> pathStack =new Stack<Point>();
	int currentAth=0;		//��ǰ��ͼ����ʹ�õ��㷨
	
	
	//������
	Stack<Point> spreadPointStack =new Stack<Point>();
	
	
	int current_x;
	int current_y;

	
	public PuzzleGameFrame(int width,int height){
		super();
		map_width = width;
		map_height = height;
		cute_width = 20;
		map = new int[map_width][map_height];
		resetMap();
		setForm();
		repaint();
	}
	public void resetMap(){
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				if(i==0 || i==map_width-1 || j==0 || j == map_height-1){
					map[i][j] =1;
				}else{
					map[i][j] = 1;
					if(i%2==1 && j%2==1){
						map[i][j] = 2;
					}
				}
				
			}
		}
		map[1][1]=0;
		current_x =1;
		current_y = 1;
		createMap();
	}
	public void createMap(){
		isMapCreate = false;
		//searchPoint(1,1,map);
		switch (currentAth){
		case 0:{
			//ʹ�÷���һ�����ͼ ��һ��������·
			//��������������С�͵�ͼ
			//setMapWithRect(new Rectangle(5,5,8,4));
			//setMapWithRect(new Rectangle(15,15,4,4));
			//setMapWithRect(new Rectangle(25,5,4,6));
			//setMapWithRect(new Rectangle(25,15,4,4));
			//setMapWithRect(new Rectangle(30,25,4,5));
			placePoint(1,1,map);
			break;
		}
		case 1:{
			//ʹ�÷����������ͼ ��·�Ƚ�����
			placePoint2(1,1,map);
			break;
		}
		case 2:{
			//ʹ�÷���һ�����ͼ ��һ��������·
			//��������������С�͵�ͼ
			setMapWithRect(new Rectangle(5,5,8,4));
			setMapWithRect(new Rectangle(15,15,4,4));
			setMapWithRect(new Rectangle(25,5,4,6));
			setMapWithRect(new Rectangle(25,15,4,4));
			setMapWithRect(new Rectangle(30,25,4,5));
			placePoint(1,1,map);
			break;
		}
		default:
			break;
		}
		
		
		
		
		
		/*
		//ʹ�÷����������ͼ ��·
		for(int i=0;i<map_width;i++){//��ʼ��ͼ
			for(int j=0;j<map_height;j++){
				map[i][j] = 1;
			}
		}
		placePoint3(new Rectangle(1,1,map_width-2,map_height-2));
		*/
	}
	public void setMapWithRect(Rectangle rect){
		if(rect.x +rect.width > map_width-2 || rect.y+rect.height > map_height-2){
			return;
		}
		for(int i=0;i<rect.width;i++){
			for(int j=0;j<rect.height;j++){
				map[rect.x+i][rect.y+j]=0;
			}
		}
	}
	//���ɵ�ͼ������
	//m*n ���ָ� ��ͼ rect
	public void placePoint3(Rectangle rect){
		if(rect.width==1 || rect.height==1){
			for(int i=0;i<rect.width;i++){
				for(int j=0;j<rect.height;j++){
					map[rect.x+i][rect.y+j]=0;
				}
			}
		}else{
			int x1=rect.x;
			int y1=rect.y;
	
			Random random = new Random();
			int k1 = random.nextInt(rect.width-1)+1;
			int k2 = random.nextInt(rect.height-1)+1;

			System.out.println("m x n " +k1+"  "+k2);

			placePoint3(new Rectangle(x1,	y1,		k1,				k2));//��һ��
			placePoint3(new Rectangle(x1,	y1+k2,	k1,				rect.height-k2));//������
			placePoint3(new Rectangle(x1+k1,y1,		rect.width-k1,	k2));//�ڶ���
			placePoint3(new Rectangle(x1+k1,y1+k2,	rect.width-k1,	rect.height-k2));//���Ŀ�
			
		}
		
	}
	
	public boolean checkSread2( int x,int y){
		int[] x_ff = {-1,1,0,0};//���� ����
		int[] y_ff = {0,0,-1,1};
		int count = 0;
		for(int i=0;i<4;i++){
			int tmpX = x+x_ff[i];
			int tmpY = y+y_ff[i];
			if(checkInBorder(tmpX,tmpY)){//ûԽ�� 
				if(map[tmpX][tmpY]==1){
					count++;
				}
			}
		}
		if(count>0){
			return true;
		}
		return false;
	}
	//���ɵ�ͼ������
	public void placePoint2(int x,int y , int[][] arr){
		if(checkSread2(x,y)){
			//System.out.println(x+" push    "+y);
			spreadPointStack.push(new Point(x,y));
		}
		map[x][y]=0;
		while(!spreadPointStack.isEmpty()){
			//System.out.println(x+" while    "+y);
			int x1= spreadPointStack.peek().x;
			int y1 = spreadPointStack.peek().y;
			System.out.println(x1+" current    "+y1);
			boolean canSpread = false;
			//Point tmpPoint;
			//int[][] tmp_map;
			int[] x_ff = {-1,1,0,0};//���� ����
			int[] y_ff = {0,0,-1,1};
			int k =0;
			int a[] = {0,0,0,0};
			int count =0;
			//System.out.println(i+" i,j    "+j);
			while(count <4){//�������
				Random random = new Random();
				k =random.nextInt(4);
				if(a[k]==0){
					a[k] = 1;
					count++;
					if(checkInBorder(x1+x_ff[k],y1+y_ff[k])){//ûԽ�� 
						if(map[x1+x_ff[k]][y1+y_ff[k]]==1){
							count = 4;//���һ��������������������ѭ��
							//pointStack.push(new Point(x+x_ff[k],y+y_ff[k]));
							System.out.println("spread  "+(x1+x_ff[k])+"  "+(y1+y_ff[k]));
							canSpread = true;
							
						}
						
					}
				}
			}
			if(canSpread){
				//System.out.println("spread");
				//Point tmp_point = pointStack.peek();
				//tmpPoint = new Point(x1+x_ff[k]*2,y1+y_ff[k]*2);
				if(map[x1+x_ff[k]*2][y1+y_ff[k]*2]==2)
				{
					if(checkSread2(x1+x_ff[k]*2,y1+y_ff[k]*2)){
						System.out.println("push");
						spreadPointStack.push(new Point(x1+x_ff[k]*2,y1+y_ff[k]*2));
					}
					map= updateMapData2(map,x1+x_ff[k]*2,y1+y_ff[k]*2,0);
					map= updateMapData2(map,x1+x_ff[k],y1+y_ff[k],0);
					
				}else{
					map=updateMapData2(map,x1+x_ff[k],y1+y_ff[k],3);
					System.out.println("ppp");
				}
			}else{
				System.out.println("pop");
				spreadPointStack.pop();
			}
		}
	}
	//���ɵ�ͼ����һ
	public void placePoint(int x,int y , int[][] arr){
		//Stack<Point> pointStack = new Stack<Point>();
		boolean canSpread = false;
		Point tmpPoint;
		int[][] tmp_map;
		int[] x_ff = {-2,2,0,0};//���� ����
		int[] y_ff = {0,0,-2,2};
		int k =0;
		int a[] = {0,0,0,0};
		int count =0;
		//System.out.println(i+" i,j    "+j);
		while(count <4){//�������
			Random random = new Random();
			k =random.nextInt(4);
			if(a[k]==0){
				a[k] = 1;
				count++;
				if(checkInBorder(x+x_ff[k],y+y_ff[k])){//ûԽ�� 
					if(arr[x+x_ff[k]][y+y_ff[k]]==2){
						count = 4;//���һ��������������������ѭ��
						//pointStack.push(new Point(x+x_ff[k],y+y_ff[k]));
						System.out.println("spread  "+(x+x_ff[k])+"  "+(y+y_ff[k]));
						canSpread = true;
						
					}
					
				}
			}
		}
		if(canSpread){
			//System.out.println("spread");
			//Point tmp_point = pointStack.peek();
			tmpPoint = new Point(x+x_ff[k],y+y_ff[k]);
			
			tmp_map= updateMapData(arr,x+x_ff[k]/2,y+y_ff[k]/2);
			tmp_map= updateMapData(tmp_map,x+x_ff[k],y+y_ff[k]);
			
			pathStack.push(new Point(x,y));
			System.out.println("pathstack add ");
			placePoint(tmpPoint.x,tmpPoint.y,tmp_map);
			//pointStack.pop();
		}else{
			if(pathStack.isEmpty()){
				System.out.println("jiesu");
				for(int i1=0;i1<map_width;i1++){
					for(int j1=0;j1<map_height;j1++){
						map[i1][j1] =  arr[i1][j1];
					}
				}
								
			}else{
				//System.out.println("back ");
				Point tmp_point = pathStack.peek();
				System.out.println("back "+pathStack.peek().x+"x y "+pathStack.peek().y);
				pathStack.pop();
				placePoint(tmp_point.x,tmp_point.y,arr);
				//pathStack.pop();

			}
			
		}
		
		
	}
	public void searchPoint(int i,int j,int[][] arr){
		if(i==map_width-2 && j==map_height-2){
			isMapCreate = true;
			for(int i1=0;i1<map_width;i1++){
				for(int j1=0;j1<map_height;j1++){
					map[i1][j1] =  arr[i1][j1];
				}
			}
			map[map_width-2][map_height-2] =0;
		}
		if(!isMapCreate){
			Stack<Point> pointStack = new Stack<Point>();
			int[] x_ff = {-1,1,0,0};//���� ����
			int[] y_ff = {0,0,-1,1};
			if(checkInBorder(i,j)){//��Ե
				int k =0;
				int a[] = {0,0,0,0};
				int count =0;
				//System.out.println(i+" i,j    "+j);
				while(count <4){//�������
					Random random = new Random();
					k =random.nextInt(4);
					if(a[k]==0){
						a[k] = 1;
						count++;
						if(checkInBorder(i+x_ff[k],j+y_ff[k]) && arr[i+x_ff[k]][j+y_ff[k]]!=0){//ûԽ�� ������
							pointStack.push(new Point(i+x_ff[k],j+y_ff[k]));
							//System.out.println((i+x_ff[k])+"    "+(j+y_ff[k]));
						}
						//System.out.println(count);
					}
				}
				while(!pointStack.isEmpty()){
					int x1= pointStack.peek().x;
					int y1= pointStack.peek().y;
					//map[x1][y1] =0;
					int[][] tmp_map= updateMapData(arr,i,j);
					searchPoint(x1,y1,tmp_map);
					pointStack.pop();
				}
			};
		}
		
		
	}
	public boolean checkInBorder(int i,int j){
		if(i<1|| i>map_width-2 || j<1 || j > map_height-2){
			return false;
		}
		return true;
	}
	public int[][] updateMapData(int[][]map,int x,int y,int direction){
		int[][] tmp_array = new int[map_width][map_height];
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				tmp_array[i][j] =  map[i][j];
			}
		}
		tmp_array[x][y]=2;
		return tmp_array;
	}
	public int[][] updateMapData2(int[][]tmp_map,int x,int y,int value){
		//System.out.println("updataMap"+x+"  "+y);
		int[][] tmp_array = new int[map_width][map_height];
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				tmp_array[i][j] =  tmp_map[i][j];
			}
		}
		tmp_array[x][y]=value;
		return tmp_array;
	}
	public int[][] updateMapData(int[][]tmp_map,int x,int y){
		System.out.println("updataMap"+x+"  "+y);
		int[][] tmp_array = new int[map_width][map_height];
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				tmp_array[i][j] =  tmp_map[i][j];
			}
		}
		tmp_array[x][y]=0;
		return tmp_array;
	}
	public void setForm(){
		Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
		int x = (screenSize.width-cute_width*(map_width+2))/2;
		int y = 10;
		System.out.println("setFrame");
		//setUndecorated(true);
		setBounds(x,y,cute_width*(map_width+2),cute_width*(map_height+3));
		//setOpacity((float) 0.8);
		//this.setBackground(new Color(0,0,0,0));
		setVisible(true);
		validate();
		setTitle("�Թ�");
		this.addKeyListener(this);
		this.addMouseListener(this);
		addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        } );	
		
	}
	public void refleshView(){
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				
			}
		}
	}
	public void paint(Graphics g){
		//@Override
		//super.paint(g);
		//Graphics2D g2 = (Graphics2D) g; 
		//ImageIcon ii1 = new ImageIcon("E:\\pic\\ͼƬ\\betterfly.png");
		//g2.drawImage(ii1.getImage(), 0, 0, this.getWidth() ,this.getHeight(),null);
		BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
		Graphics big =bi.getGraphics();
		
		//big.drawImage(e.img, e.x, e.y, e.width, e.height,null);  
		Color c =big.getColor(); 
		//����ͼ
		for(int i=0;i<map_width;i++){
			for(int j=0;j<map_height;j++){
				big.setColor(Color.white);
				if(map[i][j]==1){
					big.setColor(Color.black);
				}
				if( map[i][j]==3){
					big.setColor(Color.black);
				}
				if(map[i][j]==2){
					big.setColor(Color.yellow);
				}
				//big.fillOval(10+i*cute_width, 30+j*cute_width, cute_width, cute_width);
				big.fillRect(10+i*cute_width, 30+j*cute_width, cute_width, cute_width);
				big.setColor(c);
			}
		}
		//��ʼλ��
		big.setColor(Color.red);
		big.fillOval(10+current_x *cute_width, 30+current_y*cute_width, cute_width, cute_width);  
		
		//�յ�λ��
		big.setColor(Color.green);
		big.fillOval(10+(map_width-2)*cute_width, 30+(map_height-1)*cute_width, cute_width, cute_width);  
		big.setColor(c);
		
		g.drawImage(bi,0,0,null); 
		
	}
	public void move(int direction){
		//direction 0�� 1��  2 �� 3��
				int a[]=new int[]{-1,1,0,0};
				int b[]=new int[]{0,0,1,-1};
		if(!checkInBorder(this.current_x +a[direction],current_y +b[direction])){
			//����Ƿ�Խ��
			return;
		}
		if(map[this.current_x +a[direction]][current_y +b[direction]]==1 || map[this.current_x +a[direction]][current_y +b[direction]]==3){
			return;
		}
		current_x = current_x +a[direction];
		current_y = current_y +b[direction];
	}
	public void keyPressed(KeyEvent e) {  
        //text.append("����" + KeyEvent.getKeyText(e.getKeyCode()) + "������\n"); 
		System.out.println(KeyEvent.getKeyText(e.getKeyCode()));
		switch(KeyEvent.getKeyText(e.getKeyCode())){
		case "A":{
			//System.out.println("a press");
			move(0);
			break;
		}
	
		case "D":
			move(1);
			break;
		case "S":{
			move(2);
		}
	
		break;
		case "�ո�":
			break;
		case "Enter":
		{
			resetMap();
		break;
		}
		case "W":
		{
			move(3);
		}
		break;
		default:
			break;
		}
		this.repaint();
	}
	  
	public void keyReleased(KeyEvent e) {  
	     //  text.append("����" + KeyEvent.getKeyText(e.getKeyCode()) + "���ɿ�\n");  
	}  
	 
	public void keyTyped(KeyEvent e) {  
	       // text.append("�����������" + e.getKeyChar() + "\n");  
	}  
	public void mousePressed(MouseEvent e){} 	 //��갴��
	public void mouseReleased(MouseEvent e){	 //����ɿ� 
		System.out.println("�뿪");
	}
	public void mouseEntered(MouseEvent e){} //�����루ĳ�������
	public void mouseExited(MouseEvent e){}  //  ����뿪��ĳ�������
	public void mouseClicked(MouseEvent e){  // �����������˫��
		 if(e.isMetaDown()){//�һ�
			 System.out.println("�һ�");
			 Object[] possibleValues = { "�㷨һ", "�㷨��", "�㷨��" }; 
			 Object selectedValue = JOptionPane.showInputDialog(this, "ѡ�����ɵ�ͼ�㷨", "Input", 
			 JOptionPane.INFORMATION_MESSAGE, null, 
			 possibleValues, possibleValues[0]); 
			 //JOptionPane.setDefaultLocale(new Locale(this.getWidth()/2,this.getHeight()/2));
			 
			 if(selectedValue.equals("�㷨һ")){
				 System.out.println("�㷨һ");
				 currentAth=0;
				 resetMap();
				 this.repaint();
			 }
			 if(selectedValue.equals("�㷨��")){
				 System.out.println("�㷨��");
				 currentAth=1;
				 resetMap();
				 this.repaint();
			 }

			 
		 }

	}
}
