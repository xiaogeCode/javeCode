package rs.util;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import rs.model.Article;
import rs.model.ChapterModel;
import rs.model.MenuModel;

public class BiquGetNovel {

	/**
     * 根据url获取Document对象
     * @param url 小说章节url
     * @return Document对象
     */
    public static Document getDocument(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(5000).get();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return doc;
    }
    /**
     * 根据获取的Document对象找到章节标题
     * @param doc
     * @return 标题
     */
    public static String getTitle(Document doc){
        return doc.getElementById("nr_title").text();
    	
    }

    /**
     * 根据获取的Document对象找到小说内容
     * @param doc
     * @return 内容
     */
    public static String getContent(Document doc){
        if(doc.getElementById("nr1") != null){
            return doc.getElementById("nr1").text();
        }else{
            return null;
        }

    }
    /**
     * 根据获取的Document对象找到下一章的Url地址
     * @param doc
     * @return 下一章Url
     */
    public static String getNextUrl(Document doc){
    	//<td class="next"><a id="pb_next" href="/wapbook-16693-7602922/">下一章</a></td>
        Element ul = doc.select("tr").last();
        //String regex = "<li><a href=\"(.*?)\">下一页<\\/a><\\/li>";
        String regex = "<a id=\"(.*?)\" href=\"(.*?)\">下一章<\\/a><\\/td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Element href = nextDoc.select("a").first();
            return "www.bqg5200.com" + href.attr("href");
        }else{
            return null;
        }


    }

    /**
     * 根据获取的Document对象找到目录的Url地址
     * @param doc
     * @return 目录Url
     */
    public static String getMenuUrl(Document doc){
    	//<td class="next"><a id="pb_next" href="/wapbook-16693-7602922/">下一章</a></td>
        Element ul = doc.select("tr").last();
        //String regex = "<li><a href=\"(.*?)\">下一页<\\/a><\\/li>";
        String regex = "<a id=\"(.*?)\" href=\"(.*?)\">目录<\\/a><\\/td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Element href = nextDoc.select("a").first();
            return "www.bqg5200.com" + href.attr("href");
        }else{
            return null;
        }
    }
    
    /**
     * 根据url获取id
     * @param url
     * @return id 
     */
    public static String getId(String url){
        String urlSpilts[] = url.split("/");
        return (urlSpilts[urlSpilts.length - 1]).split("\\.")[0];
    }

    /**
     * 根据小说的Url获取一个Article对象
     * @param url
     * @return
     */
    public static Article getArticle(String url){
        Article article = new Article();        
        article.setUrl(url);
        Document doc = getDocument(url);
        article.setId(getId(url));
        article.setTitle(getTitle(doc));
        article.setNextUrl(getNextUrl(doc));
        article.setMenuUrl(getMenuUrl(doc));
        System.out.println("menu:"+article.getMenuUrl());
        article.setContent(getContent(doc));
        return article;
    }
    
    /**
     * 根据小说的Url获取一个menu对象
     * @param url
     * @return
     */
    public static MenuModel getMenu(String url){
    	MenuModel menu = new MenuModel();
    	Document doc = getDocument(url);
    	menu.setUrl(url);
    	menu.setNextUrl(getNextMenuUrl(doc));
    	menu.setBookList(getMenuBookList(doc));
    	return menu;
        
    }
    
   
    /**
     * 根据获取的Document对象找到下一目录页的Url地址
     * @param doc
     * @return nextMenuUrl
     */
    public static String getNextMenuUrl(Document doc){
    	//<div class="page"><a href="/wapbook-16693_1/">首页</a><a href="/wapbook-16693_4/">上一页</a><a href="/wapbook-16693_6/">下一页</a><a href="/wapbook-16693_6/">尾页</a></div>
    	Element ul = doc.getElementsByClass("page").first();
    	String regex = "<a href=\"(.*?)\">下一页</a>";
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Element href = nextDoc.select("a").first();
            return "www.bqg5200.com" + href.attr("href");
        }else{
            return null;
        }
    }
   
    /**
     * 根据获取的Document对象找到下一目录页的Url地址
     * @param doc
     * @return nextMenuUrl
     */
    public static List<ChapterModel> getMenuBookList(Document doc){
    	//<li><a href='/wapbook-16693-7852296/'>第78章：有凶器（求推荐票！）<span></span></a></li>
    	Element ul = doc.getElementsByClass("chapter").first();
    	String regex = "<li><a href=\"(.*?)\">(.*?)<span></span></a></li>";
    	List<ChapterModel> list  = new ArrayList<ChapterModel>();
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Elements hrefs = ul.select("a[href]"); //带有href属性的a元素
            for (Element href : hrefs) {
            	ChapterModel model= new ChapterModel();
                model.setUrl("www.bqg5200.com" + href.attr("href"));
                model.setTitle(href.text());
                
                list.add(model);
            }
            
            return list;
        }else{
            return null;
        }
    	
    }
    
    /**
     * 小说的存入本地
     * @param fileNama
     * @param content
     * @return
     */
    public static void writeToFile(String fileNama,String content) {
    	File txtFile = new File(fileNama+".txt");
    	if (!txtFile.exists()) {
    		//txtFile.delete();
    		try {
    			txtFile.createNewFile();
    		} catch (IOException e) {
    			// TODO Auto-generated catch block
    			e.printStackTrace();
    		}
		}
    	
    	FileWriter writer = null;
    	try {
			writer = new FileWriter(txtFile, true);
			writer.append(content);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			if(null!=writer)
				try {
					writer.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
		}
    }
}
