package pokertexasholdem;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Set;
import java.util.TreeMap;

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

	/** Player to do next move */
	private Player actor;

	/** Player to do next move position */
	private int actorPosition;

	/** List of pots (main and side pots if exist) */
	private List<Pot> pots;

	/** Minimum bet in current hand */
	private int minBet;

	/** Current bet in current hand */
	private int bet;

	/**
	 * Constructor of table
	 * 
	 * @param playersAmount
	 *            how many players to create
	 * @param money
	 *            how much money give to players
	 * @param smallBlind
	 *            amount of small blind in game
	 */
	public Table(int playersAmount, int money, int smallBlind) {
		this.smallBlind = smallBlind;
		bigBlind = smallBlind * 2;
		board = new ArrayList<>();
		playersList = new ArrayList<>();
		activePlayers = new ArrayList<>();
		pots = new ArrayList<>();

		String name;
		for (int i = 0; i < playersAmount; i++) {
			name = "Player" + i;
			playersList.add(new Player(name, money));
		}

		Random random = new Random();
		dealerPosition = random.nextInt(playersAmount);
		dealer = playersList.get(dealerPosition);
		System.out.println("[TABLE] Constructed");
	}

	/**
	 * Main game loop
	 */
	public void run() {
	        System.out.println("[RUN] Starting main game loop");
		while (true) {
			activePlayersNum = 0;
			activePlayers.clear();
			System.out.println("[RUN] Active players are:");
			for (Player player : playersList) {
				player.resetPlayer();
				if (player.getMoney() >= bigBlind) {
					activePlayersNum++;
					activePlayers.add(player);
					System.out.println("[RUN]   " + player.toString());
				}
			}

			if (activePlayersNum > 1) {
			         playRound();
			         System.out.println("[RUN] Called method playRound()");
			} else {
			        System.out.println("[RUN] Only one active player");
				break;
			}
		}

		// No more players to play. Game over.
		// TODO: implement whatever happens now
	}

	/**
	 * Single round ended with showdown
	 */
	private void playRound() {
	        resetRound();
	        System.out.println("[ROUND] Reset done - new round");

		// current actor posts small blind, next one posts big blind
		postSmallBlind();
		nextActor(actor);

		postBigBlind();
		nextActor(actor);
		System.out.println("[ROUND] Blinds posted");
		
		// deal cards to active players
		dealCards();
		System.out.println("[ROUND] Cards dealt");
		
		// do first betting round
		betting();
		System.out.println("[ROUND] 1st betting ended");
		
		// FLOP, deal 3 cards to board
		dealCommunityCards(3);
		System.out.println("[ROUND] Flop dealt");
		
		betting();
		System.out.println("[ROUND] 2nd betting ended");
		
		// TURN, deal one card to board
		dealCommunityCards(1);
		System.out.println("[ROUND] Turn dealt");

		// do second betting
		betting();
		System.out.println("[ROUND] 3rd betting ended");

		// RIVER, deal one card to board
		dealCommunityCards(1);
		System.out.println("[ROUND] River dealt");

		// do third, last betting
		betting();
		System.out.println("[ROUND] 4th betting ended");

		// SHOWDOWN, evaluate players
		showdown();
		System.out.println("[ROUND] Showdown ended");

	}

	/**
	 * Prepares table for next round
	 */
	private void resetRound() {
		// Preparing table for next hand
		board.clear();
		pots.clear();
		System.out.println("[RESET] Board and pots cleared");

		// Rotate dealer
		nextDealer();
		System.out.println("[RESET] Dealer moved");
		// Set actor to player next to dealer
		nextActor(dealer);
		System.out.println("[RESET] Player set next to dealer");
		// Shuffle deck
		deck = new Deck();
		System.out.println("[RESET] Deck created");
		deck.shuffleDeck(3);
		System.out.println("[RESET] Deck shuffled");
		// Set bets
		minBet = bigBlind;
		bet = minBet;
		System.out.println("[RESET] Bets set");
	}

	/**
	 * Posts Small Blind
	 */
	private void postSmallBlind() {
		actor.smallBlind(smallBlind);
		contributePot(smallBlind);
	}

	/**
	 * Posts Big Blind
	 */
	private void postBigBlind() {
		actor.bigBlind(bigBlind);
		contributePot(bigBlind);
	}

	/**
	 * Deals 2 cards to each active player
	 */
	private void dealCards() {
		for (Player player : activePlayers) {
			Card[] cards = new Card[2];
			cards[0] = deck.drawFromDeck();
			cards[1] = deck.drawFromDeck();
			player.setHandCards(cards);
		}
	}

	/**
	 * Does the betting
	 */
	private void betting() {
		int playersToBet = activePlayersNum;

		// if it's not the first betting, reset bet and set actor to next to
		// dealer
		// if it is a first betting bet and actor were set earlier to proper
		// ones
		if (board.size() != 0) {
			bet = 0;
			nextActor(dealer);
		}

		while (playersToBet > 0) {
			Action action;

			// Actor is all-in, so he checks
			if (actor.getLastAction().equals(Action.ALL_IN)) {
				action = Action.CHECK;
				playersToBet--;
			}
			// Actor can choose how to act
			else {
				Set<Action> legalActions = getLegalActions(actor);
				action = null;// TODO: get action from Client, first send him legalActions
				playersToBet--;

				if (action == Action.CHECK) {
					// nothing to do, dude
				} else if (action == Action.CALL) {
					int toSettleBet = (bet - actor.getBet());
					actor.pay(toSettleBet);
					actor.setBet(actor.getBet() + toSettleBet);
					contributePot(toSettleBet);
				} else if (action == Action.BET) {
					int betAmount = 0;// TODO: equals to bet send by Client;
					actor.setBet(betAmount);
					actor.pay(betAmount);
					contributePot(betAmount);
					bet = betAmount;
					minBet = betAmount;
					// all players get another round
					playersToBet = activePlayersNum;
				} else if (action == Action.RAISE) {
					int raiseAmount = 0;// TODO: equals to raise send by Client
					bet += raiseAmount;
					minBet = raiseAmount;
					int toRaiseBet = (bet - actor.getBet());
					actor.setBet(bet);
					actor.pay(toRaiseBet);
					contributePot(toRaiseBet);
					// all players get another round
					playersToBet = activePlayersNum;
				} else if (action == Action.FOLD) {
					actor.setHand(null);
					activePlayers.remove(actor);
					activePlayersNum = activePlayers.size();
					actorPosition--;

					// only one player left, give him the money
					if (activePlayersNum == 1) {
						Player winner = activePlayers.get(0);
						int winAmount = getTotalPot();
						winner.win(winAmount);
						playersToBet = 0;
					}
				}
			}
			actor.setLastAction(action);
			if (playersToBet > 0) {
				// TODO: refresh board/client NOTIFY
			}
		}

		// reset bets
		for (Player player : activePlayers) {
			player.setBet(0);
			player.setLastAction(null);
		}

	}

	/**
	 * @return total amount of money bet during round
	 */
	private int getTotalPot() {
		int total = 0;
		for (Pot pot : pots) {
			total += pot.getValue();
		}
		return total;
	}

	/**
	 * Creates set of allowed actions for the player to perform
	 * 
	 * @param actor
	 *            player
	 * @return allowed actions set
	 */
	private Set<Action> getLegalActions(Player actor) {
		Set<Action> legalActions = new HashSet<>();
		// if player is all-in he can only check and wait for showdown
		if (actor.getLastAction() == Action.ALL_IN) {
			legalActions.add(Action.CHECK);
		}
		// else if player is not all-in and can something more
		else {
			// if no bet was placed yet (so no first betting as then it is equal
			// to big blind)
			if (bet == 0) {
				legalActions.add(Action.CHECK);
				legalActions.add(Action.BET);
			}
			// if there is some bet posted
			// and actor have more money than difference between his and table
			// bet
			else if ((bet - actor.getBet()) < actor.getMoney()) {
				// and actor's bet is not equal to that on table
				if (actor.getBet() < bet) {
					legalActions.add(Action.CALL);
					legalActions.add(Action.RAISE);
				}
				// or his bet is equal to that on table
				else {
					legalActions.add(Action.CHECK);
					legalActions.add(Action.RAISE);
				}
			}
			// if actor have money equal to difference between his and table bet
			else if ((bet - actor.getBet()) == actor.getMoney()) {
				legalActions.add(Action.CALL);
			}
			// and every active not all-in player can fold and go all-in
			legalActions.add(Action.ALL_IN);
			legalActions.add(Action.FOLD);
		}
		return legalActions;
	}

	/**
	 * Deals community cards amount to board
	 * 
	 * @param amount
	 *            number of cards to deal
	 */
	private void dealCommunityCards(int amount) {
		for (int j = 0; j < amount; j++) {
			board.add(deck.drawFromDeck());
		}
	}

	/**
	 * Deals money to winners
	 */
	private void showdown() {
		/**
		 * TreeMap of summary hand values that finally settles winers order for
		 * each summaryHandValue there is HashMap that keeps players with that
		 * summaryHandValue and their HandValue
		 */
		TreeMap<Integer, HashMap<Player, HandValue>> playersRanking = new TreeMap<>();
		for (Player player : activePlayers) {
			int summaryValue;
			HandValue handValue;
			HandValueEvaluator evaluator = new HandValueEvaluator((ArrayList<Card>) board, player.getHand());
			handValue = evaluator.isSpecialValue();
			summaryValue = evaluator.getSummaryValue();
			HashMap<Player, HandValue> playersMap = playersRanking.get(summaryValue);

			if (playersMap == null) {
				playersMap = new HashMap<>();
			}

			playersMap.put(player, handValue);
			playersRanking.put(summaryValue, playersMap);
		}

		Map<Player, Integer> potsDistribution = new HashMap<>();

		for (Integer summaryValue : playersRanking.descendingKeySet()) {
			Set<Player> winners = playersRanking.get(summaryValue).keySet();

			for (Pot pot : pots) {
				// find how many winners share this pot
				int potWinningContributors = 0;
				for (Player winner : winners) {
					if (pot.hasContributed(winner)) {
						potWinningContributors++;
					}
				}
				if (potWinningContributors > 0) {
					// Distributing pots
					int potWinning = (pot.getValue() / potWinningContributors);
					for (Player contributingWinner : winners) {
						if (pot.hasContributed(contributingWinner)) {
							if (potsDistribution.containsKey(contributingWinner)) {
								int oldWinning = potsDistribution.get(contributingWinner);
								potsDistribution.put(contributingWinner, (oldWinning + potWinning));
							} else {
								potsDistribution.put(contributingWinner, potWinning);
							}
						}
					}
					// If anything is left in pot from dividing whole numbers..
					int potLeftovers = (pot.getValue() % potWinningContributors);
					if (potLeftovers > 0) {
						nextActor(dealer);
						while (potLeftovers > 0) {
							if (potsDistribution.containsKey(actor)) {
								int oldWinning = potsDistribution.get(actor);
								potsDistribution.put(actor, (oldWinning + 1));
								potLeftovers--;
								nextActor(actor);
							}
						}
					}
					pot.clearPot();
				}
			}
		}

		// Distributing winnings to winners
		for (Player winner : potsDistribution.keySet()) {
			int potWinning = potsDistribution.get(winner);
			winner.win(potWinning);
		}
	}

	/**
	 * Puts money in pot or creates side pot
	 * 
	 * @param amount
	 *            amount to contribute
	 */
	private void contributePot(int amount) {
		for (Pot pot : pots) {
			if (!pot.hasContributed(actor)) {
				// regular call/bet/raise
				if (amount >= pot.getBet()) {
					pot.addContributor(actor);
					amount -= pot.getBet();
				}
				// partial call (all-in), split pot
				else {
					pots.add(pot.split(actor, amount));
					amount = 0;
				}
			}
			if (amount <= 0) {
				break;
			}
		}
		if (amount > 0) {
			Pot pot = new Pot(amount);
			pot.addContributor(actor);
			pots.add(pot);
		}
	}

	/**
	 * Moves dealer to next active player
	 */
	private void nextDealer() {
		dealerPosition = ((activePlayers.indexOf(dealer) + 1) % activePlayers.size());
		dealer = activePlayers.get(dealerPosition);
	}

	/**
	 * Moves actor to next from active players
	 * 
	 * @param nextToWho
	 *            sets actor to player next to that player
	 */
	private void nextActor(Player nextToWho) {
		actorPosition = ((activePlayers.indexOf(nextToWho) + 1) % activePlayersNum);
		actor = activePlayers.get(actorPosition);
	}

	/**
	 * @return the playersList
	 */
	public List<Player> getPlayersList() {
		return playersList;
	}

	/**
	 * @return the smallBlind
	 */
	public int getSmallBlind() {
		return smallBlind;
	}

	/**
	 * @return the bigBlind
	 */
	public int getBigBlind() {
		return bigBlind;
	}

	/**
	 * @return the deck
	 */
	public Deck getDeck() {
		return deck;
	}

	/**
	 * @return the board
	 */
	public List<Card> getBoard() {
		return board;
	}

	/**
	 * @return the dealer
	 */
	public Player getDealer() {
		return dealer;
	}

	/**
	 * @return the dealerPosition
	 */
	public int getDealerPosition() {
		return dealerPosition;
	}

	/**
	 * @return the activePlayersNum
	 */
	public int getActivePlayersNum() {
		return activePlayersNum;
	}

	/**
	 * @return the activePlayers
	 */
	public List<Player> getActivePlayers() {
		return activePlayers;
	}

	/**
	 * @return the actor
	 */
	public Player getActor() {
		return actor;
	}

	/**
	 * @return the actorPosition
	 */
	public int getActorPosition() {
		return actorPosition;
	}

	/**
	 * @return the pot
	 */
	public List<Pot> getPots() {
		return pots;
	}

	/**
	 * @return the minBet
	 */
	public int getMinBet() {
		return minBet;
	}

	/**
	 * @return the bet
	 */
	public int getBet() {
		return bet;
	}
}
