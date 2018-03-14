package sk.model;

import java.util.ArrayList;
import java.util.List;


public class MyStack {

	List<Node> nodeList; 
	int length;
	
	public void  createEmptyStack() {
		nodeList  = new ArrayList<Node>(); 
	}
	public boolean isEmpty() {
		if (nodeList.isEmpty()) {
			return true;
		}
		return false;
	}
	public void isFull() {
		
	}
	public void push(int data1) {
		Node newNode = new Node();
		newNode.data = data1;
		nodeList.add(newNode);
		
	}
	public void pop() {
		if (nodeList.isEmpty()) {
			return;
		}
		int last = nodeList.size();
		nodeList.remove(last-1);
	}
	public void printStack() {
		for (int i = 0; i < nodeList.size(); i++) {
			System.out.println(nodeList.get(i).data);
		}
	}
}

