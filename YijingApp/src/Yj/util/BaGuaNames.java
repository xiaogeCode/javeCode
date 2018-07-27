package Yj.util;

public enum BaGuaNames {
    /*八卦之乾* */
    乾("乾", 0),
    /*八卦之兑* */
    离("离", 2),
    /*八卦之离* */
    兑("兑", 1),
    /*八卦之震* */
    震("震", 3),
    /*八卦之巽* */
    巽("巽", 4),
    /*八卦之坎* */
    坎("坎", 5),
    /*八卦之艮* */
    艮("艮", 6),
    /*八卦之坤* */
    坤("坤", 7);
	private String name;
    private int index;

    private BaGuaNames(String name, int index) {
        this.name = name;
        this.index = index;
    }

    public static String getName(int index) {
        for (BaGuaNames c : BaGuaNames.values()) {
            if (c.getIndex() == index) {
                return c.name;
            }
        }
        return null;
    }


    public static int getIndex(String names) {
        for (BaGuaNames c : BaGuaNames.values()) {
            if (c.getName().equals(names)) {
                return c.index;
            }
        }
        return 0;
    }
    
    
    // get set
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
