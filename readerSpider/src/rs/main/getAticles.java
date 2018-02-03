package rs.main;

import java.util.List;

import rs.model.*;
import rs.util.*;
public class getAticles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		
		//getBiquMenu();
		//getBiquNovel();
		getBixiaNovel();
		
	}

	//获取笔下网某个小说内容
	public static void getBixiaNovel() {
		String firstUrl = "http://www.bxwx.org/b/5/5131/832882.html";
        Article article = CommonUtil.getArticle(firstUrl);
        while(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("996627")){
            article = CommonUtil.getArticle(article.getNextUrl());
            CommonUtil.writeToFile("g:/小说", article.getContent());
            System.out.println(article.getId()+"----"+article.getTitle());
        }
	}
	
	//获取笔趣网某个小说目录
	public static void getBiquMenu() {
		String firstUrl = "https://www.bqg5200.com/wapbook-16693_1/";
		MenuModel menu = BiquGetNovel.getMenu(firstUrl);
		
		List<ChapterModel> chaptList = null;
		chaptList = menu.getBookList();
		for (int i = 0; i < chaptList.size(); i++) {
			//String str=chaptList.get(i).getTitle()+":"+chaptList.get(i).getUrl();
			String str=chaptList.get(i).getTitle()+"\r\n";
			CommonUtil.writeToFile("g:/xtqmenu",str);
		}
		
		while(menu.getNextUrl() != null ){
			menu = BiquGetNovel.getMenu("https://"+menu.getNextUrl());
			chaptList = menu.getBookList();
			for (int i = 0; i < chaptList.size(); i++) {
				//String str=chaptList.get(i).getTitle()+":"+chaptList.get(i).getUrl();
				String str=chaptList.get(i).getTitle()+"\r\n";
				CommonUtil.writeToFile("g:/xtqmenu",str);
			}
        }
	}
	//获取笔趣网某个小说内容
	public static void getBiquNovel() {
		String firstUrl = "https://www.bqg5200.com/wapbook-16693-7902491/";
        Article article = BiquGetNovel.getArticle(firstUrl);
        while(article.getNextUrl() != null && article.getContent() != null && !article.getNextUrl().equals(article.getMenuUrl())){
            article = BiquGetNovel.getArticle("https://"+article.getNextUrl());
            System.out.println("title:"+article.getTitle());
            CommonUtil.writeToFile("g:/xtqpd", article.getTitle());
            //CommonUtil.writeToFile("g:/xtqpd", article.getContent());
            System.out.println(article.getId()+"----"+article.getTitle());
        }
	}
}
