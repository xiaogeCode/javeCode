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
//��ȡ��ʸ�С˵  https://www.miaobige.com/read/20145/
public class MiaobigeNovel {

	/**
     * ����url��ȡDocument����
     * @param url С˵�½�url
     * @return Document����
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
     * ���ݻ�ȡ��Document�����ҵ��½ڱ���
     * @param doc
     * @return ����
     */
    public static String getTitle(Document doc){
    	//<div class="title">
        //	<h1> ��1�£��������ǣ��ŶӰ���</h1>
        //	����:<span><a href="/search/?s=%C0%E6%BB%A8%B0%D7%CA%AF" rel="nofollow">�滨��ʯ</a></span>����:<span>2358</span>����ʱ��:<span>2017-12-12 03:29:18</span>
        //</div>
    	Elements ul = doc.getElementsByClass("title");
    	Elements tagElement = doc.getElementsByTag("h1");
    	return tagElement.text();
    	
    }

    /**
     * ���ݻ�ȡ��Document�����ҵ�С˵����
     * @param doc
     * @return ����
     */
    public static String getContent(Document doc){
        if(doc.getElementById("content") != null){
            return doc.getElementById("content").text();
        }else{
            return null;
        }

    }
    /**
     * ���ݻ�ȡ��Document�����ҵ���һ�µ�Url��ַ
     * @param doc
     * @return ��һ��Url
     */
    public static String getNextUrl(Document doc){
    	//<td class="next"><a id="pb_next" href="/wapbook-16693-7602922/">��һ��</a></td>
    	//<div class="jump"><a href="/read/20145/" class="disabled">��һ��</a>
    	//<a href="javascript:showTip('','award_add','');">Ͷ�Ƽ�Ʊ</a> 
    	//<a href="/read/20145/">��Ŀ¼</a>
    	//<a href="javascript:addMark();">�����ǩ</a>
    	//<a href="11136770.html" >��һ��</a></div>
    	Elements ul = doc.getElementsByClass("jump");
        //Element ul = doc.select("tr").last();
        //String regex = "<li><a href=\"(.*?)\">��һҳ<\\/a><\\/li>";
        String regex = "<a href=\"(.*?)\" >��һ��</a>";
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
     * ���ݻ�ȡ��Document�����ҵ�Ŀ¼��Url��ַ
     * @param doc
     * @return Ŀ¼Url
     */
    public static String getMenuUrl(Document doc){
    	//<td class="next"><a id="pb_next" href="/wapbook-16693-7602922/">��һ��</a></td>
        Element ul = doc.select("tr").last();
        //String regex = "<li><a href=\"(.*?)\">��һҳ<\\/a><\\/li>";
        String regex = "<a id=\"(.*?)\" href=\"(.*?)\">Ŀ¼<\\/a><\\/td>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Element href = nextDoc.select("a").first();
            return "https://www.bqg5200.com" + href.attr("href");
        }else{
            return null;
        }
    }
    
    /**
     * ����url��ȡid
     * @param url
     * @return id 
     */
    public static String getId(String url){
        String urlSpilts[] = url.split("/");
        return (urlSpilts[urlSpilts.length - 1]).split("\\.")[0];
    }

    /**
     * ����С˵��Url��ȡһ��Article����
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
        //article.setMenuUrl(getMenuUrl(doc));
        article.setContent(getContent(doc));
        return article;
    }
    
    /**
     * ����С˵��Url��ȡһ��menu����
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
     * ���ݻ�ȡ��Document�����ҵ���һĿ¼ҳ��Url��ַ
     * @param doc
     * @return nextMenuUrl
     */
    public static String getNextMenuUrl(Document doc){
    	return "";
    	/*
    	//<div class="page"><a href="/wapbook-16693_1/">��ҳ</a><a href="/wapbook-16693_4/">��һҳ</a><a href="/wapbook-16693_6/">��һҳ</a><a href="/wapbook-16693_6/">βҳ</a></div>
    	Element ul = doc.getElementsByClass("page").first();
    	String regex = "<a href=\"(.*?)\">��һҳ</a>";
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
     * ���ݻ�ȡ��Document�����ҵ�Ŀ¼ҳ���½��б�
     * @param doc
     * @return nextMenuUrl
     */
    public static List<ChapterModel> getMenuBookList(Document doc){
    	//<li><a href='/wapbook-16693-7852296/'>��78�£������������Ƽ�Ʊ����<span></span></a></li>
    	//<li><a href="/read/20145/11136767.html" target="_blank">��1�£��������ǣ��ŶӰ���</a> 12-12</li>
    	//Element ul = doc.getElementsByClass("chapter").first();
    	Element ul = doc.getElementById("readerlists");
    	//Element ul = readerlists.getElementsByClass("ul").first();
    	//String regex = "<li><a href=\"(.*?)\">(.*?)<span></span></a></li>";
    	String regex = "<li><a href=\"(.*?)\" target=\"(.*?)\">(.*?)</a> 12-12</li>";
    	List<ChapterModel> list  = new ArrayList<ChapterModel>();
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Elements hrefs = ul.select("a[href]"); //����href���Ե�aԪ��
            for (Element href : hrefs) {
            	ChapterModel model= new ChapterModel();
                model.setUrl("https://www.miaobige.com" + href.attr("href"));
                model.setTitle(href.text());
                
                System.out.println("title:"+model.getTitle());
                System.out.println("url"+model.getUrl());
                
                list.add(model);
            }
            
            return list;
        }else{
            return null;
        }
    	
    }
}
