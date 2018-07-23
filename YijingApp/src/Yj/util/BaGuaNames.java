package Yj.util;

public enum BaGuaNames {
    乾("乾", 0), 兑("兑", 1), 离("离", 2), 震("震", 3)
    ,巽("巽", 4), 坎("坎", 5), 艮("艮", 6), 坤("坤", 7);
    // ��Ա����
	private String name;
    private int index;

    // ���췽��
    private BaGuaNames(String name, int index) {
        this.name = name;
        this.index = index;
    }

    // ��ͨ����
    public static String getName(int index) {
        for (BaGuaNames c : BaGuaNames.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }

 // ��ͨ����
    public static int getIndex(String names) {
        for (BaGuaNames c : BaGuaNames.values()) {
            if (c.getName().equals(names)) {
                return c.index;
            }
        }
        return 0;
    }
    
    
    // get set ����
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }
}
