package jna.manger;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: WindowFuncManager
 * Author:   xiaoge
 * Date:     2018/7/20 23:49
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */


import com.sun.jna.platform.win32.BaseTSD;
import com.sun.jna.platform.win32.User32;
import jna.model.KeyboardHook;

import com.sun.jna.Native;
import com.sun.jna.Pointer;
import com.sun.jna.platform.win32.WinDef;
import com.sun.jna.platform.win32.WinDef.HWND;
import com.sun.jna.platform.win32.WinUser;
import com.sun.jna.platform.win32.WinUser.WNDENUMPROC;
import com.sun.jna.win32.StdCallLibrary;
import jna.source.W32API;


public  class WindowFuncManager {

    public  interface User32 extends StdCallLibrary {

        User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class);
        boolean EnumWindows(WinUser.WNDENUMPROC lpEnumFunc, Pointer arg);
        int GetWindowTextA(HWND hWnd, byte[] lpString, int nMaxCount);
        HWND FindWindowExA(HWND hwndParent, HWND childAfter, String className, String windowName);
        HWND FindWindowA(String className, String windowName);
    }
    public  static WindowFuncManager windowFuncManager;
    static {
        windowFuncManager = new WindowFuncManager();
    }
    static WinUser.INPUT input = new WinUser.INPUT();

    /**
     * 功能描述: <br>
     * 〈枚举窗口〉

     * @return:
     * @Author:xiaoge
     * @Date: 2018/7/20 23:32
     */
    private  void enumWindow(String title) {
        final WindowFuncManager.User32 user32 = WindowFuncManager.User32.INSTANCE;
        user32.EnumWindows(new WNDENUMPROC() {
            int count = 0;
            @Override
            public boolean callback(HWND hWnd, Pointer arg1) {
                byte[] windowText = new byte[512];
                user32.GetWindowTextA(hWnd, windowText, 512);
                String wText = Native.toString(windowText);

                // get rid of this if block if you want all windows regardless of whether
                // or not they have text
                if (wText.isEmpty()) {
                    return true;
                }

                System.out.println("Found window with text " + hWnd + ", total " + ++count
                        + " Text: " + wText);
                return true;
            }
        }, null);
    }

    /**
     * 功能描述: <br>
     * 〈寻找特定标题的窗口〉
     *

     * @return:
     * @Author:xiaoge
     * @Date: 2018/7/20 23:21
     */
    public  void findWindow(String title) {
        HWND hwnd = com.sun.jna.platform.win32.User32.INSTANCE.FindWindow(null,title);
        //HWND hwnd = WindowFuncManager.User32.INSTANCE.FindWindowA(null,title);


        if (hwnd == null) {
            System.out.println(title+" is not running");
        }else{
            final WindowFuncManager.User32 user32 = WindowFuncManager.User32.INSTANCE;
            com.sun.jna.platform.win32.User32.INSTANCE.ShowWindow(hwnd,9);        // SW_RESTORE
            com.sun.jna.platform.win32.User32.INSTANCE.SetForegroundWindow(hwnd);   // bring to front

            byte[] windowText = new byte[512];
            user32.GetWindowTextA(hwnd, windowText, 512);
            String wText = Native.toString(windowText);
            if (!wText.isEmpty()) {
                System.out.println("Found window with text " + hwnd + ","+
                        " Text: " + wText);
            }

        }



    }

    public  void findQQ() {
	   String windowName = "a.txt - 记事本";
       HWND hwnd = WindowFuncManager.User32.INSTANCE.FindWindowA(null,windowName);
       HWND hwndShell = User32.INSTANCE.FindWindowExA(null, null, null, windowName);

        if (hwndShell==null){
           System.out.println("Miss!");
       } else {
           System.out.println("Hit!");
           //boolean showed = User32.INSTANCE.ShowWindow(hwnd, WinUser.SW_RESTORE );
           //System.out.println(windowName+(showed?"窗口之前可见.":"窗口之前不可见."));
       }
    }
    public  void  sendChar(char ch){
        input.type = new WinDef.DWORD( WinUser.INPUT.INPUT_KEYBOARD );
        input.input.setType("ki"); // Because setting INPUT_INPUT_KEYBOARD is not enough: https://groups.google.com/d/msg/jna-users/NDBGwC1VZbU/cjYCQ1CjBwAJ
        input.input.ki.wScan = new WinDef.WORD( 0 );
        input.input.ki.time = new WinDef.DWORD( 0 );
        input.input.ki.dwExtraInfo = new BaseTSD.ULONG_PTR( 0 );
        // Press
        input.input.ki.wVk = new WinDef.WORD( Character.toUpperCase(ch) ); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD( 0 );  // keydown
        com.sun.jna.platform.win32.User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );

        // Release
        input.input.ki.wVk = new WinDef.WORD( Character.toUpperCase(ch) ); // 0x41
        input.input.ki.dwFlags = new WinDef.DWORD( 2 );  // keyup
        com.sun.jna.platform.win32.User32.INSTANCE.SendInput( new WinDef.DWORD( 1 ), ( WinUser.INPUT[] ) input.toArray( 1 ), input.size() );
    }
    public  void hook() {
        KeyboardHook kbhook = new KeyboardHook();
        new Thread(kbhook).start();
    }
}
