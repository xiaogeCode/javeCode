package xiaoge;

import java.util.Random;

public class CeSuan {

	Yang baGuaData[] = new Yang[3];
	Yang liuSiGua[] = new Yang[6];
	Yang currYan;
	public CeSuan(){
		
	}
	//
	public  void newGua(){
		for(int i=0;i<3;i++){
			newYang(50-1,1);
			baGuaData[i]=currYan;
			liuSiGua[i]=currYan;
		}
		//内卦
		BaGua tmp1 = new BaGua(baGuaData[0],baGuaData[1],baGuaData[2]);
		System.out.println("bagua  "+tmp1.name);
		System.out.println("bagua  "+tmp1.value);
		
		//外卦
		for(int i=0;i<3;i++){
			newYang(50-1,1);
			baGuaData[i]=currYan;
			liuSiGua[i+3]=currYan;
		}
		BaGua tmp2 = new BaGua(baGuaData[0],baGuaData[1],baGuaData[2]);
		LiuSiGua tmp3 = new LiuSiGua(liuSiGua[0],liuSiGua[1],liuSiGua[2],liuSiGua[3],liuSiGua[4],liuSiGua[5]);
		System.out.println("bagua  "+tmp2.name);
		System.out.println("bagua  "+tmp2.value);
		System.out.println("64gua  "+tmp3.shangGua.name);
		System.out.println("64gua  "+tmp3.xiaGua.name);
	}
	//对一个数字进行三次运算
	public  void newYang(int number,int seq){
		Random random = new Random();
		//x 取值范围: 1 -(number-1)
		int x =random.nextInt(number-1)+1;
		
		int tempA = x;
		int tempB = number - x;
		
		//System.out.println("tempA  "+tempA);
		//System.out.println("tempB  "+tempB);
		
		int x1 =(tempA-1)/4;
		int x2 =(tempA-1)%4;
		if (x2 ==0){
			x1 = (x1>0?x1-1:0);
			//x1= x1-1;
		}
		int y1 = tempB/4;
		int y2 = tempB%4;
		if (y2 ==0){
			y1 = (y1>0?y1-1:0);
			//y1= y1-1;
		}
		if(seq<3){
			
			newYang((x1+y1)*4,seq+1);
			
		}else{
		
			System.out.println("  "+(x1+y1));
			Yang tmp = new Yang(x1+y1);
			currYan = tmp;
			System.out.println("  "+tmp.name);
		}
		
		
	}	
}
