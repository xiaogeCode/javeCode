package sk.cmd;

import sk.model.MyStack;

public class StackMain {
	public static void main(String[] args) {
		MyStack stack = new MyStack();
		stack.createEmptyStack();
		stack.push(2);
		stack.push(3);
		stack.printStack();
		stack.pop();
		stack.printStack();
	}
}
