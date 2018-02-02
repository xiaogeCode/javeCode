package rs.main;

import rs.model.*;
import rs.util.*;
public class getAticles {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub

		String firstUrl = "http://www.bxwx.org/b/5/5131/832882.html";
        Article article = CommonUtil.getArticle(firstUrl);
        while(article.getNextUrl() != null && article.getContent() != null && !article.getId().equals("996627")){
            article = CommonUtil.getArticle(article.getNextUrl());
            CommonUtil.writeToFile("g:/С˵", article.getContent());
            System.out.println(article.getId()+"----"+article.getTitle());
        }
	}

}
