package pokertexasholdem;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

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
	private List<Pot> pots;
	
	/** Minimum bet in current hand */
	private int minBet;
	
	/** Current bet in current hand */
	private int bet;
	
	/** Player that bet or raised last */
	private Player lastBetPlayer;
	
	public Table( int playersAmount, int money, int smallBlind ) {
		this.smallBlind = smallBlind;
		bigBlind = smallBlind * 2;
		board = new ArrayList<>();
		playersList = new ArrayList<>();
		activePlayers = new ArrayList<>();
		pots = new ArrayList<>();
		
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
                pots.clear();
    		
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
            contributePot(smallBlind);
        }
        
        private void postBigBlind() {
            actor.bigBlind(bigBlind);
            contributePot(bigBlind);
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
            int playersToBet = activePlayersNum;
            
            // if it's not the first betting, reset bet and set actor to next to dealer
            // if it is a first betting bet and actor were set earlier to proper ones
            if( board.size() != 0 ) {
                bet = 0;
                nextActor(dealer);
            }
            
            lastBetPlayer = null;
            
            while( playersToBet > 0 ) {
                Action action;
                
                // Actor is all-in, so he checks
                if( actor.getLastAction().equals(Action.ALL_IN) ) {
                    action = Action.CHECK;
                    playersToBet--;
                }
                // Actor can choose how to act
                else {
                    Set<Action> legalActions = getLegalActions(actor);
                    action = null;//TODO: get action from Client
                    playersToBet--;
                    
                    if( action == Action.CHECK ) {
                        // nothing to do, dude
                    }
                    else if( action == Action.CALL ) {
                        int toSettleBet = ( bet - actor.getBet() );
                        actor.pay( toSettleBet );
                        actor.setBet( actor.getBet() + toSettleBet );
                        contributePot( toSettleBet );
                    }
                    else if( action == Action.BET ) {
                        int betAmount = 0;//TODO: equals to bet send by Client;
                        actor.setBet(betAmount);
                        actor.pay(betAmount);
                        contributePot(betAmount);
                        bet = betAmount;
                        minBet = betAmount;
                        lastBetPlayer = actor;
                        // all players get another round
                        playersToBet = activePlayersNum;
                    }
                    else if( action == Action.RAISE ) {
                        int raiseAmount = 0;//TODO: equals to raise send by Client
                        bet += raiseAmount;
                        minBet = raiseAmount;
                        int toRaiseBet = ( bet - actor.getBet() );
                        actor.setBet(bet);
                        actor.pay(toRaiseBet);
                        contributePot(toRaiseBet);
                        lastBetPlayer = actor;
                        // all players get another round
                        playersToBet = activePlayersNum;
                    }
                    else if( action == Action.FOLD ) {
                        actor.setHand(null);
                        activePlayers.remove(actor);
                        activePlayersNum = activePlayers.size();
                        actorPosition--;
                        
                        // only one player left, give him the money
                        if( activePlayersNum == 1 ) {
                            Player winner = activePlayers.get(0);
                            int winAmount = getTotalPot();
                            winner.win(winAmount);
                            playersToBet = 0;
                        }
                    }
                }
                actor.setLastAction(action);
                if( playersToBet > 0 ) {
                    //TODO: refresh board/client NOTIFY
                }
            }
            
            // reset bets
            for( Player player : activePlayers ) {
                player.setBet(0);
                player.setLastAction(null);
            }
        
        }   
         
        private int getTotalPot() {
            int total = 0;
            for( Pot pot : pots ) {
                total += pot.getValue();
            }
            return total;
        }

        private Set<Action> getLegalActions(Player actor) {
            Set<Action> legalActions = new HashSet<>();
            // if player is all-in he can only check and wait for showdown
            if( actor.getLastAction() == Action.ALL_IN ) {
                legalActions.add(Action.CHECK);
            }
            // else if player is not all-in and can something more
            else {
                // if no bet was placed yet (so no first betting as then it is equal to big blind)
                if( bet == 0 ) {
                    legalActions.add(Action.CHECK);
                    legalActions.add(Action.BET);
                }
                // if there is some bet posted
                // and actor have more money than difference between his and table bet
                else if( ( bet - actor.getBet() ) < actor.getMoney() ) {
                    // and actor's bet is not equal to that on table
                    if( actor.getBet() < bet ) {
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
                else if ( ( bet - actor.getBet() ) == actor.getMoney() ){
                    legalActions.add(Action.CALL);
                }
                // and every active not all-in player can fold and go all-in
                legalActions.add(Action.ALL_IN);
                legalActions.add(Action.FOLD);
            }
            return legalActions;
        }

        private void dealCommunityCards(int amount) {
	    for( int j = 0; j < amount; j++ ) {
	        board.add( deck.drawFromDeck() );
	    }
	    
	}
        
        private void showdown() {
        // TODO Auto-generated method stub
        
        }
        
        private void contributePot( int amount ) {
            for( Pot pot : pots ) {
                if( !pot.hasContributed(actor) ) {
                    // regular call/bet/raise
                    if( amount >= pot.getBet() ) {
                        pot.addContributor(actor);
                        amount -= pot.getBet();
                    }
                    // partial call (all-in), split pot
                    else {
                        pots.add( pot.split(actor, amount) );
                        amount = 0;
                    }
                }
                if( amount <= 0 ) {
                    break;
                }
            }
            if( amount > 0 ) {
                Pot pot = new Pot(amount);
                pot.addContributor(actor);
                pots.add(pot);
            }
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
