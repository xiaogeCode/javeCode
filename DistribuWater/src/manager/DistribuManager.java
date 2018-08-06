package manager;

import com.sun.org.apache.xalan.internal.xsltc.compiler.util.ResultTreeType;
import model.StateModel;
import model.StateNode;

import javax.swing.text.StyledEditorKit;
import java.nio.file.attribute.UserDefinedFileAttributeView;
import java.sql.Statement;
import java.util.*;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: DistribuManager
 * Author:   xiaoge
 * Date:     2018/8/6 11:50
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class DistribuManager {
    //定义容器a,b 的最大容量
    public static final int MAX_A = 3;
    public static final int MAX_B = 10;
    //定义容器初始状态和目标状态
    StateModel wateState;
    StateModel gonalState = new StateModel(0,7);
    //保存状态队列，防止重复搜索
    Queue<StateNode>stateExistList= null;
    //已经搜索过的表，防止重复搜索
    List<StateNode> alreadySearchList = null;
    //保存最后找到的节点，可根据此找到解决路径
    StateNode resultTree = null;

    public DistribuManager() {
        wateState = new StateModel(0,0);
        stateExistList = new LinkedList<StateNode>();
        alreadySearchList = new ArrayList<StateNode>();
        StateTree tree = new StateTree();
        tree.insert(wateState,null);
        stateExistList.offer(tree.rootNode);
    }
    /**
     * 功能描述: <br>
     * 〈广度优先搜索取水解决方案〉
       参数         []
     * 返回 @return:void
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:40
     */
    public void serch(){
        int k = 0;
        String[] opeStr={
                "    empty a",
                "    empty b",
                "   filled a",
                "   filled b",
                "pour a to b",
                "pour b to a "};
        while (stateExistList.size()>0){
            k++;
            //从队列中取出一个节点
            StateNode curStateNode = stateExistList.poll();

            System.out.println("第"+k+"次");
            //System.out.println("  curState " +": "+curStateNode.getState().getaVol()+"  "+curStateNode.getState().getbVol());
            //System.out.println("             a  "+"b");

            //对队列进行六种操作 生成的新状态保存到队列中
            for (int i=0;i<6;i++){
                StateModel tmp = operateWithType(curStateNode.getState(),i);

                //System.out.println(opeStr[i] +": "+tmp.getaVol()+"  "+tmp.getbVol());

                //生成的新状态不在状态队列中
                if (!stateExist(tmp) && !existInAlreadySearchStateList(tmp)){
                    //构建新节点 加入到队列中
                    StateNode node = new StateNode(tmp);
                    node.setParrent(curStateNode);
                    stateExistList.offer(node);
                    //找到解决方案，倒序输出
                    //判断b容器的值是否相等
                    if (tmp.getbVol() == gonalState.getbVol()){
                        resultTree = node;
                        System.out.println("resutl: ");
                        StateTree.printPath(resultTree);
                        return;
                    }
                }
            }
            alreadySearchList.add(curStateNode);
        }

    }
    /**
     * 功能描述: <br>
     * 〈枚举六种取水操作〉
       参数         [curState, type]
     * 返回 @return:model.StateModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:36
     */
    public StateModel operateWithType(StateModel curState,int type){
        switch (type){
            case 0:{
                return emptyA(curState);

            }
            case 1:{
                return emptyB(curState);

            }
            case 2:{
                return fillA(curState);

            }
            case 3:{
                return fillB(curState);

            }
            case 4:{
                return pourA2B(curState);

            }
            case 5:{
                return pourB2A(curState);

            }
            default:{
                return null;

            }
        }
    }
    /**
     * 功能描述: <br>
     * 〈判断状态队列中是否已经存在新状态〉
       参数         [state]
     * 返回 @return:boolean
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:37
     */
    public boolean stateExist(StateModel state){
        for (StateNode node:stateExistList) {
            StateModel model  = node.getState();
            if ((model.getbVol() == state.getbVol()) && (model.getaVol() == state.getaVol())) {
                return true;
            }

        }
        return false;
    }
    /**
     * 功能描述: <br>
     * 〈判断已经搜索过的表中是否已经存在新状态〉
       参数         [state]
     * 返回 @return:boolean
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 19:52
     */
    public boolean existInAlreadySearchStateList(StateModel state){
        for (StateNode node:alreadySearchList) {
            StateModel model  = node.getState();
            if ((model.getbVol() == state.getbVol()) && (model.getaVol() == state.getaVol())) {
                return true;
            }

        }
        return false;
    }
/**
 * 功能描述: <br>
 * 〈清空容器a〉
   参数         [curState]
 * 返回 @return:model.StateModel
 * 作者 @Author:xiaoge
 * 时间 @Date: 2018/8/6 15:37
 */
    public StateModel emptyA(StateModel curState){
        StateModel tmp = new StateModel(curState.getaVol(),curState.getbVol());
        tmp.setaVol(0);
        return tmp;
    }
    /**
     * 功能描述: <br>
     * 〈清空容器b〉
       参数         [curState]
     * 返回 @return:model.StateModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:38
     */
    public StateModel emptyB(StateModel curState){
        StateModel tmp = new StateModel(curState.getaVol(),curState.getbVol());
        tmp.setbVol(0);
        return tmp;
    }
    /**
     * 功能描述: <br>
     * 〈装满〉
       参数         [curState]
     * 返回 @return:model.StateModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:38
     */
    public StateModel fillA(StateModel curState){
        StateModel tmp = new StateModel(curState.getaVol(),curState.getbVol());
        tmp.setaVol(MAX_A);
        return tmp;
    }
    /**
     * 功能描述: <br>
     * 〈装满b〉
       参数         [curState]
     * 返回 @return:model.StateModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:38
     */
    public StateModel fillB(StateModel curState){
        StateModel tmp = new StateModel(curState.getaVol(),curState.getbVol());
        tmp.setbVol(MAX_B);
        return tmp;
    }
    /**
     * 功能描述: <br>
     * 〈把a倒入b中〉
       参数         [curState]
     * 返回 @return:model.StateModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:38
     */
    public StateModel pourA2B(StateModel curState){
        StateModel tmp = new StateModel(curState.getaVol(),curState.getbVol());
        if ((curState.getaVol() + curState.getbVol())<=MAX_B){
            tmp.setaVol(0);
            tmp.setbVol(curState.getaVol() + curState.getbVol());
        }else {
            tmp.setaVol(curState.getaVol() + curState.getbVol()-MAX_B);
            tmp.setbVol(MAX_B);
        }
        return tmp;
    }
    /**
     * 功能描述: <br>
     * 〈把b导入a中〉
       参数         [curState]
     * 返回 @return:model.StateModel
     * 作者 @Author:xiaoge
     * 时间 @Date: 2018/8/6 15:39
     */
    public StateModel pourB2A(StateModel curState){
        StateModel tmp = new StateModel(curState.getaVol(),curState.getbVol());
        if ((curState.getaVol() + curState.getbVol())<=MAX_A){
            tmp.setaVol(curState.getaVol() + curState.getbVol());
            tmp.setbVol(0);
        }else {
            tmp.setaVol(MAX_A);
            tmp.setbVol(curState.getaVol() + curState.getbVol()-MAX_A);
        }
        return tmp;
    }

}
