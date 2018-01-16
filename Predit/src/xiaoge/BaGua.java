package xiaoge;

public class BaGua {
	String name;
	int value;
	public BaGua(int number){
		this.value = number;
		this.name = BaGuaNames.getName(number);
	}
	public BaGua(String str){
		this.name = str;
		this.value = BaGuaNames.getIndex(str);
	}
	public BaGua(Yang yan1,Yang yan2,Yang yan3){
		//yan1,yan2,yan3  从高位到低位
		this.value = yan3.type*1+yan2.type*2+yan1.type*4;
		this.name = BaGuaNames.getName(this.value);
	}
}
