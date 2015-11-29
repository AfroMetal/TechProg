package pokertexasholdem;

public class Player {
	private final String name;
	
	private Hand hand;
	
	private int money;
	
	private boolean hasFolded;
	
	private int bet;
	
	private Action lastAction;
	
	public Player( String name, int money ) {
		this.name = name;
		setMoney(money);
		setBet(0);
		
		hand = new Hand();
		hasFolded = false;
	}
	
	public void resetPlayer() {
		hasFolded = false;
		bet = 0;
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
		hasFolded = true;
	}
	
	public boolean hasFolded() {
		return hasFolded;
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
		return name;
	}
}