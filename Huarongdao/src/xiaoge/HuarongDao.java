package xiaoge;
import java.awt.*;

import javax.swing.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import java.util.Stack;

public class HuarongDao extends JFrame implements MouseListener,KeyListener,ActionListener{

	Person person[]= new Person[10];
	int map_data[][];
	MenuBar menubar;
	Menu file;
	MenuItem reset,select,exit;
	Stack<Point[]> resultStack = new Stack<Point[]>();
	Point[] placeArray = new Point[10];
	Stack<int[][]> maps = new Stack<int[][]>();
	
	public HuarongDao(){
		setFrame();
		//readByBufferedReader();
		init();
		/*
		int[][] tmp_array = new int[4][5];
		for(int i=0;i<4;i++){
			for(int j=0;j<4;j++){
				tmp_array[i][j] = 0;
			}
		}
		placePersonOnMap("曹操",tmp_array,0);
		*/
		//setFrame();
		//init();
	}
	public void setFrame(){
		//setUndecorated(true);
		setBounds(400,100,337,460);
		//setOpacity((float) 0.8);
		//this.setBackground(new Color(0,0,0,0));
		setVisible(true);
		validate();
		setTitle("华容道");
		addWindowListener( new WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
                System.exit(0);
            }
        } );	
		menubar = new MenuBar();
		file = new Menu("file");
		reset = new MenuItem("reset");
		reset.addActionListener(this);
		
		select = new MenuItem("select Map");
		select.addActionListener(this);
		
		exit = new MenuItem("exit");
		exit.addActionListener(this);
		
		file.add(reset);
		file.add(select);
		file.add(exit);
		menubar.add(file);
		
		
		setMenuBar(menubar);
		
		JButton closeBtn = new JButton("X");
		closeBtn.setBounds(300, -20, 40, 20);
		add(closeBtn);
	}

	public void init(){
		setLayout(null);
		setMapData();
		String name[] = {"曹操","关羽","张飞","刘备","赵云","黄忠","兵","兵","兵","兵"};
		int a[][]= new int[][]{{1,0},{1,2},{0,0},{0,2},{3,0},{3,2},{1,3},{2,3},{0,4},{3,4},};
		for(int i=0;i<10;i++){
			person[i]= new Person(name[i]);
			person[i].addMouseListener(this);
			person[i].addKeyListener(this);
			person[i].setPersonLocation(map_data[i][0], map_data[i][1]);
			add(person[i]);
		}
		/*
		person[0].setPersonLocation(1, 0);
		person[1].setPersonLocation(1, 2);
		person[2].setPersonLocation(0, 0);
		person[3].setPersonLocation(0, 2);
		person[4].setPersonLocation(3, 0);
		person[5].setPersonLocation(3, 2);
		person[6].setPersonLocation(1, 3);
		person[7].setPersonLocation(2, 3);
		person[8].setPersonLocation(0, 4);
		person[9].setPersonLocation(3, 4);
		*/
		validate();
	}
	public void setMapData(){
		//String name[] = {"曹操","关羽","张飞","刘备","赵云","黄忠","兵","兵","兵","兵"};
		int i=1;
		map_data = null;
		switch(i){
		case 1:
			map_data= new int[][]{{1,0},{1,2},{0,0},{0,2},{3,0},{3,2},{1,3},{2,3},{0,4},{3,4}};
			break;
		case 2:
			map_data= new int[][]{{1,0},{1,2},{0,0},{0,3},{3,0},{3,3},{1,3},{2,3},{0,2},{3,2}};
			break;
		case 3:
			map_data= new int[][]{{1,0},{1,4},{0,2},{1,2},{2,2},{3,2},{0,0},{0,1},{3,0},{3,1}};
			break;
		case 4:
			map_data= new int[][]{{0,0},{0,2},{1,3},{2,0},{3,0},{3,2},{0,4},{2,2},{0,3},{3,4}};
			break;
		}
	}
	public void movePerson(Person man,int direction){
		//direction 0 左,1右,2上,3下
		int a[]=new int[]{-1,1,0,0};
		int b[]=new int[]{0,0,-1,1};
		int current_x = man.location_x;
		int current_y = man.location_y;
		int tmp_x= man.location_x+a[direction];
		int tmp_y= man.location_y+b[direction];
		if(direction==3 || direction == 1){
			//下移动和右移动要考虑到边长
			tmp_x= man.location_x+a[direction]*man.person_width;
			tmp_y= man.location_y+b[direction]*man.person_height;
		}
		if(tmp_x<0 || tmp_x>3 || tmp_y<0 || tmp_y>4){
			//越界
			System.out.println("越界");
			return;
		}
		tmp_x= man.location_x+a[direction];
		tmp_y= man.location_y+b[direction];
		Rectangle manRect = man.getBounds();
	    manRect.setLocation(tmp_x*man.lenth, tmp_y*man.lenth);
		for(int i=0;i<10;i++){
			Rectangle personRect = person[i].getBounds();
			if(!(current_x == person[i].location_x && current_y==person[i].location_y)){
				if(manRect.intersects(personRect)){
					//碰撞
					System.out.println("碰撞");
					return;
				}
				
			}
			
		}
		man.setPersonLocation(tmp_x,tmp_y);			
		
		
	}
		

	public void keyTyped(KeyEvent e){}
	 public void keyReleased(KeyEvent e){}
	 public void keyPressed(KeyEvent e)
	 {
		 Person man = (Person)e.getSource();
		 if(e.getKeyCode()==KeyEvent.VK_A){ 
			 movePerson(man,0);
		 }
		 if(e.getKeyCode()==KeyEvent.VK_D){ 
			 movePerson(man,1);
		 }
		 if(e.getKeyCode()==KeyEvent.VK_W){ 
			 movePerson(man,2);
		 }
		 if(e.getKeyCode()==KeyEvent.VK_S){ 
			 movePerson(man,3);
		 }
		  
		 
	 }
	 public void mousePressed(MouseEvent e)
	 {
		 Person man = (Person)e.getSource();
		  int x = -1,y = -1;
		  x = e.getX();
		  y = e.getY();
		  int w = man.getBounds().width;
		  int h = man.getBounds().height;
		  if(Math.abs(y-h/2)>Math.abs(x-w/2)){
			  if(y>h/2)
			  {
				  movePerson(man,3);
			  }
			  if(y<h/2)
			  {
				  movePerson(man,2);
			  }
		  }
		  else{
			  if(x<w/2)
			  {
				  movePerson(man,0);
			  }
			  if(x>w/2)
			  {
				  movePerson(man,1);
			  }
		  }
		 
	 }
	 public void mouseReleased(MouseEvent e){}
	 public void mouseEntered(MouseEvent e){}
	 public void mouseExited(MouseEvent e){}
	 public void mouseClicked(MouseEvent e){}
	 
	 public void actionPerformed(ActionEvent e)
	 {
		 String title = e.getActionCommand();
		 switch (title){
		 case "reset":
		 {
			 dispose();
			 new HuarongDao();
		 }
		 break;
		 case "select Map":
		 {
			 System.out.println("select view");
			 add(new MapSelectionScrollView());
		 }
		 break;
		 case "exit":
		 {
			 System.out.println("exit");
			 System.exit(0);
		 }
		 break;
		 default:
			 break;
		 }
		 
	 }
	void setMap(){
		
	}
	void placePersonOnMap(String typeString,int arr[][],int index){
		String name[] = {"曹操","关羽","张飞","刘备","赵云","黄忠","兵","兵","兵","兵"};
		Stack<Point> placeStack = new Stack<Point>();
		
		
		int width = 0;
		int height = 0;
		
		switch(typeString){
		case "曹操":
		{
			width = 2;
			height = 2;
		}
			break;
		case "关羽":
		{
			width = 2;
			height = 1;
		}
			break;
		case "张飞":
		case "刘备":
		case "赵云":
		case "黄忠":
		{
			width = 1;
			height = 2;
		}
			break;
		case "兵":
		{
			width = 1;
			height = 1;
		}
			break;
			default :
				break;
			
		}
		for(int i=0;i<4;i++){
			for(int j=0;j<5;j++){
				//检查是否能够安放 如果能 递归安放下一个人物
				if(checkCanPlace(i,j,width,height,arr)){
					//placeArr.
					Point tmp_point = new Point(i,j);
					if(index != 9){
						placeStack.push(tmp_point);
					}
					placeArray[index]=tmp_point;
					int[][] tmp_map= updateMapData(arr,i,j,width,height);
					if(index ==9){
						//将生成的某种满足条件的地图加入到结果队列中
						resultStack.push(placeArray);
						String result = "";
						for(int k=0;k<10;k++){
							result = result+""+placeArray[k].x+"," +placeArray[k].y+";";
						}
						writeByBufferedReader(result+":");
						System.out.println(result);
					}
					if(!placeStack.isEmpty() && index !=9){
						placeStack.pop();
						placePersonOnMap(name[index+1],tmp_map,index+1);
						
					}
				}
			}
			
		}
	}
	public boolean checkCanPlace(int begin_x,int begin_y,int width,int height,int arr[][]){
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				if((begin_x+i > 3) || (begin_y+j)>4){
					return false ;
				}
				if(arr[begin_x+i][begin_y+j] == 1){
					return false ;
				}	
				
			}
		}
		return true;
	}
	public int[][] updateMapData(int[][]map,int x,int y,int width,int height){
		int[][] tmp_array = new int[4][5];
		for(int i=0;i<4;i++){
			for(int j=0;j<5;j++){
				tmp_array[i][j] =  map[i][j];
			}
		}
		for(int i=0;i<width;i++){
			for(int j=0;j<height;j++){
				tmp_array[x+i][y+j] =1;	
			}
		}
		tmp_array[x][y]=1;
		return tmp_array;
	}
	public  void writeByBufferedReader(String content) {  
		try {  
            File file = new File("/D:/workplace/Huarongdao/map.txt");  
            // if file doesnt exists, then create it  
            if (!file.exists()) {  
                file.createNewFile();  
            }  
            FileWriter fw = new FileWriter(file, true);  
            BufferedWriter bw = new BufferedWriter(fw);  
            bw.write(content);  
            bw.flush();  
            bw.close();  
  
        } catch (IOException e) {  
            e.printStackTrace();  
        }  
    }
	public  void readByBufferedReader() {  
		try {  
            File file = new File("/D:/workplace/Huarongdao/map.txt");  
            // 读取文件，并且以utf-8的形式写出去  
            BufferedReader bufread;  
            String read;  
            bufread = new BufferedReader(new FileReader(file));  
            while ((read = bufread.readLine()) != null) {  
                System.out.println(read);  
                getMapList(read);
            }  
            bufread.close();  
        } catch (FileNotFoundException ex) {  
       ex.printStackTrace();  
       } catch (IOException ex) {  
           ex.printStackTrace();  
        }   
	} 
	public  void getMapList(String str){
		String a1[]= str.split(":");
		
		for(int i=0;i<a1.length;i++){
			System.out.println("a1   "+ a1[i]);
			String a2[] = a1[i].split(";");
			int[][]map=new int[10][2];
			for(int j=0;j<a2.length;j++){
				map[j][0]=0;
				map[j][1]=0;
				System.out.println("a2   "+ a2[j]);
				String a3[]=a2[j].split(",");
				System.out.println(j +"   ");
				System.out.println("a30   "+ a3[0]);
				System.out.println("a31   "+ a3[1]);
				map[j][0]=Integer.parseInt(a3[0]);
				map[j][1]=Integer.parseInt(a3[1]);
			}
			maps.push(map);
		}
		
	}

		        
}
