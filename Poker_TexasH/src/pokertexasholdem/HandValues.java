package pokertexasholdem;

public enum HandValues {
    
    STRAIGHT_FLUSH("a Straigh Flush", 8),
    FOUR_OF_A_KIND("Four of a Kind", 7),
    FULL_HOUSE("a Full House", 6),
    FLUSH("a Flush", 5),
    STRAIGHT("a Straight", 4),
    THREE_OF_A_KIND("Three of a Kind", 3),
    TWO_PAIR("Two Pairs", 2),
    ONE_PAIR("One Pair", 1),
    HIGH_CARD("a High Card", 0);
    
    private String valueString;
    private int rate;
    
    HandValues( String valueString, int rate ) {
        this.valueString = valueString;
        this.rate = rate;
    }

    public int getRate() {
        return rate;
    }

    public String getValueString() {
        return valueString;
    }

}
