package pokertexasholdem;

import java.net.Socket;

public class Player {
	private final String name;
	
	private final Socket socket;

	private Hand hand;

	private int money;

	private boolean hasFolded;

	private int bet;

	private Action lastAction;

	public Player(String name, Socket socket, int money) {
		this.name = name;
		this.socket = socket;
		setMoney(money);
		setBet(0);

		hand = new Hand();
		hasFolded = false;
		
		System.out.println("[PLAYER] " + name + " joined table.");
	}

	public void resetPlayer() {
		hasFolded = false;
		bet = 0;
		lastAction = null;
	}

	public void smallBlind(int smallBlind) {
		setLastAction(Action.SMALL_BLIND);
		money -= smallBlind;
		bet += smallBlind;
	}

	public void bigBlind(int bigBlind) {
		setLastAction(Action.BIG_BLIND);
		money -= bigBlind;
		bet += bigBlind;
	}

	public void win(int amount) {
		money += amount;
	}

	public void fold() {
		setLastAction(Action.FOLD);
		hasFolded = true;
	}

	public boolean hasFolded() {
		return hasFolded;
	}

	public void pay(int amount) {
		money -= amount;
	}

	public int getMoney() {
		return money;
	}

	public void setMoney(int money) {
		this.money = money;
	}

	public Action getLastAction() {
		return lastAction;
	}

	public void setLastAction(Action lastAction) {
		this.lastAction = lastAction;
	}

	public String getName() {
		return name;
	}

	public Socket getSocket() {
	        return socket;
	}
	    
	public Hand getHand() {
		return hand;
	}

	public void setHand(Hand hand) {
		this.hand = hand;
	}

	public Card[] getHandCards() {
		return hand.getHand();
	}

	public void setHandCards(Card[] cards) {
		hand.setHand(cards);
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	@Override
	public String toString() {
		return getName();
	}
}
