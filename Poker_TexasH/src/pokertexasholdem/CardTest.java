package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokertexasholdem.Card.Ranks;
import pokertexasholdem.Card.Suits;

public class CardTest {
    
    private static Card card;
    private static Ranks rank3;
    private static Suits suitD;
    private static Suits suitC;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        
        rank3 = Ranks.RANK3;
        suitD = Suits.DIAMONDS;
        suitC = Suits.CLUBS; 
        
    }
    
    @Before
    public void setUp() throws Exception {
        card = new Card( rank3, suitC );
    }
    
    @Test
    public void testCard() {
        //if card was created (is not null)
        assertNotNull( card );
        //if card was created with proper rank
        assertEquals( rank3, card.getRank() );
        //if cards suit is different than another suit
        assertNotEquals( suitD, card.getSuit() );
        //if after change suits match
        card.setSuit(suitD);
        assertEquals( suitD, card.getSuit() );
        //if card's String representation is as expected
        assertEquals( "3 of Diamonds", card.toString() );        
    }
   
}
