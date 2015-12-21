package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Test;

public class ActionTest {

	@Test
	public void test() {
		assertEquals("Bet", Action.BET.getName());
		assertEquals("calls", Action.CALL.toString());
	}

}
