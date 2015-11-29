package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DeckTest {
    
    private static Card.Ranks rank;
    private static Card.Suits suit;
    private static Deck deck;
    private static Deck deck2;
    //private static Card[] deal1;
    //private static Card[] deal2;

    private static int shuffleTimes = 5;
    private static Card firstCard;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        rank = Card.Ranks.RANK2;
        suit = Card.Suits.CLUBS;
    }
    
    @Before
    public void setUp() throws Exception {
        deck = new Deck();
        deck2 = new Deck();
    }

    @Test
    public void testShuffleDeck() {
        //if String representations of 2 not shuffled decks are equal
        assertEquals( deck2.getDeckList().toString(), deck.getDeckList().toString() );
        //if after shuffling one deck String representations differ
        deck.shuffleDeck( shuffleTimes );     
        assertNotEquals( deck2.getDeckList().toString(), deck.getDeckList().toString() );
    
    }
    /*
    @Test
    public void testDealCardsFromDeck() {
        //if the 1st card from fresh deck is the 1st dealt card
        firstCard = deck.getDeckList().get(0);
        deal1 = deck.dealCardsFromDeck(1);
        deal2 = deck.dealCardsFromDeck(1);
        assertSame( firstCard, deal1[0] );
        //if String representations of this cards match
        assertEquals( firstCard.toString(), deal1[0].toString() );
        //if the 1st and 2nd dealt cards differ 
        //(card is really removed from deck after deal)
        assertNotSame( deal1, deal2 );
        //if 1st and 2nd dealt cards String representations differ
        assertNotEquals( deal1[0].toString(), deal2[0].toString() );
        
    }
    */
    @Test
    public void testDrawFromDeck() {
        //if the 1st card from fresh deck is the 1st drawn card
        firstCard = deck.getDeckList().get(0);
        assertSame( firstCard, deck.drawFromDeck() );
        //if the 1st card from fresh deck is not the 2nd card drawn from deck
        assertNotSame( firstCard, deck.drawFromDeck() );
        
    }
    
    @Test
    public void testAddCardToDeck() {
        //if two fresh decks String representations equals
        assertEquals( deck2.getDeckList().toString(), deck.getDeckList().toString() );
        //if two decks differ after adding one card to one of the decks
        deck.addCardToDeck( rank, suit );
        assertNotEquals( deck2.getDeckList().toString(), deck.getDeckList().toString() );
        
    }
      
}
