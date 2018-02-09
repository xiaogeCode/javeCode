package rs.main;

import java.util.List;

import rs.model.*;
import rs.ui.MyTextArea;
import rs.util.*;
public class getAticles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//getBiquMenu();
		//getBiquNovel();
		//getBixiaNovel();
		//getMiaobigeNovel();
		//getBalingtxtNovel();
		MyTextArea myttArea =  new MyTextArea();
		//myttArea.setText(CommonUtil.readFromFile("g:/xtqpd.txt"));
		
		
	}

	//��ȡ������ĳ��С˵����
	public static void getBixiaNovel() {
		String firstUrl = "http://www.bxwx.org/b/5/5131/832882.html";
        Article article = BixiaGetNovel.getArticle(firstUrl);
        while(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("996627")){
            article = BixiaGetNovel.getArticle(article.getNextUrl());
            CommonUtil.writeToFile("g:/С˵.txt", article.getContent());
            System.out.println(article.getId()+"----"+article.getTitle());
        }
	}
	
	//��ȡ��Ȥ��ĳ��С˵Ŀ¼
	public static void getBiquMenu() {
		String firstUrl = "https://www.bqg5200.com/wapbook-16693_1/";
		MenuModel menu = BiquGetNovel.getMenu(firstUrl);
		
		List<ChapterModel> chaptList = null;
		chaptList = menu.getBookList();
		for (int i = 0; i < chaptList.size(); i++) {
			//String str=chaptList.get(i).getTitle()+":"+chaptList.get(i).getUrl();
			String str=chaptList.get(i).getTitle()+"\r\n";
			CommonUtil.writeToFile("g:/xtqmenu.txt",str);
		}
		
		while(menu.getNextUrl() != null ){
			menu = BiquGetNovel.getMenu("https://"+menu.getNextUrl());
			chaptList = menu.getBookList();
			for (int i = 0; i < chaptList.size(); i++) {
				//String str=chaptList.get(i).getTitle()+":"+chaptList.get(i).getUrl();
				String str=chaptList.get(i).getTitle()+"\r\n";
				CommonUtil.writeToFile("g:/xtqmenu.txt",str);
			}
        }
	}
	//��ȡ��Ȥ��ĳ��С˵����
	public static void getBiquNovel() {
		String firstUrl = "https://www.bqg5200.com/wapbook-16693-7902491/";
        Article article = BiquGetNovel.getArticle(firstUrl);
        while(article.getNextUrl() != null && article.getContent() != null && !article.getNextUrl().equals(article.getMenuUrl())){
            article = BiquGetNovel.getArticle("https://"+article.getNextUrl());
            //System.out.println("title:"+article.getTitle());
            CommonUtil.writeToFile("g:/xtqpd.txt", article.getTitle()+"\r\n");
            CommonUtil.writeToFile("g:/xtqpd.txt", article.getContent());
            //System.out.println(article.getId()+"----"+article.getTitle());
        }
	}
	//��ȡ��Ȥ��ĳ��С˵����
	public static void getMiaobigeNovel() {
		//String firstUrl = "https://www.miaobige.com/read/20145/";
		//MenuModel menu = MiaobigeNovel.getMenu(firstUrl);
		String firstUrl = "https://www.miaobige.com/read/20145/11136767.html";
        Article article = MiaobigeNovel.getArticle(firstUrl);
        //System.out.println("content�� "+article.getContent());
        System.out.println("title�� "+article.getTitle());
        System.out.println("nextUrl"+article.getNextUrl());
		
	}
	//��ȡ80txtĳ��С˵���� //ֻд�����ݺͱ������ȡĿ¼�б����ȡ
	public static void getBalingtxtNovel() {
		//String firstUrl = "http://www.80txt.com/txtml_75764.html";
		//MenuModel menu = BalingtxtNovel.getMenu(firstUrl);
		String firstUrl = "http://www.qiushu.cc/t/75764/23408137.html";
        Article article = BalingtxtNovel.getArticle(firstUrl);
        //ֻд�����ݺͱ������ȡ
        //System.out.println(article.getContent());
        //System.out.println("title"+article.getTitle());
	}
}
