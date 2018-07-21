package pk.model;

public enum PukeTypeEnum {
//	黑桃、红桃、草花和方块 小王 大王
	spades(1), hearts(2) , clubs(3), diamonds(4),blackJoker(5),redjoker(6);
	int value;
	PukeTypeEnum(int v){
		this.value = v;
	}
	public int getValue() {
		return this.value;
	}
	public static PukeTypeEnum getByValue(int value) {  
        for(PukeTypeEnum typeEnum : PukeTypeEnum.values()) {  
            if(typeEnum.value == value) {  
                return typeEnum;  
            }  
        }  
        throw new IllegalArgumentException("No element matches " + value);  
    }  
}
