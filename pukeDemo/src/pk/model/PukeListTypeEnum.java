package pk.model;

public enum PukeListTypeEnum {
    //单根
	single(1),
    //对子
	paire(2),
    //连对
	pairDargon(3),
    //王炸
	kingBoom(4),
    //三带一
	three(5),
    //三带一
	threeOne(6),
    //三带一对
	threePair(7),
    //三带一连续
	threeOneDragon(8),
    //三带一对连续
	threePairDragon(9),
    //四个相同的炸弹
	boom(10),
    //顺子
	dragon(11),
    //杂牌
	mix(-1);
	
	int value;
	PukeListTypeEnum(int va){
		this.value = va;
	}
	
}
