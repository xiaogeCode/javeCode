package jna.main;

import com.sun.jna.platform.win32.WinDef;
import jna.manger.WindowFuncManager;
import jna.model.KeyboardHook;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;



public class Main {


   public static void main(String[] args) {
       //WindowFuncManager.windowFuncManager.findQQ();
       WindowFuncManager.windowFuncManager.findWindow("a.txt - 记事本");
       WindowFuncManager.windowFuncManager.sendChar('a');

//	   hook();

   }


}