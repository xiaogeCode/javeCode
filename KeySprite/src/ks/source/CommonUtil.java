package ks.source;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;


import ks.model.QupuModel;


public class CommonUtil {
	public static	ArrayList<QupuModel> QuPuList;
	static{
		QuPuList=new ArrayList<QupuModel>();
		QuPuList.add(new QupuModel("太平令", "yewte", 6));
		QuPuList.add(new QupuModel("玉人歌", "eettyq", 6));
		QuPuList.add(new QupuModel("折红英", "ytywet", 6));
		QuPuList.add(new QupuModel("佳人醉", "ytewqty", 6));
		QuPuList.add(new QupuModel("破阵子", "wetwet", 6));
		QuPuList.add(new QupuModel("绝音", "qwe", 66));
		QuPuList.add(new QupuModel("山鬼谣", "yetye", 3*60+6));
		QuPuList.add(new QupuModel("苏生", "qqwett", 30*60+6));
	}

    /*
     * 
     * @param fileNama "g:/xtqpd.txt"
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
     * 
     * @param fileNama "g:/xtqpd.txt"
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
					//s+=sb+"\r\n";
					s+=sb;

				   //System.out.prinln (s);q
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

