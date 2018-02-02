package Yj.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import Yj.action.BaguaAction;
import Yj.model.LiuSiGuaModel;
import Yj.model.YaoModel;
import Yj.util.CommonUtil;


public class CeSuanView extends JFrame implements ActionListener {
	JFrame frame = new JFrame();
	public Container container  = frame.getContentPane();
	List<JTextField> textFieldsList  = new ArrayList<JTextField>();
	YaoModel curYao = new YaoModel();			//测算出来的当前爻
	LiuSiGuaModel curGua = new LiuSiGuaModel();	//测算出来的本卦
	int curCount=0;								//当前策算次数
	JTextField textSg;
	JTextField textXg;
	JTextField textBenGua;
	JButton jb1;
	List<YaoModel> yaoList  = new ArrayList<YaoModel>();
	
	private String[] guaTypes = {"本卦","错卦","踪卦","互卦","变卦"};

	List<LiuSiGuaImagePanel> guaPl  = new ArrayList<LiuSiGuaImagePanel>();


	public  CeSuanView() {
		setFrame();
		setUI();
	}
	public void setFrame(){

		System.out.println("setFrame");
		container.setLayout(null);
		frame.setTitle("六爻策算");
		//container.setBackground(Color.orange);
		frame.setSize(500, 500);
		frame.setLocation(400, 50);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); 
		frame.addWindowListener(new WindowAdapter() {
        	public void windowClosing(WindowEvent e) {
        		curCount = 0;
				frame.dispose();
			}
		});
		//frame.addKeyListener(this);
	}

	public void setUI(){
		//创建组件  
		jb1=new JButton("开始测算");  
		jb1.setSize(100, 50);
		jb1.setLocation(200, 400);
        jb1.addActionListener(this);
        
        JPanel jp1=new JPanel(); 
        jp1.setLocation(20,20);
        jp1.setSize(460, 360);
        jp1.setLayout(null);
        
        //List<JTextField> textFieldsList = new ArrayList<JTextField>();
        String[] titleString ={"第一爻：","第二爻：","第三爻：","第四爻：","第五爻：","第六爻："}; 
        for (int i = 0; i < 6; i++) {
        	JLabel jl = new JLabel(titleString[i]);  
            jl.setLocation(20, 300-i*50);
            jl.setSize(60, 50);
            jp1.add(jl);
		}
        for (int k = 0; k < 6; k++) {
			JTextField text = new JTextField();
			text.setLocation(120, 310-k*50);
            text.setSize(120, 30);
            text.setEditable(false);
            textFieldsList.add(text);

            jp1.add(text);
		} 
        
        textSg = new JTextField();
        textSg.setLocation(260, 310-2*50);
        textSg.setSize(100, 30);
        textSg.setEditable(false);
        jp1.add(textSg);
        
        textXg = new JTextField();
        textXg.setLocation(260, 310-1*50);
        textXg.setSize(100, 30);
        textXg.setEditable(false);
        jp1.add(textXg);
        
        textBenGua = new JTextField();
        textBenGua.setLocation(260, 310-0*50);
        textBenGua.setSize(100, 30);
        textBenGua.setEditable(false);
        jp1.add(textBenGua);
     
        
        for (int kk = 0; kk < 5; kk++) {
        	LiuSiGuaImagePanel guaImg = new LiuSiGuaImagePanel();
            guaImg.setLocation(280+(kk%3)*70,20+(kk/3)*105);
            guaImg.setGuaName("乾");
            guaImg.setGuaType(kk);
            guaImg.setSize(60,100);
       
            guaPl.add(guaImg);
            container.add(guaImg);

		}
        container.add(jb1);
        container.add(jp1);
        container.repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="开始测算")  
        {  
        	try {
        		jb1.setEnabled(false);
        		if (curCount == 0) {
        			for (JTextField txfld : textFieldsList)
        		    {
        		      txfld.setText("");
        		    }
        			textSg.setText("");
        			textXg.setText("");
        			textBenGua.setText("");

				}
        		
        		curCount+=1;
        		newYang(50, 0);
        		System.out.println("curCount:"+curCount);
        		JTextField textField = textFieldsList.get(curCount-1);
        		textField.setText(curYao.getValue()+"  -->"+curYao.getType());	
				
        		if (curCount == 3) {
        			//测算出的下卦
        			String name = CommonUtil.getCommonUtil().getBagua(yaoList.get(0), yaoList.get(1), yaoList.get(2)).getName();
            		textXg.setText(name);

				}
        		if (curCount == 6) {
        			//测算出的上卦
        			String name = CommonUtil.getCommonUtil().getBagua(yaoList.get(3), yaoList.get(4), yaoList.get(5)).getName();
            		textSg.setText(name);
            		//上下卦组成一个64卦
            		LiuSiGuaModel lsgua = CommonUtil.getCommonUtil().getLiuSigua(CommonUtil.getCommonUtil().getBagua(yaoList.get(0), yaoList.get(1), yaoList.get(2)), 
            				CommonUtil.getCommonUtil().getBagua(yaoList.get(3), yaoList.get(4), yaoList.get(5)));
            		
            		BaguaAction action = new BaguaAction();
            		curGua = action.getByGuaXiang(lsgua);
            		//System.out.println(yaoList.get(0).getType()+);
            		System.out.println("bengunname "+curGua.getName());
            		textBenGua.setText(curGua.getName());
            		//设置个个卦图
            		setBenGua();
            		setCuoGua();
            		setZongGua();
            		setHuGua();
            		setBianGua();
            		
					curCount = 0;
					
					//textFieldsList.clear();
					yaoList.clear();
					
				}
        		jb1.setEnabled(true);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
        }else if(e.getActionCommand()=="重新占卦")  
        {  
               new CeSuanView();
        } 
	}

	//易经筹算法：对一个数字进行三次运算
		public  void newYang(int number,int seq){
			Random random = new Random();
			//x 取值范围: 1 -(number-1)
			int x =random.nextInt(number-1)+1;
			
			//分为俩堆
			int tempA = x;
			int tempB = number - x;
			
			//System.out.println("tempA  "+tempA);
			//System.out.println("tempB  "+tempB);
			
			//俩堆分别进行再分堆，每堆4个，将余数去除
			int x1 =(tempA-1)/4;
			int x2 =(tempA-1)%4;
			if (x2 ==0){
				x1 = (x1>0?x1-1:0);
				//x1= x1-1;
			}
			int y1 = tempB/4;
			int y2 = tempB%4;
			if (y2 ==0){
				y1 = (y1>0?y1-1:0);
				//y1= y1-1;
			}
			if(seq<3){//进行三次迭代策算
				
				newYang((x1+y1)*4,seq+1);
				
			}else{
				//生成相应的爻
				curYao = CommonUtil.getCommonUtil().getYaoByValue(x1+y1);
				yaoList.add(curYao);
				
			}
			
		}	
		//本卦
		public void setBenGua() {
			setGuaxiangPanelWithGuatype(yaoList, 0);
		}
		//踪卦
		public void setZongGua() {
			List<YaoModel> yaoArray  = new ArrayList<YaoModel>();
			for (int i = 0; i < 6; i++) {
				YaoModel tmpYao= new YaoModel();
				yaoArray.add(tmpYao);
			}
			yaoArray.set(0, yaoList.get(5));
			yaoArray.set(1, yaoList.get(4));
			yaoArray.set(2, yaoList.get(3));
			yaoArray.set(3, yaoList.get(2));
			yaoArray.set(4, yaoList.get(1));
			yaoArray.set(5, yaoList.get(0));
			setGuaxiangPanelWithGuatype(yaoArray, 2);
			
		}
		//错卦
		public void setCuoGua() {
			List<YaoModel> yaoArray  = new ArrayList<YaoModel>();
			for (int i = 0; i < 6; i++) {
				YaoModel tmpYao= new YaoModel();
				//取反
				tmpYao.setType((yaoList.get(i).getType()+1)%2);
				yaoArray.add(tmpYao);
			}
			/*
			yaoArray.set(0, yaoList.get(5));
			yaoArray.set(1, yaoList.get(4));
			yaoArray.set(2, yaoList.get(3));
			yaoArray.set(3, yaoList.get(2));
			yaoArray.set(4, yaoList.get(1));
			yaoArray.set(5, yaoList.get(0));
			*/
			setGuaxiangPanelWithGuatype(yaoArray, 1);
			
		}
		//互卦
		public void setHuGua() {
			List<YaoModel> yaoArray  = new ArrayList<YaoModel>();
			for (int i = 0; i < 6; i++) {
				YaoModel tmpYao= new YaoModel();
				yaoArray.add(tmpYao);
			}
			yaoArray.set(0, yaoList.get(1));
			yaoArray.set(1, yaoList.get(2));
			yaoArray.set(2, yaoList.get(3));
			yaoArray.set(3, yaoList.get(2));
			yaoArray.set(4, yaoList.get(3));
			yaoArray.set(5, yaoList.get(4));
			setGuaxiangPanelWithGuatype(yaoArray, 3);
			
		}
		//变卦
		public void setBianGua() {//.9为老阳，7为少阳；6为老阴，8为少阴（9、6为可变之爻；7、8为不变之爻）。
			List<YaoModel> yaoArray  = new ArrayList<YaoModel>();
			for (int i = 0; i < 6; i++) {
				YaoModel tmpYao= new YaoModel();
				tmpYao.setType(yaoList.get(i).getType());
				if (yaoList.get(i).getValue() == 9) {//太阳变阴
					tmpYao.setType(1);
				}
				if (yaoList.get(i).getValue() == 6) {//太阴变阳
					tmpYao.setType(0);
				}
				yaoArray.add(tmpYao);
			}
			/*
			yaoArray.set(0, yaoList.get(0));
			yaoArray.set(1, yaoList.get(1));
			yaoArray.set(2, yaoList.get(2));
			yaoArray.set(3, yaoList.get(2));
			yaoArray.set(4, yaoList.get(3));
			yaoArray.set(5, yaoList.get(4));
			*/
			setGuaxiangPanelWithGuatype(yaoArray, 4);
			
		}
				
				
		//根据爻数组生成相应的卦象图
		public void setGuaxiangPanelWithGuatype (List<YaoModel> yaoArray,int type) {
			//上下卦组成一个64卦
    		LiuSiGuaModel lsgua = CommonUtil.getCommonUtil().getLiuSigua(CommonUtil.getCommonUtil().getBagua(yaoArray.get(0), yaoArray.get(1), yaoArray.get(2)), 
    				CommonUtil.getCommonUtil().getBagua(yaoArray.get(3), yaoArray.get(4), yaoArray.get(5)));
    		
    		BaguaAction action = new BaguaAction();
    		try {
				lsgua = action.getByGuaXiang(lsgua);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
    		//System.out.println("setguaxi"+lsgua.getName());;
    		guaPl.get(type).setGuaName(lsgua.getName());
			guaPl.get(type).setYao(yaoArray.get(0).getType(), yaoArray.get(1).getType(), yaoArray.get(2).getType(), yaoArray.get(3).getType(), yaoArray.get(4).getType(), yaoArray.get(5).getType());
		
		}
}
