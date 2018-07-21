package jna.main;

import jna.source.User32;
import jna.source.W32API;

import java.io.IOException;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: OpenWindow
 * Author:   xiaoge
 * Date:     2018/7/21 1:19
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class OpenWindow {
    public static void main(String[] args) {
        W32API.HWND hwnd = User32.INSTANCE.FindWindow(0, "a.txt - 记事本");
        W32API.HWND childHwnd = User32.INSTANCE.FindWindowEx(hwnd, 0, 0, 0); //User32.INSTANCE.SetForegroundWindow(hwnd);

        if(hwnd !=null)
        {
            User32.INSTANCE.ShowWindow(hwnd, 9);
            User32.INSTANCE.SetForegroundWindow(hwnd);
            for(int i = 0;i< 100;i++)
            {
                //keyPress(82);
                //User32.INSTANCE.PostMessage(hwnd, 82, 0,0);
                backKeyPress(childHwnd, 82);
            }
        } else
        {
            try {
                System.out.println(" can't find the window !!");
                Runtime.getRuntime().exec("NotePad.exe");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void backKeyPress(W32API.HWND hwnd,int keyNum) {
        User32.INSTANCE.PostMessage(hwnd, 256 ,keyNum, 0);
    }
    public static void backKeyRelease(W32API.HWND hwnd,int keyNum) {
        User32.INSTANCE.PostMessage(hwnd, 257 ,keyNum, 0);
    }
    public static void keyPress(int keyNum) {
        User32.INSTANCE.keybd_event(keyNum,0,0,0); User32.INSTANCE.keybd_event(keyNum,0,"KEYEVENTF_KEYUP",0);
    }
}



