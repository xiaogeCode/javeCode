package rs.util;

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
//获取80txt小说  http://www.80txt.com/txtml_75764.html
public class BalingtxtNovel {

	/**
     * 根据url获取Document对象
     * @param url 小说章节url
     * @return Document对象
     */
    public static Document getDocument(String url){
        Document doc = null;
        try {
            doc = Jsoup.connect(url).timeout(10000).get();
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
    	//<div class="title">
        //	<h1> 第1章：求求你们，排队啊！</h1>
        //	作者:<span><a href="/search/?s=%C0%E6%BB%A8%B0%D7%CA%AF" rel="nofollow">梨花白石</a></span>字数:<span>2358</span>更新时间:<span>2017-12-12 03:29:18</span>
        //</div>
    	Elements ul = doc.getElementsByClass("date");
    	Elements tagElement = doc.getElementsByTag("h1");
    	return tagElement.text();
    	
    }

    /**
     * 根据获取的Document对象找到小说内容
     * @param doc
     * @return 内容
     */
    public static String getContent(Document doc){
        if(doc.getElementById("content") != null){
            return doc.getElementById("content").text();
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
    	//<div class="jump"><a href="/read/20145/" class="disabled">上一章</a>
    	//<a href="javascript:showTip('','award_add','');">投推荐票</a> 
    	//<a href="/read/20145/">回目录</a>
    	//<a href="javascript:addMark();">标记书签</a>
    	//<a href="11136770.html" >下一章</a></div>
    	Elements ul = doc.getElementsByClass("jump");
        //Element ul = doc.select("tr").last();
        //String regex = "<li><a href=\"(.*?)\">下一页<\\/a><\\/li>";
        String regex = "<a href=\"(.*?)\" >下一章</a>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Element href = nextDoc.select("a").first();
            return "https://www.miaobige.com" + href.attr("href");
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
        // article.setNextUrl(getNextUrl(doc));
        //article.setMenuUrl(getMenuUrl(doc));
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
    	//menu.setNextUrl(getNextMenuUrl(doc));
    	menu.setBookList(getMenuBookList(doc));
    	return menu;
        
    }
    
   
    /**
     * 根据获取的Document对象找到下一目录页的Url地址
     * @param doc
     * @return nextMenuUrl
     */
    public static String getNextMenuUrl(Document doc){
    	return "";
    	/*
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
        */
    }
   
    /**
     * 根据获取的Document对象找到目录页的章节列表
     * @param doc
     * @return nextMenuUrl
     */
    public static List<ChapterModel> getMenuBookList(Document doc){
    	//<li><a rel="nofollow" href="http://www.qiushu.cc/t/75764/23536165.html">第18章：破案了！</a></li>
    	Element ul = doc.getElementById("yulan");
    	String regex = "<li><a rel=\"(.*?)\" href=\"(.*?)\">(.*?)</a></li>";
    	List<ChapterModel> list  = new ArrayList<ChapterModel>();
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Elements hrefs = ul.select("a[href]"); //带有href属性的a元素
            for (Element href : hrefs) {
            	ChapterModel model= new ChapterModel();
                model.setUrl(href.attr("href"));
                model.setTitle(href.text());
                
                //System.out.println("title:"+model.getTitle());
                //System.out.println("url"+model.getUrl());
                
                list.add(model);
            }
            
            return list;
        }else{
            return null;
        }
    	
    }
}

