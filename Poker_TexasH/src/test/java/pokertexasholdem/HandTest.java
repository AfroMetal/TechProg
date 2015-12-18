package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import pokertexasholdem.Hand;

public class HandTest {

	private static Hand h1;
	private static Hand h2;
	private static Deck d1;
	private static Deck d2;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		d1 = new Deck();
		d2 = new Deck();
	}

	@Before
	public void setUp() throws Exception {
		Card[] cards = new Card[2];
		cards[0] = d1.drawFromDeck();
		cards[1] = d1.drawFromDeck();
		h1 = new Hand(cards);
	}

	@Test
	public void testHand() {
		// if 3 cards are really dealt to hand
		assertNotNull(h1.getHand()[0]);
		assertNotNull(h1.getHand()[1]);
		// if not created Hand h2 is null
		assertNull(h2);
		// if after creation Hand h2 is not null
		Card[] cards2 = new Card[2];
		cards2[0] = d2.drawFromDeck();
		cards2[1] = d2.drawFromDeck();
		h2 = new Hand(cards2);
		assertNotNull(h2);
	}

}
