package Yj.model;


public class LiuSiGuaModel {
	private int id;							//卦在数据库中的id
	private String name;					//卦名
	private int value;						//卦值
	private String describle;				//卦的描述
	private String xiaGuaName;				//下卦 /内卦
	private String shangGuaName;			//上卦  /外卦
	private BaGuaModel xiaGua;				//
	private BaGuaModel shangGua;			//
	
	public int getId() {
		return this.id;
	}
	public void setId(int na) {
		this.id = na;
	}
	
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
	
	public String getDescrible() {
		return this.describle;
	}
	public void setDescrible(String na) {
		this.describle = na;
	}
	
	public String getXiaGuaName() {
		return this.xiaGuaName;
	}
	public void setXiaGuaName(String va) {
		this.xiaGuaName = va;
	}
	
	public String getShangGuaName() {
		return this.shangGuaName;
	}
	public void setShangGuaName(String va) {
		this.shangGuaName = va;
	}
}
