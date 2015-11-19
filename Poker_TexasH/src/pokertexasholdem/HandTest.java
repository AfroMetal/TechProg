package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pokertexasholdem.Hand;

public class HandTest {
    
    private static Hand h1;
    private static Hand h2;
    private static int cardsAmount;
    private static Deck d1;
    private static Deck d2;
    
    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        d1 = new Deck();
        d2 = new Deck();
        cardsAmount = 3;
    }
    
    @Before
    public void setUp() throws Exception {
        h1 = new Hand( cardsAmount, d1 );
    }
    
    @Test
    public void testHand() {
        //if 3 cards are really dealt to hand
        assertNotNull( h1.getHand()[0] );
        assertNotNull( h1.getHand()[1] );
        assertNotNull( h1.getHand()[2] );
        //if not created Hand h2 is null
        assertNull( h2 );
        //if after creation Hand h2 is not null
        h2 = new Hand( cardsAmount, d2 );
        assertNotNull( h2 );
    }
    
}
