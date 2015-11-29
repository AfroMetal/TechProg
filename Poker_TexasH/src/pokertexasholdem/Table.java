package pokertexasholdem;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Table {
	
	/** List of Players at a table */
	private List<Player> playersList;
	
	private int smallBlind;
	private int bigBlind;
	
	/** Deck used in current hand */
	private Deck deck;
	
	/** Community cards */
	private List<Card> board;
	
	/** Current dealer player */
	private Player dealer;
	
	/** Current dealer player position */
	private int dealerPosition;
	
	/** Number of active Players (that still have money) */
	private int activePlayersNum;
	
	/** List of Players that can afford at least big blind */
	private List<Player> activePlayers;
	
	/** List of pots (main and side pots if exist) */
	private List<Pot> pots;
	
	/** Minimum bet in current hand */
	private int minBet;
	
	/** Current bet in current hand */
	private int bet;
	
	public Table( int playersAmount, int money, int smallBlind ) {
		this.smallBlind = smallBlind;
		bigBlind = smallBlind * 2;
		board = new ArrayList<>();
		playersList = new ArrayList<>();
		
		String name;
		for( int i=0; i<playersAmount; i++ ) {
			name = "Player" + i;
			playersList.add( new Player(name, money) );
		}
		
		Random random = new Random();
		dealerPosition = random.nextInt(playersAmount);
		dealer = playersList.get(dealerPosition);
	}
	
	public void run() {
		while(true) {
			activePlayersNum = 0;
			for( Player player : playersList ) {
				if( player.getMoney() >= bigBlind ) {
					activePlayersNum++;
				}
			}
			
			if( activePlayersNum > 1 ) {
				playRound();
			}
			else {
				break;
			}
		}
		
		//No more players to play.
		//TODO: implement whatever happens now
	}
	
	private void playRound() {
		resetRound();
		
		
		
	}
	
	private void resetRound() {
		//Preparing table for next hand
		board.clear();
		pots.clear();
		//Rotate dealer
		dealerPosition = (dealerPosition+1) % activePlayers.size();
		dealer = activePlayers.get(dealerPosition);
		//Shuffle deck
		deck = new Deck();
		deck.shuffleDeck(3);
		//Set bets
		minBet = bigBlind;
		bet = minBet;
	}
}
