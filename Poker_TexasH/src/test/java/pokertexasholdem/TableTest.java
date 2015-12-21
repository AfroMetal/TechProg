package pokertexasholdem;

import static org.junit.Assert.*;

import java.net.Socket;
import java.util.HashMap;

import org.junit.BeforeClass;
import org.junit.Test;

public class TableTest {
	
	private static Table table;
	private static int money;
	private static int smallBlind;
	private static HashMap<Socket, String> playersSocketsNames;
	
	
	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		playersSocketsNames = new  HashMap<Socket, String>();
		playersSocketsNames.put(null, "A");
		playersSocketsNames.put(null, "B");
		
		money = 100;
		smallBlind = 20;
	}

	@Test
	public void test() {
		table = new Table(playersSocketsNames, money, smallBlind);
		
		table.run();
		
		assertNotNull(table.getPlayersList());
		assertNotNull(table.getSmallBlind());
		assertEquals(20, table.getSmallBlind());
		assertNotNull(table.getBigBlind());
		assertEquals(40, table.getBigBlind());
		//assertNotNull(table.getDeck());
		assertNotNull(table.getBoard());
		assertNotNull(table.getDealer());
		assertNotNull(table.getDealerPosition());
		assertNotNull(table.getActivePlayers());
		//assertNotNull(table.getActor());
		assertNotNull(table.getActorPosition());
		assertNotNull(table.getPots());
		assertNotNull(table.getMinBet());
		assertNotNull(table.getBet());
		
	}

}
