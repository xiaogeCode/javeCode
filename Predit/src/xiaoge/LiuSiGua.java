package xiaoge;

public class LiuSiGua {

	String name;
	int value;
	BaGua xiaGua;				//���� /����
	BaGua shangGua;				//����  /����
	public LiuSiGua(int number){
		this.value = number;
		//����
		BaGua tmp1 = new BaGua(number/8);
		xiaGua = tmp1;
		//����
		BaGua tmp2 = new BaGua(number%8);
		shangGua = tmp2;
	}
	public LiuSiGua(String str){
		this.name = str;
	}
	public LiuSiGua(Yang yan1,Yang yan2,Yang yan3,Yang yan4,Yang yan5,Yang yan6){
		//yan1,yan2,yan3  �Ӹ�λ����λ
		this.value = yan6.type*1+yan5.type*2+yan4.type*4+yan3.type*8+yan2.type*16+yan1.type*32;
		//����
		BaGua tmp1 = new BaGua(yan1,yan2,yan3);
		xiaGua = tmp1;
		//����
		BaGua tmp2 = new BaGua(yan4,yan5,yan6);
		shangGua = tmp2;
	}
	
}
