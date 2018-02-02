package rs.util;

import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import rs.model.*;;

public class CommonUtil {

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
        return doc.getElementById("title").text();
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
        Element ul = doc.select("ul").first();
        String regex = "<li><a href=\"(.*?)\">��һҳ<\\/a><\\/li>";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(ul.toString());
        Document nextDoc = null;
        if (matcher.find()) {
            nextDoc = Jsoup.parse(matcher.group());
            Element href = nextDoc.select("a").first();
            return "http://www.bxwx.org/b/5/5131/" + href.attr("href");
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
        article.setContent(getContent(doc));
        return article;
    }

	    
}
