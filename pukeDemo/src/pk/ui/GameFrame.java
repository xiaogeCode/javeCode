package pk.ui;

import java.awt.*;

import javax.swing.*;

import pk.manager.PukeManager;
import pk.model.CardModel;
import pk.model.PukeListTypeEnum;
import pk.model.PukeModel;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;


public class GameFrame extends JFrame implements ActionListener{
	
	ArrayList<PukeModel> pk_list = null;
    List<CardModel> user1CardList = new ArrayList<CardModel>();
    //出牌数组
    List<CardModel> playCardList = new ArrayList<CardModel>();
	public GameFrame(){
		initFrame();
		resetview();
	}
//	
	public void initFrame() {
//		this.getLayeredPane().setLayout(null);
		this.setLayout(null);
		this.setTitle("pk");
		this.setSize(800 , 500);
		this.setLocation(400, 50);
		this.setResizable(false);
		
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//this.addKeyListener(this);
		this.addWindowListener(new WindowAdapter() {  
		@Override
        public void windowClosing(WindowEvent arg0) {
			System.exit(1);//
		}  
	    }); 
	}
    /**
     * 功能描述: <br>
     * 〈重绘界面〉
       参数         []
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/3 19:46
     */
    public void resetview(){
        this.getContentPane().removeAll();
        showMenu();
        showCardListPanel();
        showDealCardListPanel();
    }
    /**
     * 功能描述: <br>
     * 〈显示手上的牌 界面〉
       参数         []
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/3 19:46
     */
	public void showCardListPanel(){
        System.out.println("showcardlist");
        CardListPanel cardListPanel = new CardListPanel();
        cardListPanel.setLocation(200,200);
        cardListPanel.setSize(500,200);
        if (user1CardList.size()>0) {
            for (int i = 0; i < user1CardList.size(); i++) {
                // 卡牌重新赋值
                CardPanel cardPanel=new CardPanel();

                cardPanel.setCardInfo(user1CardList.get(i));
                cardPanel.setLoaction_index(i);

                cardPanel.setLocation(i*20, 50);
                cardPanel.setSize(105, 150);

                cardListPanel.getPanelList().add(cardPanel);

            }
        }

        cardListPanel.showPanel();

        this.add(cardListPanel);
        this.getContentPane().repaint();
	}
	/**
	 * 功能描述: <br>
	 * 〈显示出牌界面〉
	   参数         []
	 * 返回 @return:void
	 * 作者 @Author:xiaoge
	 * 时间 @Date: 2018/8/3 19:46
	 */
    public void showDealCardListPanel(){
        System.out.println("showDealCardListPanel");

        CardListPanel cardListPanel = new CardListPanel();
        cardListPanel.setLocation(200,50);
        cardListPanel.setSize(500,200);
        if (playCardList.size()>0) {
            for (int i = 0; i < playCardList.size(); i++) {
                // 卡牌重新赋值
                CardPanel cardPanel=new CardPanel();

                cardPanel.setCardInfo(playCardList.get(i));
                cardPanel.setLoaction_index(i);

                cardPanel.setLocation(i*20, 50);
                cardPanel.setSize(105, 150);

                cardListPanel.getPanelList().add(cardPanel);

            }
        }

        cardListPanel.showPanel();

        this.add(cardListPanel);
        this.getContentPane().repaint();
    }

	public void showMenu(){
        JButton jb1=new JButton("发牌");
        jb1.setSize(100, 50);
        jb1.setLocation(50, 400);
        jb1.addActionListener(this);

        JButton jb2=new JButton("出牌");
        jb2.setSize(100, 50);
        jb2.setLocation(680, 400);
        jb2.addActionListener(this);

        this.add(jb1);
        this.add(jb2);
        this.getContentPane().repaint();
    }
/**
 * 功能描述: <br>
 * 〈发牌，生成一定数量的牌〉
   参数         []
 * 返回 @return:void
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/3 19:47
 */
	public void reDealCard() {
		if (pk_list!=null && pk_list.size()>0) {
			pk_list.clear();
		}
		PukeManager pManager=new PukeManager();
		pk_list = (ArrayList<PukeModel>) pManager.getCardList(Arrays.copyOfRange(pManager.dealCard(1), 0, 17) );
		pManager.sortCard(pk_list);
		
	}
	/**
	 * 功能描述: <br>
	 * 〈将生成的牌转换为卡牌模型〉
	   参数         []
	 * 返回 @return:void
	 * 作者 @Author:xiaoge
	 * 时间 @Date: 2018/8/3 19:48
	 */
    private void getUser1CardInfo() {
	    if (user1CardList.size()>0){
	        user1CardList.clear();
        }
        for (PukeModel pkmodel:pk_list) {
            CardModel cardInfo = new CardModel();
            cardInfo.pukeInfo = pkmodel;
            cardInfo.selected  = false;
            user1CardList.add(cardInfo);
        }
    }
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand()=="发牌")
        { 
			reDealCard();
			getUser1CardInfo();
            resetview();
        }
        if(e.getActionCommand()=="出牌")
        {
        	ArrayList<PukeModel> tmpSelectPukeModelList = new ArrayList<PukeModel>(); //出牌数组
        	if (playCardList.size()>0){
        	    playCardList.clear();
            }

        	//获取选中的卡牌信息
            for (CardModel tmpCard : user1CardList) {
                if (tmpCard.selected) {
                    tmpSelectPukeModelList.add(tmpCard.pukeInfo);
                    CardModel card = new CardModel();
                    card.pukeInfo=tmpCard.pukeInfo;
                    card.selected = false;
                    playCardList.add(card);
                }
            }
			if (tmpSelectPukeModelList.size()>0) {
				PukeManager pManager= new PukeManager();
				System.out.println(pManager.getCardListInfo(tmpSelectPukeModelList).type);
				if (pManager.getCardListInfo(tmpSelectPukeModelList).type != PukeListTypeEnum.mix) {
                    Iterator<CardModel> it = user1CardList.iterator();
                    while(it.hasNext()){
                        CardModel card = it.next();
                        if (card.selected) {
                            it.remove();
                        }
                    }
                    resetview();
                    this.getContentPane().repaint();
				}


			}
        }
	}
}
