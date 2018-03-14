package sk.model;

import java.util.ArrayList;
import java.util.List;

public class MyList {

	List<Node> nodeList; 
	public void createEmptylist() {
		nodeList = new ArrayList<Node>();
		Node node = new Node();
		node.nextNode = null;
	}
	public int getLength() {
		Node tmpNode = nodeList.get(0);
		int count = 0;
		while (tmpNode != null) {
			count++;
			tmpNode = tmpNode.nextNode;
		}
		return count;
	}
	public Node findNodes(int location) {//location 0,1,2,3...
		Node tmpNode = nodeList.get(0);
		int i = 0;
		while (tmpNode != null && (i<location)) {
			i++;
			tmpNode = tmpNode.nextNode;
		}
		if (i == location) {
			return tmpNode;
		}
		return null;
	}
	public int findLocation(int data) {
		if (getLength()<1) {
			return -1;
		}
		int count = 0;
		Node tmpNode = nodeList.get(0);
		while (tmpNode.nextNode != null) {
			if (tmpNode.data == data ) {
				break;
			}
			count++;
			tmpNode = tmpNode.nextNode;
			
		}
		if (count>nodeList.size()-1) {
			return -1;
		}
		return count;
		
	}
	public void insert(int data,int loca) {
		Node node = new Node();
		node.data = data;
		
		if (loca == 0) {
			Node headNode = nodeList.get(0);
			node.nextNode = headNode;
			return;
		}
		
		Node preNode = findNodes(loca-1);
		if (preNode != null) {
			node.nextNode = preNode.nextNode;
			preNode.nextNode = node;
		}
	}
	public void delete(int location) {
		if (getLength()< 1) {
			return;
		}
		if (location == 0) {
			Node tmpNode = nodeList.get(0);
			tmpNode = tmpNode.nextNode;
			return;
		}
		Node preNode = findNodes(location-1);
		if (preNode == null) {
			return;
		}
		if (preNode.nextNode == null) {
			return;
		}
		preNode.nextNode = preNode.nextNode.nextNode;
		
	}
}
