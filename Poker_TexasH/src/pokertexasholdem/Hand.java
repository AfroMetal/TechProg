package pokertexasholdem;

public class Hand {

    private Card[] hand; 
    
    public Hand( int cardsAmount, Deck d ) {
        setHand( d.dealCardsFromDeck( cardsAmount ) );
    }

    public Card[] getHand() {
        return hand;
    }

    public void setHand( Card[] hand ) {
        this.hand = hand;
    }
    
    @Override
    public String toString() {
        StringBuilder sBuilder = new StringBuilder();
        for( int i = 0; i < hand.length; i++ ) {
            sBuilder.append( hand[i].toString() );
            if( i != hand.length - 1) {
                sBuilder.append('\n');
            }
        }
        return sBuilder.toString();
    }
}
