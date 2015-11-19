package pokertexasholdem;

import java.util.ArrayList;
import java.util.Random;

public class Deck {
    
    public ArrayList<Card> deckList;
    public Card[] dealArray;
    private Random random;
    
    public Deck() {
        setDeckList( new ArrayList<Card>() );
        
        for( Card.Ranks rank: Card.Ranks.values() ) {
            for( Card.Suits suit: Card.Suits.values() ) {
                addCardToDeck( rank, suit );
            }
        }
    }
    
    //shuffles two halves (indexes 0-25 with 26-52) 
    //by swapping card from one with one from the other
    public void shuffleDeck( int times ) {        
        setRandom( new Random() );
        int shuffleCard1;
        int shuffleCard2;
        Card tempCard;
        
        int deckHalf = ( deckList.size() ) / 2;
        
        for( int i=0; i<(times*deckList.size()); i++ ) {            
            shuffleCard1 = random.nextInt( deckHalf - 1 );
            shuffleCard2 = ( random.nextInt( deckHalf ) + deckHalf );
            
            tempCard = deckList.get(shuffleCard1);
            deckList.set( shuffleCard1, deckList.get(shuffleCard2) );
            deckList.set( shuffleCard2, tempCard );
        }        
    }
    
    public Card[] dealCardsFromDeck( int cardsAmount ) {
        Card[] dealArray = new Card[cardsAmount];
        for( int i=0; i<cardsAmount; i++ ) {
            dealArray[i] = drawFromDeck();
        }
        return dealArray;
    }
    
    //draw from deckList at index 0, return Card while removing it from deckList
    public Card drawFromDeck() {
        return deckList.remove(0);
    }
    
    public void addCardToDeck( Card.Ranks rank, Card.Suits suit ) {
        deckList.add( new Card( rank, suit ) );
    }
    
    public ArrayList<Card> getDeckList() {
        return deckList;
    }

    public void setDeckList( ArrayList<Card> deckList ) {
        this.deckList = deckList;
    }
    
    public void setRandom( Random random ) {
        this.random = random;
    }

}
