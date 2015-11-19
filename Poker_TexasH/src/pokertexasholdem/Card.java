package pokertexasholdem;

public class Card {

    public enum Ranks { 
        RANK2("2"), RANK3("3"), RANK4("4"), RANK5("5"), RANK6("6"), 
        RANK7("7"), RANK8("8"), RANK9("9"), RANK10("10"), 
        RANKJ("Jack"), RANKQ("Queen"), RANKK("King"), RANKA("Ace");
        private String rankString;
        private Ranks( String rankString ) {
            this.rankString = rankString;
        }
        @Override
        public String toString() {
            return rankString;
        }
    }
    public enum Suits { 
        CLUBS("Clubs"), DIAMONDS("Diamonds"), HEARTS("Hearts"), SPADES("Spades");
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
        return ( rank.toString() + " of " + suit.toString() );
    }
}
