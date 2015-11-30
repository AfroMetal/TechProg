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
	
	/** Player to do next move */
	private Player actor;
	
	/** Player to do next move position */
        private int actorPosition;
	
	/** List of pots (main and side pots if exist) */
        //TODO: resolve side pots somehow
	private Pot pot;
	
	/** Minimum bet in current hand */
	private int minBet;
	
	/** Current bet in current hand */
	private int bet;
	
	public Table( int playersAmount, int money, int smallBlind ) {
		this.smallBlind = smallBlind;
		bigBlind = smallBlind * 2;
		board = new ArrayList<>();
		playersList = new ArrayList<>();
		activePlayers = new ArrayList<>();
		pot = new Pot();
		
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
			activePlayers.clear();
			for( Player player : playersList ) {
			        player.resetPlayer();
				if( player.getMoney() >= bigBlind ) {
					activePlayersNum++;
					activePlayers.add(player);
				}
			}
			
			if( activePlayersNum > 1 ) {
				playRound();
			}
			else {
				break;
			}
		}
		
		//No more players to play. Game over.
		//TODO: implement whatever happens now
	}
	
	private void playRound() {
		resetRound();
		
		//current actor posts small blind, next one posts big blind
		postSmallBlind();
		nextActor(actor);
		
		postBigBlind();
		nextActor(actor);
		
		//deal cards to active players
		dealCards();
		
		//do first betting round
		betting();
		
		//FLOP, deal 3 cards to board
		bet = 0;
		dealCommunityCards( 3 );
		
		betting();
		
		//TURN, deal one card to board
                dealCommunityCards( 1 );
                
                //do second betting
                betting();		
                
                //RIVER, deal one card to board
                dealCommunityCards( 1 );
                
                //do third, last betting
                betting();
                
                //SHOWDOWN, evaluate players
                showdown();
        
	}

        private void resetRound() {
    		//Preparing table for next hand
                board.clear();
                pot.clearPot();
    		
    		//Rotate dealer
    		nextDealer();
    		//Set actor to player next to dealer
    		nextActor(dealer);
    		//Shuffle deck
    		deck = new Deck();
    		deck.shuffleDeck(3);
    		//Set bets
    		minBet = bigBlind;
    		bet = minBet;
    	}
        
        private void postSmallBlind() {
            actor.smallBlind(smallBlind);
            pot.addToPot(smallBlind);
        }
        
        private void postBigBlind() {
            actor.bigBlind(bigBlind);
            pot.addToPot(bigBlind);           
        }
        
        private void dealCards() {
            for( Player player : activePlayers )  {
                Card[] cards = new Card[2];
                cards[0] = deck.drawFromDeck();
                cards[1] = deck.drawFromDeck();
                player.setHandCards(cards);
            }
        }   
        
         private void betting() {
            
        
        }   
         
        private void dealCommunityCards(int amount) {
            
	    for( int j = 0; j < amount; j++ ) {
	        board.add( deck.drawFromDeck() );
	    }
	    
	}
        
        private void showdown() {
        // TODO Auto-generated method stub
        
        }
        
        private void nextDealer() {
            
            dealerPosition = ( ( activePlayers.indexOf(dealer) + 1 ) % activePlayers.size() );
            dealer = activePlayers.get( dealerPosition );
            
        }
        
        private void nextActor( Player nextToWho ) {
            
            actorPosition = ( ( activePlayers.indexOf(nextToWho)  + 1 ) % activePlayersNum );
            actor = activePlayers.get( actorPosition );
        
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
        public Pot getPot() {
            return pot;
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
