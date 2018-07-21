package pk.ui;

import java.awt.*;

import javax.swing.*;

import pk.manager.PukeManager;
import pk.model.PukeListTypeEnum;
import pk.model.PukeModel;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;


public class GameFrame extends JFrame implements ActionListener{
	
	ArrayList<PukeModel> pk_list = null;
	ArrayList<CardPanel> card_list = new ArrayList<CardPanel>(); //玩家一牌面数组
	ArrayList<CardPanel> play_list = new ArrayList<CardPanel>(); //出牌数组
	public GameFrame(){
		initFrame();
		for (int i = 0; i <2*54; i++) {
			CardPanel cardPanel=new CardPanel();
			card_list.add(cardPanel);
		}
		//repaint();
		reSetView();
	}
//	
	public void initFrame() {
		this.getLayeredPane().setLayout(null);
		this.setTitle("pk");
		this.setSize(800 , 500);
		this.setLocation(400, 50);
		this.setResizable(false);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.addKeyListener(this);
		this.addWindowListener(new WindowAdapter() {  
		public void windowClosing(WindowEvent arg0) {  
			System.exit(1);//  
		}  
	    }); 
	}
	public void reSetView() {
//		重新洗牌前将所有的卡牌归位，防止出现重影
		for (int i = 0; i < card_list.size(); i++) {
			CardPanel tmPanel=card_list.get(i);
			//tmPanel.setLocation(tmPanel.location().x,360);
		}
		this.getLayeredPane().removeAll();
		JButton jb1=new JButton("发牌");
		jb1.setSize(100, 50);
		jb1.setLocation(50, 400);
        jb1.addActionListener(this);
        
        JButton jb2=new JButton("出牌");
		jb2.setSize(100, 50);
		jb2.setLocation(680, 400);
        jb2.addActionListener(this);
		
		
        
        if (pk_list != null) {
        	for (int i = 0; i < pk_list.size(); i++) {
            	PukeModel pukeModel = pk_list.get(i);
            	Image img = new ImageIcon("src/Image/"+pukeModel.tag+".jpg").getImage();
//            	卡牌重新赋值
            	CardPanel cardPanel=card_list.get(i);
//    			cardPanel = new CardPanel(img);//;
    			cardPanel.pukeModel = pukeModel;
    			cardPanel.tag = i;
    			cardPanel.callBack = new CardPanel.SelectCallBack() {
					
					@Override
					public void onResponse(int tag, boolean state) {
						// TODO Auto-generated method stub
						cardStateChange(tag,state);
					}

					
				};
    			cardPanel.setImage(img);
    			//cardPanel.setSelected(false);
    			if (cardPanel.getSelected() == false) {
    				cardPanel.setLocation(200+i*20, 320);
				}else {
					cardPanel.setLocation(200+i*20, 270);
				}
    			
    			cardPanel.setSize(105, 150);
    			
    			cardPanel.repaint();
    			this.getLayeredPane().add(cardPanel,new Integer(i));

    		}
		}
        
        this.getLayeredPane().add(jb1);
        this.getLayeredPane().add(jb2);
        
        
	}
	
	protected void cardStateChange(int tag, boolean state) {
		// TODO Auto-generated method stub
		
		//int i = card.tag;
		System.out.println("tag"+tag);
		//card_list.get(tag).setSelected(state);
		reSetView();
		
		
	}
	/*public void paint(Graphics g) { 
		
		BufferedImage bi =(BufferedImage)this.createImage(this.getSize().width,this.getSize().height);  
        Graphics big =bi.getGraphics(); 
        
        int i = 0;
        PukeManager pManager=new PukeManager();
        ArrayList<PukeModel> list = (ArrayList<PukeModel>) pManager.getCardList(pManager.dealCard(1));
        pManager.sortCard(list);
        for (Iterator iterator = list.iterator(); iterator.hasNext();) {
			PukeModel pukeModel = (PukeModel) iterator.next();
			Image img = new ImageIcon("src/Image/"+pukeModel.tag+".jpg").getImage(); 
			//big.drawImage(img, 200+i*20, 320, 80, 100,null);
			//System.out.println(pukeModel.number+pukeModel.type.toString());
			i++;
		}
        
        g.drawImage(bi,0,0,null);  
	}*/
/*
 * 重新发牌
*/	
	public void reDealCard() {
		if (pk_list!=null && pk_list.size()>0) {
			pk_list.clear();
		}
		PukeManager pManager=new PukeManager();
		pk_list = (ArrayList<PukeModel>) pManager.getCardList(Arrays.copyOfRange(pManager.dealCard(1), 0, 17) );
		pManager.sortCard(pk_list);
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="发牌")
        { 
			reDealCard();
			reSetView();
        }
        if(e.getActionCommand()=="出牌")
        { 
        	ArrayList<PukeModel> tmpSelectPukeModelList = new ArrayList<PukeModel>(); //出牌数组
        	
        	int k=0;
			for (int i = 0; i < card_list.size(); i++) {
				CardPanel tmPanel= card_list.get(i);
				if (tmPanel.getSelected()) {
					tmpSelectPukeModelList.add(tmPanel.pukeModel);
					play_list.add(tmPanel);
//					int x= 200+k*20;
//					int y = 100;
//					tmPanel.setLocation(x, y);
//					k++;
				}
			}
			if (tmpSelectPukeModelList.size()>0) {
				PukeManager pManager= new PukeManager();
				System.out.println(pManager.getCardListInfo(tmpSelectPukeModelList).type);
				if (pManager.getCardListInfo(tmpSelectPukeModelList).type != PukeListTypeEnum.mix) {
					k=0;
					for (int i = 0; i < card_list.size(); i++) {
						CardPanel tmPanel= card_list.get(i);
						if (tmPanel.getSelected()) {
							int x= 200+k*20;
							int y = 100;
							tmPanel.setLocation(x, y);
							k++;
							tmPanel.setSelected(false);
							card_list.remove(tmPanel);
							reSetView();
						}
						
					}
				}
			}
        }
	}

}
