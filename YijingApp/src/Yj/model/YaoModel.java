package Yj.model;

public class YaoModel {
	private int type=0;						//0�� ����Ϊ��:һ    			1�� ż��Ϊ����--
	private String name;					//س��     0 �� 			1�� 
	private int value;						//سֵ		6,7,8,9
	
	public String getName() {
		return this.name;
	}
	public void setName(String na) {
		this.name = na;
	}
	public int getValue() {
		return this.value;
	}
	public void setValue(int va) {
		this.value = va;
	}
	public int getType() {
		return this.type;
	}
	public void setType(int va) {
		this.type = va;
	}

}
