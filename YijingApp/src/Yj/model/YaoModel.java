package Yj.model;

public class YaoModel {
	private int type=0;						//0阳 奇数为阳:一    			1阴 偶数为阴：--
	private String name;					//爻名     0 阳 			1阴 
	private int value;						//爻值		6,7,8,9
	
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
