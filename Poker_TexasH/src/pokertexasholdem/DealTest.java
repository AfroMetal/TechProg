package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class DealTest {
    
    private static Card[] cardArray;
    private static Deck deck;
    private static Deal deal;
    private static int playersAmount;
    private static int cardsAmount;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        playersAmount = 4;
        cardsAmount = 2;
    }
    
    @Before
    public void setUp() throws Exception {
        deck = new Deck();
        deal = new Deal( deck );   
        deal.dealToPlayers( playersAmount, cardsAmount );
    }
    
    @Test
    public void testDealToPlayers() {
        
        deck = new Deck();
        int index = 0;
        for( int i=0; i<playersAmount; i++ ) {
            cardArray = deal.playersMap.get("Player"+i).getHand();
            for( int j=0; j<cardsAmount; j++ ) {
                //if every card was dealt
                assertNotNull( cardArray[j] );
                //if cards were dealt in proper order to 
                //      (playerAmount) players, (cardsAmount) cards to each player
                //meaning (for 4 players, 2 cards): 
                //      1st&2nd card to Player0, 3rd&4th card to Player1 ...
                assertEquals( deck.getDeckList().get(index).toString(), cardArray[j].toString() );
                index++;
            }
        }
        
    }
        
}
