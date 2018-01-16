package xiaoge;

public class LiuSiGua {

	String name;
	int value;
	BaGua xiaGua;				//下卦 /内卦
	BaGua shangGua;				//上卦  /外卦
	public LiuSiGua(int number){
		this.value = number;
		//下卦
		BaGua tmp1 = new BaGua(number/8);
		xiaGua = tmp1;
		//上卦
		BaGua tmp2 = new BaGua(number%8);
		shangGua = tmp2;
	}
	public LiuSiGua(String str){
		this.name = str;
	}
	public LiuSiGua(Yang yan1,Yang yan2,Yang yan3,Yang yan4,Yang yan5,Yang yan6){
		//yan1,yan2,yan3  从高位到低位
		this.value = yan6.type*1+yan5.type*2+yan4.type*4+yan3.type*8+yan2.type*16+yan1.type*32;
		//下卦
		BaGua tmp1 = new BaGua(yan1,yan2,yan3);
		xiaGua = tmp1;
		//上卦
		BaGua tmp2 = new BaGua(yan4,yan5,yan6);
		shangGua = tmp2;
	}
	
}
