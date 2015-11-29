package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

public class PotTest {
	
	private static Pot pot;
	
	@Before
	public void setUp() throws Exception {
		pot = new Pot();
	}
	
	@Test
	public void test() {
		assertEquals(0, pot.getPot());
		
		pot.addToPot(20);
		assertEquals(20, pot.getPot());
		assertEquals("Current pot = 20", pot.toString());
		
		pot.clearPot();
		assertEquals(0, pot.getPot());
		
		pot.setPot(40);
		assertEquals(40, pot.getPot());
	}

}
