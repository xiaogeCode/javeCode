package pk.model;

public enum PukeListTypeEnum {

	single(1),//单根
	paire(2), //对子
	pairDargon(3),//连对
	kingBoom(4),//王炸
	three(5),//三带一  
	threeOne(6),//三带一  
	threePair(7),//三带一对
	threeOneDragon(8),//三带一连续
	threePairDragon(9),//三带一对连续
	boom(10),//四个相同的炸弹
	dragon(11),//顺子
	mix(-1);//杂牌
	
	int value;
	PukeListTypeEnum(int va){
		this.value = va;
	}
	
}
