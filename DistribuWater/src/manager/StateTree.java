package manager;

import model.StateModel;
import model.StateNode;

import java.time.chrono.ChronoLocalDate;

/**
 * Copyright (C), 2018-2018, XXX有限公司
 * FileName: StateNode
 * Author:   xiaoge
 * Date:     2018/8/6 11:55
 * Description: ${DESCRIPTION}
 * History:
 * <author>          <time>          <version>          <desc>
 * 作者姓名           修改时间           版本号              描述
 */
public class StateTree {
    StateNode rootNode = null;

    public StateNode getRootNode() {
        return rootNode;
    }

    public StateTree() {
    }

    public StateTree(StateNode rootNode) {
        this.rootNode = rootNode;
    }

    public void insert(StateModel model, StateNode parrent){
        if (rootNode==null){
            rootNode = new StateNode(model);
            return;
        }
        StateNode tmp = new StateNode(model);
        tmp.setParrent(parrent);
        parrent.getChilds().add(tmp);

    }
    public void delete(){}
    public void preOrder(){}
    public void midOrder(){}
    public void lastOrder(){}
    public void test(){
        StateTree tree = new StateTree();
        tree.insert(new StateModel(3,10),null);
        StateNode root = tree.getRootNode();
        tree.insert(new StateModel(4,3),root);
        StateNode child = root.getChilds().get(0);
        tree.insert(new StateModel(5,5),child);
        StateNode childchild = root.getChilds().get(0).getChilds().get(0);
        System.out.println("childchild: "+childchild.getState().getaVol()+"  "+childchild.getState().getbVol());
        System.out.println("childchild parent : "+childchild.getParrent().getState().getaVol()+"  "+childchild.getParrent().getState().getbVol());
        System.out.println("child: "+child.getState().getaVol()+"  "+child.getState().getbVol());
        System.out.println("parnt: "+root.getState().getaVol()+"  "+root.getState().getbVol());
    }
    public static void printPath(StateNode child){
        StateNode tmp = child;
        while (tmp!=null){
            System.out.println(tmp.getState().getaVol()+"  "+ tmp.getState().getbVol());
            tmp = tmp.getParrent();
        }
    }
}
