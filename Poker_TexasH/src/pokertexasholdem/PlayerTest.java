package pokertexasholdem;

import static org.junit.Assert.*;

import java.net.Socket;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import pokertexasholdem.Card.Ranks;
import pokertexasholdem.Card.Suits;

public class PlayerTest {

	private static Player player;
	private static String name;
	private static int money;
	private static int actualMoney;
	private static int smallBlind;
	private static int bigBlind;
	private static Card[] cards1;
	private static Card[] cards2;
	private static Hand hand;
	private static Socket socket = null;

	@BeforeClass
	public static void setUpBeforeClass() throws Exception {
		name = "Player1";
		money = 500;
		actualMoney = money;
		smallBlind = 10;
		bigBlind = 20;
	}

	@Before
	public void setUp() throws Exception {
		player = new Player(name, socket, money);
	}

	@Test
	public void testSetGetHandCards() {
		cards1 = new Card[2];
		cards1[0] = new Card(Ranks.RANK10, Suits.CLUBS);
		cards1[1] = new Card(Ranks.RANK3, Suits.DIAMONDS);
		player.setHandCards(cards1);
		assertEquals(cards1.toString(), player.getHandCards().toString());
	}

	@Test
	public void testSetGetHand() {
		cards2 = new Card[2];
		cards2[0] = new Card(Ranks.RANK8, Suits.SPADES);
		cards2[1] = new Card(Ranks.RANKK, Suits.HEARTS);
		hand = new Hand(cards2);
		player.setHand(hand);
		assertEquals(hand.toString(), player.getHand().toString());
	}

	@Test
	public void testPlayer() {
		assertFalse(player.hasFolded());
		player.fold();
		assertTrue(player.hasFolded());
		player.resetPlayer();
		assertFalse(player.hasFolded());
		assertEquals(0, player.getBet());

		player.setBet(100);
		assertEquals(100, player.getBet());

		player.resetPlayer();
		player.pay(200);
		actualMoney -= 200;
		assertEquals(actualMoney, player.getMoney());

		player.smallBlind(smallBlind);
		actualMoney -= smallBlind;
		assertEquals(actualMoney, player.getMoney());
		assertEquals(smallBlind, player.getBet());
		assertEquals(Action.SMALL_BLIND, player.getLastAction());

		player.resetPlayer();
		player.bigBlind(bigBlind);
		actualMoney -= bigBlind;
		assertEquals(actualMoney, player.getMoney());
		assertEquals(bigBlind, player.getBet());
		assertEquals(Action.BIG_BLIND, player.getLastAction());

		player.win(money);
		actualMoney += money;
		assertEquals(actualMoney, player.getMoney());

		assertEquals(name, player.toString());
	}
}
