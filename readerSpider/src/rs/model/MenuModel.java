package rs.model;

import java.util.ArrayList;
import java.util.List;


//Ŀ¼
public class MenuModel {

    private String url;			//��ǰҳurl
    private String nextUrl;		//��һҳurl
    //private List<String>bookList[] ;
    List<ChapterModel> bookList  = new ArrayList<ChapterModel>(); //����ҳ���ϵ��½�
    
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
