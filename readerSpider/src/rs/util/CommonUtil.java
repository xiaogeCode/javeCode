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
     * 小说的存入本地
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
     * 本地读小说
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
