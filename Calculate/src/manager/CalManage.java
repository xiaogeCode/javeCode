package manager;


import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: CalManage
 * Author:   xiaoge
 * Date:     2018/8/14 22:36
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class CalManage {
    Stack<String> calStack = new Stack<>();
    Stack<String> middleStack = new Stack<>();

/**
 * 功能描述: <br>
 * 〈枚举四个数的计算〉
   参数         [a, b, c, d]
 * 返回 @return:void
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/15 1:17
 */
    public void calFourNum(int a, int b, int c, int d) {
        String[] numArr ={String.valueOf(a),String.valueOf(b),String.valueOf(c),String.valueOf(d)};
        String[] operaArr= {"+","-","*","/"};
//        对四个数进行全排列 24种
        List<int[]> numList = getSortAllList();
//        枚举四个操作符情况 4X4X4=64种
        List<int[]> operaList = getOperaList();

        for (int i=0;i<numList.size();i++) {
            int[] tmpNum = numList.get(i);
            for (int k=0;k<operaList.size();k++) {
                int[] tmpOpera = operaList.get(k);
                //后序排列  ab*cd+- 等价于 (a*b)-(c+d)
                String str = numArr[tmpNum[0]]+" "+numArr[tmpNum[1]]+" "+operaArr[tmpOpera[0]]+" "
                        +numArr[tmpNum[2]]+" "+numArr[tmpNum[3]]+" "+operaArr[tmpOpera[1]]+" "+operaArr[tmpOpera[2]];
                //输出等于24情况
                if (calString(str)==24){
                    System.out.println(tranLastToMidle(str)+"=: " + calString(str));
                }
                //后序排列  ab+c+d+ 等价于 (a+b+c)+d
                String str2 = numArr[tmpNum[0]]+" "+numArr[tmpNum[1]]+" "+operaArr[tmpOpera[0]]+" "
                        +numArr[tmpNum[2]]+" "+operaArr[tmpOpera[1]]+" "+numArr[tmpNum[3]]+" "+operaArr[tmpOpera[2]];
                //输出等于24情况
                if (calString(str2)==24){
                    System.out.println(tranLastToMidle(str2)+"=: " + calString(str2));
                }
                //后序排列  abc+d++ 等价于 a+(b+c+d) 24游戏不用考虑
            }
        }

    }
    /**
     * 功能描述: <br>
     * 〈获取运算符排列〉
       参数         []
     * 返回 @return:java.util.List<int[]>
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/15 1:24
     */
    public List<int[]> getOperaList(){
        List<int[]> list  = new ArrayList();
        for (int i=0;i<4;i++){
            int[] tmp={0,0,0};
            tmp[0] = i;
            for (int i2=0;i2<4;i2++) {
                tmp[1] = i2;
                for (int i3=0;i3<4;i3++) {
                    tmp[2] = i3;
                    int[] tmpInfo ={tmp[0],tmp[1],tmp[2]};
                    list.add(tmpInfo);
                }
            }
        }

        return list;
    }
    /**
     * 功能描述: <br>
     * 〈获取四个数据的全排列〉
       参数         []
     * 返回 @return:java.util.List<int[]>
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/15 1:24
     */
    public List<int[]> getSortAllList(){
        //默认未选中
        int[] seletInfo = {0,0,0,0};
        //当前排列
        int[] curInfo = {0,0,0,0};

        List<int[]> result = new ArrayList<>();

        sortAll(0,seletInfo,curInfo,result);

        return result;

    }
    //获取四个数的全排列递归
    public void sortAll(int index,int[] selectInfo,int[] curInfo,List resutList){
        for (int i=0;i<4;i++) {
            if (selectInfo[i] == 0) {
                int[] tmpseletInfo ={selectInfo[0],selectInfo[1],selectInfo[2],selectInfo[3]};
                tmpseletInfo[i] =1;

                curInfo[index] = i;

                if (index == 3){
                    int[] tmpcurInfo ={curInfo[0],curInfo[1],curInfo[2],curInfo[3]};
                    resutList.add(tmpcurInfo);
                }else{
                    sortAll(index+1,tmpseletInfo,curInfo,resutList);
                }

            }
        }
    }

/*
后序表达式计算 不考虑括号
*/
    public double calString(String str) {
        double returnData = -1;
        String[] list = str.split(" ");
        for (int i=0;i<list.length;i++) {
            if (list[i].equals("+") ||list[i].equals("-") ||list[i].equals("*")||list[i].equals("/")){
                double a1 = Double.valueOf(calStack.pop());
                double a2 = Double.valueOf(calStack.pop());
                double result = operateResult(a2,a1,list[i]);
                calStack.push(String.valueOf(result));
            }else{
                calStack.push(list[i]);
            }
        }
        if (!calStack.empty()){
            returnData =Double.valueOf(calStack.pop());
        }
        return returnData;
    }

    public double operateResult(double a,double b,String op) {
        switch (op) {
            case "+": {
                return a+b;
            }
            case "-": {
                return a-b;
            }case "*": {
                return a*b;
            }
            case "/":
            default:{
                if (b != 0) {
                    return a / b;
                } else {
                    return -999;
                }

            }
        }

    }
    /**
     * 功能描述: <br>
     * 〈后序转为中序输出〉
       参数         [str]
     * 返回 @return:java.lang.String
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/15 1:25
     */
    public String tranLastToMidle(String str){
        String reStr="";
        String[] list = str.split(" ");
        for (int i=0;i<list.length;i++) {
            if (list[i].equals("+") ||list[i].equals("-") ||list[i].equals("*")||list[i].equals("/")){
                String a=middleStack.pop();
                String b=middleStack.pop();
                String tmp = "("+b+list[i]+a+")";
                middleStack.push(tmp);
            }else {
                middleStack.push(list[i]);
            }
        }
        if (!middleStack.empty()){
            reStr = middleStack.pop();
        }
        return reStr;
    }
}
