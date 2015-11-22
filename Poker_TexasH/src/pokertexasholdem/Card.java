package pokertexasholdem;

public class Card {

    public enum Ranks { 
        RANK2("2", 0), RANK3("3", 1), RANK4("4", 2), RANK5("5", 3), RANK6("6", 4), 
        RANK7("7", 5), RANK8("8", 6), RANK9("9", 7), RANK10("10", 8), 
        RANKJ("Jack", 9), RANKQ("Queen", 10), RANKK("King", 11), RANKA("Ace", 12);
        
        private String rankString;
        private int rate;
        
        private Ranks( String rankString, int rate ) {
            this.rankString = rankString;
            this.rate = rate;
        }
        public int getRate() {
            return rate;
        }
        @Override
        public String toString() {
            return rankString;
        }
    }
    public enum Suits { 
        SPADES("of Spades"), CLUBS("of Clubs"), DIAMONDS("of Diamonds"), HEARTS("of Hearts");
        
        private String suitString;
        
        private Suits( String suitString ) {
            this.suitString = suitString;
        }
        @Override
        public String toString() {
            return suitString;
        }
    }
    
    private Ranks rank;
    private Suits suit;
    
    public Card( Ranks rank, Suits suit ) {
        this.setRank(rank);
        this.setSuit(suit);
    }
    
    public int getRate() {
        return this.getRank().getRate();
    }
    
    public Ranks getRank() {
        return rank;
    }

    public void setRank( Ranks rank ) {
        this.rank = rank;
    }

    public Suits getSuit() {
        return suit;
    }

    public void setSuit( Suits suit ) {
        this.suit = suit;
    }
    
    @Override
    public String toString() {
        return ( rank.toString() + " " + suit.toString() );
    }
}
