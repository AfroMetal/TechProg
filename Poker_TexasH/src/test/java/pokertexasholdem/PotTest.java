package pokertexasholdem;

import static org.junit.Assert.*;

import java.net.Socket;
import java.util.Set;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

public class PotTest {

	private static Pot pot;
	private Set<Player> contributorsSet;
	private static Player player,player1;
	private static String name;
	private static int money;
	private static int actualMoney;
	private static int smallBlind;
	private static int bigBlind;
	private static Socket socket = null;
	private static int partialBet;
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		name = "Player1";
		money = 500;
		actualMoney = money;
		smallBlind = 10;
		bigBlind = 20;
		partialBet= 10;
		
	}

	@Before
	public void setUp() throws Exception {
		pot = new Pot(0);
		player = new Player(name, socket, money);
		player1 = new Player(name, socket, money);
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
		
		pot.addContributor(player);
		assertNotNull(pot.hasContributed(player));
		assertNotNull(pot.split(player, partialBet));
		
		pot.addContributor(player1);
		assertEquals(20, pot.getValue());
		
		assertNotNull(pot.getContributorsSet());
		
		assertEquals("Pot: 20", pot.toString());
	}

}
