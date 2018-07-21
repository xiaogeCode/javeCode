package jna.source;

import com.sun.jna.Native;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: User32
 * Author:   xiaoge
 * Date:     2018/7/21 1:29
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public interface User32 extends W32API {
    User32 INSTANCE = (User32) Native.loadLibrary("user32", User32.class, DEFAULT_OPTIONS);
    boolean ShowWindow(HWND hWnd, int nCmdShow);
    boolean SetForegroundWindow(HWND hWnd);
    HWND FindWindow(String winClass, String title);
    HWND FindWindow(int winClass, String title);
    HWND FindWindowEx(HWND hWnd, HWND childWnd, int wParam, int lParam);
    HWND FindWindowEx(HWND hWnd, int childWnd, int wParam, int lParam);
    boolean PostMessage(HWND hWnd, Integer Msg, Integer wParam, Integer lParam);
    boolean PostMessage(HWND hWnd, int Msg, int wParam, int lParam);
    boolean PostMessage(HWND hWnd,String Msg,int wParam,int lParam);
    boolean PostMessage(HWND hWnd,String Msg,String wParam,String lParam);
    boolean PostMessage(HWND hWnd,int Msg,String wParam,String lParam);
    boolean PostMessage(HWND hWnd,int Msg,String wParam,int lParam);
    boolean PostMessage(HWND hWnd,String Msg,String wParam,int lParam);
    void keybd_event(String bVk,String bScan,String dwFlags,String dwExtralnfo);
    void keybd_event(int bVk,String bScan,String dwFlags,String dwExtralnfo);
    void keybd_event(String bVk,int bScan,int dwFlags,int dwExtralnfo);
    void keybd_event(int bVk,int bScan,int dwFlags,int dwExtralnfo);
    void keybd_event(int bVk,int bScan,String dwFlags,int dwExtralnfo);
    void keybd_event(String bVk,int bScan,String dwFlags,int dwExtralnfo);
    boolean SendMessage(HWND hWnd,Integer Msg,Integer wParam,Integer lParam);
    boolean SendMessage(HWND hWnd,int Msg,int wParam,int lParam);
    boolean SendMessage(HWND hWnd,String Msg,int wParam,int lParam);
    boolean SendMessage(HWND hWnd,String Msg,String wParam,String lParam);
    boolean SendMessage(HWND hWnd,int Msg,String wParam,String lParam);
    boolean SendMessage(HWND hWnd,int Msg,String wParam,int lParam);
    boolean SendMessage(HWND hWnd,String Msg,String wParam,int lParam);
}
