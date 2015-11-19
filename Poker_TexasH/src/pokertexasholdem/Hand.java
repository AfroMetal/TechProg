package pokertexasholdem;

public class Hand extends Deck{

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

}
