package rs.model;

import java.util.ArrayList;
import java.util.List;


//目录
public class MenuModel {

    private String url;			//当前页url
    private String nextUrl;		//下一页url
    //private List<String>bookList[] ;
    List<ChapterModel> bookList  = new ArrayList<ChapterModel>(); //保存页面上的章节
    
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
    
    public void setBookList(List<ChapterModel> list) {
    	if (!bookList.isEmpty()) {
			bookList.clear();
		}
    	for (int i = 0; i < list.size(); i++) {
			bookList.add(list.get(i));
		}
	}
    public List<ChapterModel>  getBookList() {
		return bookList;
	}
}
