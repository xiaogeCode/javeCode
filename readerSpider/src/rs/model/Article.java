package rs.model;

public class Article {

	private String id;//id
    private String title;//����
    private String content;//����
    private String url;//��ǰ�½�url
    private String nextUrl;//��һ��url
    private String menuUrl;//��һ��url

    
    public void setId(String i) {
		id = i;
	}
    public String  getId() {
		return id;
	}
    
    public void setTitle(String i) {
		title = i;
	}
    public String  getTitle() {
		return title;
	}
    
    public void setContent(String i) {
		content = i;
	}
    public String  getContent() {
		return content;
	}
    
    public void setUrl(String i) {
		url = i;
	}
    public String  getUrl() {
		return url;
	}
    
    public void setNextUrl(String i) {
    	nextUrl = i;
	}
    public String  getNextUrl() {
		return nextUrl;
	}
    public void setMenuUrl(String i) {
    	menuUrl = i;
	}
    public String  getMenuUrl() {
		return menuUrl;
	}
    
    @Override
    public String toString() {
        return "Article [id=" + id + ", title=" + title + ", content="
                + content + ", url=" + url + ", nextUrl=" + nextUrl + "]";
    }

}
