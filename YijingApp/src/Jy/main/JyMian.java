package Jy.main;

import Yj.ui.*;
import Yj.util.*;

import java.io.File;

public class JyMian {

	public static void main(String[] args) throws Exception {

        try
        {
            org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper.launchBeautyEyeLNF();
        }
        catch(Exception e)
        {
            //TODO exception
        }
		new ManageView();
	}
}
