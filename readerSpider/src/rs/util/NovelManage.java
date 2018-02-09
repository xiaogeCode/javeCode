package rs.util;

import java.util.List;

import rs.model.Article;
import rs.model.ChapterModel;
import rs.model.MenuModel;

public class NovelManage {

		//获取笔下网某个小说内容
		public static void getBixiaNovelMenu() {
			
		}
		//获取笔下网某个目录列表
		public static Article getBixiaNovelByUrl(String url) {
			//String firstUrl = "http://www.bxwx.org/b/5/5131/832882.html";
	        Article article = BixiaGetNovel.getArticle(url);
	        return article;
	        
		}
		
		
		//获取笔趣网某个小说目录列表
		public static MenuModel getBiquNovelMenu() {
			String firstUrl = "https://www.bqg5200.com/wapbook-16693_1/";
			MenuModel menu = BiquGetNovel.getMenu(firstUrl);
			return menu;
		}
		//获取笔趣网某个小说内容
		public static Article getBiquNovelByUrl(String url) {
			//String firstUrl = "https://www.bqg5200.com/wapbook-16693-7902491/";
	        Article article = BiquGetNovel.getArticle(url);
	        return article;
	        
		}
		//获取妙趣阁某个小说目录列表
		public static MenuModel getMiaobigeNovelMenu() {
			String firstUrl = "https://www.miaobige.com/read/20145/";
			MenuModel menu = MiaobigeNovel.getMenu(firstUrl);
			return menu;
	        
			
		}		
		//获取妙趣阁某个小说内容
		public static Article getMiaobigeNovelByUrl(String url) {
			//String firstUrl = "https://www.miaobige.com/read/20145/11136767.html"
	        Article article = MiaobigeNovel.getArticle(url);
			return article;
		}
		//获取80txt提取目录列表
		public static MenuModel getBalingtxtNovelMenu() {
			String firstUrl = "http://www.80txt.com/txtml_75764.html";
			MenuModel menu = BalingtxtNovel.getMenu(firstUrl);
			return menu;
	        
		}
		//获取80txt某个小说内容 
		public static Article getBalingtxtNovelByUrl(String url) {
	        Article article = BalingtxtNovel.getArticle(url);
			return article;
		}		
}
