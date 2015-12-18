package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PotTest {

	private static Pot pot;

	@Before
	public void setUp() throws Exception {
		pot = new Pot(0);
	}

	@Test
	public void test() {
		assertEquals(0, pot.getBet());

		pot.addToPot(20);
		assertEquals(20, pot.getBet());

		pot.clearPot();
		assertEquals(0, pot.getBet());

		pot.setPot(40);
		assertEquals(40, pot.getBet());
	}

}
