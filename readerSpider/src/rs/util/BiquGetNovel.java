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
     * ����url��ȡDocument����
     * @param url С˵�½�url
     * @return Document����
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
     * ���ݻ�ȡ��Document�����ҵ��½ڱ���
     * @param doc
     * @return ����
     */
    public static String getTitle(Document doc){
        return doc.getElementById("nr_title").text();
    	
    }

    /**
     * ���ݻ�ȡ��Document�����ҵ�С˵����
     * @param doc
     * @return ����
     */
    public static String getContent(Document doc){
        if(doc.getElementById("nr1") != null){
            return doc.getElementById("nr1").text();
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
        Element ul = doc.select("tr").last();
        //String regex = "<li><a href=\"(.*?)\">��һҳ<\\/a><\\/li>";
        String regex = "<a id=\"(.*?)\" href=\"(.*?)\">��һ��<\\/a><\\/td>";
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
            return "www.bqg5200.com" + href.attr("href");
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
        article.setMenuUrl(getMenuUrl(doc));
        System.out.println("menu:"+article.getMenuUrl());
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
    	menu.setNextUrl(getNextMenuUrl(doc));
    	menu.setBookList(getMenuBookList(doc));
    	return menu;
        
    }
    
   
    /**
     * ���ݻ�ȡ��Document�����ҵ���һĿ¼ҳ��Url��ַ
     * @param doc
     * @return nextMenuUrl
     */
    public static String getNextMenuUrl(Document doc){
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
    }
   
    /**
     * ���ݻ�ȡ��Document�����ҵ���һĿ¼ҳ��Url��ַ
     * @param doc
     * @return nextMenuUrl
     */
    public static List<ChapterModel> getMenuBookList(Document doc){
    	//<li><a href='/wapbook-16693-7852296/'>��78�£������������Ƽ�Ʊ����<span></span></a></li>
    	Element ul = doc.getElementsByClass("chapter").first();
    	String regex = "<li><a href=\"(.*?)\">(.*?)<span></span></a></li>";
    	List<ChapterModel> list  = new ArrayList<ChapterModel>();
    	Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Elements hrefs = ul.select("a[href]"); //����href���Ե�aԪ��
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
     * С˵�Ĵ��뱾��
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
