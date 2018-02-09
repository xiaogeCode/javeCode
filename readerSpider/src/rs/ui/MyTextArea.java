package rs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;
import javax.swing.plaf.basic.BasicBorders.RadioButtonBorder;

import rs.model.Article;
import rs.model.ChapterModel;
import rs.model.MenuModel;
import rs.util.BiquGetNovel;
import rs.util.CommonUtil;
import rs.util.NovelManage;

public class MyTextArea extends JFrame implements ActionListener,TableOperationInterface{
	public static int jspValue;
    JScrollPane jsp=null;
    JTextArea jta=null;
    MyMenu menuUI = null;
    JPanel jp=null;
    JButton jb1=null;
    JButton jb2=null;
    JButton jb3=null;
    JButton jb4=null;
			
    String text  = "";
    int currentSourceIndex = 0;				//当前选择的小说来源
    public  MyTextArea() {
    	jta=new JTextArea();
        jta.setLineWrap(true);
        jta.setText(text);
        jta.setEditable(false);
        jsp=new JScrollPane(jta);
        jp=new JPanel();
        jb1=new JButton("上");
        jb2=new JButton("下");
        jb3=new JButton("目录");
        jb4=new JButton("小说源");
        
        jp.add(jb3);
        jp.add(jb1);
        jp.add(jb2);
        jp.add(jb4);
        
        this.setLayout(new BorderLayout());
        this.add(jsp);
        this.add(jp,BorderLayout.SOUTH);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
        jb4.addActionListener(this);
        this.setSize(300, 203);
        this.setVisible(true);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
    
    public void setText(String tx) {
		text = tx;
		jta.setText(text);
	}
    @Override
    public void actionPerformed(ActionEvent e) {
 
        if(e.getSource()==jb2)
        {
            jspValue=jsp.getVerticalScrollBar().getValue();
            jsp.getVerticalScrollBar().setValue(jspValue+=jsp.getHeight());
             
        }
        else if(e.getSource()==jb1)
        {
            jspValue=jsp.getVerticalScrollBar().getValue();
            jsp.getVerticalScrollBar().setValue(jspValue-=jsp.getHeight());
        }
        else if(e.getSource()==jb3)
        {
        	System.out.println("menu");
        	menuUI= new MyMenu();
        	menuUI.myTextArea = this;
        	menuUI.setTableUI(getChaptList());
        }
        else if(e.getSource()==jb4)
        {
        	System.out.println("小说源");
            NovelSourceRadio ns = new NovelSourceRadio();
            ns.txarea = this;
            ns.setLocation(this.getX()+this.getWidth()-250,this.getY()+this.getHeight());
            if (menuUI != null) {
            	menuUI.dispose();
            	menuUI = null;
			}
            
        }
    }
    //获取小说目录
    public List<ChapterModel> getChaptList() {
    	MenuModel menu = null;
    	switch (currentSourceIndex) {
    	case 0://80s
		{
			menu = NovelManage.getBalingtxtNovelMenu();
		}
			break;
		case 1://biqu
		{
			menu = NovelManage.getBiquNovelMenu();
		}
			break;
		case 2://miaobi
		{
			menu = NovelManage.getMiaobigeNovelMenu();
		}
			break;

		default:
			break;
		}
		
		List<ChapterModel> totalChaptList=new ArrayList<ChapterModel>();
		List<ChapterModel> chaptList = null;
		chaptList = menu.getBookList();
		copyArry1ToArray2(chaptList, totalChaptList);
		
		while(menu.getNextUrl() != null ){
			menu = BiquGetNovel.getMenu(menu.getNextUrl());
			chaptList = menu.getBookList();
			copyArry1ToArray2(chaptList, totalChaptList);
        }
		return totalChaptList;
	}
    //将第一个数组的元素全部加入到第二个数组
    public void copyArry1ToArray2(List<ChapterModel>arry1,List<ChapterModel>array2) {
		for (int i = 0; i < arry1.size(); i++) {
			array2.add(arry1.get(i));
		}
	}

    //获取单个章节
	@Override
	public void tableDidSelect(Vector v) {//表格被点击
		// TODO Auto-generated method stub
		System.out.println("get data"+v.get(0)+v.get(1)); 
		String url = (String) v.get(1);
		Article article  = null;
		switch (currentSourceIndex) {
    	case 0://80s
		{
			article  = NovelManage.getBalingtxtNovelByUrl(url);
		}
			break;
		case 1://biqu
		{
			article  = NovelManage.getBiquNovelByUrl(url);
		}
			break;
		case 2://miaobi
		{
			article  = NovelManage.getMiaobigeNovelByUrl(url);
		}
			break;

		default:
			break;
		}
		setText(article.getContent());
		jta.setCaretPosition(0);//滚动条置顶 
		this.setTitle(article.getTitle());
	}
	public void itemChanged(int index) {
		// TODO Auto-generated method stub
		currentSourceIndex = index;
		
	}
}
