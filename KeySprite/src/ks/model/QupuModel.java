package ks.model;

public class QupuModel {

	String name;		//曲谱名
	String quickKey;	//快捷键
	int 	delayTime;	//延迟时间
	
	public  QupuModel(String na,String qk,int time) {
		this.name = na;
		this.quickKey = qk;
		this.delayTime = time;
	}
	public String getName() {
		return this.name;
	}
	public void setName(String na) {
		this.name = na;
	}
	public String getQuickKey() {
		return this.quickKey;
	}
	public void setQuickKey(String va) {
		this.quickKey = va;
	}
	public int getDelayTime() {
		return this.delayTime;
	}
	public void setDelayTime(int va) {
		this.delayTime = va;
	}
}
