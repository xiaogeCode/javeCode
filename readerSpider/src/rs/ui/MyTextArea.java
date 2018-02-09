package rs.ui;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;

import javax.swing.*;

import rs.model.Article;
import rs.model.ChapterModel;
import rs.model.MenuModel;
import rs.util.BiquGetNovel;
import rs.util.CommonUtil;

public class MyTextArea extends JFrame implements ActionListener,TableOperationInterface{
	public static int jspValue;
    JScrollPane jsp=null;
    JTextArea jta=null;
    JPanel jp=null;
    JButton jb1=null;
    JButton jb2=null;
    JButton jb3=null;
    String text  = "";
    public  MyTextArea() {
    	jta=new JTextArea();
        jta.setLineWrap(true);
        jta.setText(text);
        jta.setEditable(false);
        jsp=new JScrollPane(jta);
        jp=new JPanel();
        jb3=new JButton("目录");
        jb1=new JButton("上");
        jb2=new JButton("下");
        jp.add(jb3);
        jp.add(jb1);
        jp.add(jb2);
        this.setLayout(new BorderLayout());
        this.add(jsp);
        this.add(jp,BorderLayout.SOUTH);
        jb1.addActionListener(this);
        jb2.addActionListener(this);
        jb3.addActionListener(this);
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
            MyMenu menu= new MyMenu();
            menu.myTextArea = this;
            menu.setTableUI(getChaptList());
        }
    }
    public List<ChapterModel> getChaptList() {
    	String firstUrl = "https://www.bqg5200.com/wapbook-16693_1/";
		MenuModel menu = BiquGetNovel.getMenu(firstUrl);
		
		List<ChapterModel> totalChaptList=new ArrayList<ChapterModel>();
		List<ChapterModel> chaptList = null;
		chaptList = menu.getBookList();
		copyArry1ToArray2(chaptList, totalChaptList);
		
		while(menu.getNextUrl() != null ){
			menu = BiquGetNovel.getMenu("https://"+menu.getNextUrl());
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

	@Override
	public void tableDidSelect(Vector v) {//表格被点击
		// TODO Auto-generated method stub
		System.out.println("get data"+v.get(0)+v.get(1)); 
		Article article = BiquGetNovel.getArticle("https://"+v.get(1));
		setText(article.getContent());
		jta.setCaretPosition(0);//滚动条置顶 
	}
}
