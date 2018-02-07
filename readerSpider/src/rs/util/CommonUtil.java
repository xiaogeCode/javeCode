package rs.util;

import java.awt.TextField;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
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
    /**
     * С˵�Ĵ��뱾��
     * @param fileNama
     * @param content
     * @return
     */
    public static void writeToFile(String fileNama,String content) {
    	File txtFile = new File(fileNama);
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
    /**
     * ���ض�С˵
     * @param fileNama
     * @param content
     * @return
     */
    public static String readFromFile(String fileName) {
		String s = "";

        FileReader fr;
		try {
			fr = new FileReader (fileName);
			BufferedReader br = new BufferedReader (fr);
			String sb;
	        try {
				while ((sb = br.readLine() )!=null) {
					//System.out.println(sb);
					s+=sb+"\r\n";
				   //System.out.prinln (s);
				}
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
	        
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
        
        
		return s;
	}
  
}
