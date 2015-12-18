package pokertexasholdem;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
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
    
    private long sleeptime = 2000;
    
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
    public Table( HashMap<Socket, String> playersSocketsNames, int money, int smallBlind ) {
        this.smallBlind = smallBlind;
        bigBlind = smallBlind * 2;
        board = new ArrayList<>();
        playersList = new ArrayList<>();
        activePlayers = new ArrayList<>();
        pots = new ArrayList<>();
        
        for (Socket socket : playersSocketsNames.keySet()) {
            String name = playersSocketsNames.get(socket);
            playersList.add(new Player(name, socket, money));
        }
        
        Random random = new Random();
        dealerPosition = random.nextInt(playersSocketsNames.size());
        dealer = playersList.get(dealerPosition);
        System.out.println("[TABLE] Constructed");
    }
    
    /**
     * Preparing players GUIs
     */
    public void prepareClients() {
        String message;
        System.out.println("[TABLE] Preparing players for game");
        
        // Inform clients to create Game Window
        message = "CREATEGUI";
        informAllPlayers(message);
        
        // Inform about start money
        for (Player player : playersList) {
            message = "MONEY Player" + playersList.indexOf(player) + " " + player.getMoney();
            informAllPlayers(message);
        }
        
        // Inform about players aliases
        for (Player player : playersList) {
            message = "NAME Player" + playersList.indexOf(player) + " " + player.getName();
            informAllPlayers(message);
        }
        
        // Send welcoming information
        message = "INFO Welcome! The game is Texas Holdem. Small blinds are set to $" + smallBlind
                + " and big blinds are $" + bigBlind;
        informAllPlayers(message);
        
        message = "GAMEREADY";
        informAllPlayers(message);
    }
    
    /**
     * Send message to all clients
     */
    private void informAllPlayers(String message) {
        for (Player player : playersList) {
            informPlayer(message, player, false);
        }
    }
    
    /**
     * Send message to concrete player
     */
    private String informPlayer(String message, Player player, boolean waitForReply) {
        PrintWriter out = null;
        BufferedReader in = null;
        try {
            out = new PrintWriter(player.getSocket().getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(player.getSocket().getInputStream()));
            System.out.println("[TABLE] Informing " + player.getName() + ": " + message);
            out.println(message);
            
            if (waitForReply) {
                System.out.println("[TABLE] Waiting for response...");
                String response = in.readLine();
                System.out.println("[TABLE] Got answer: " + response);
                return response;
            }
        } catch (IOException e) {
            System.out.println("[TABLE] Inform player I/O Exception");
        } catch (NullPointerException e) {
            ; // TODO player disconected
        }
        return null;
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
                informAllPlayers("INFO Round will start shortly");
                pause(sleeptime*3);
                playRound();
                System.out.println("[RUN] Called method playRound()");
            } else {
                System.out.println("[RUN] Only one active player");
                break;
            }
        }
        
        informAllPlayers("INFO [GAME OVER] Congratulation to the winner!");
        // No more players to play. Game over.
        // TODO: implement whatever happens now
    }
    
    /**
     * Single round ended with showdown
     */
    private void playRound() {
        boolean continueRound;
        
        resetRound();
        System.out.println("[ROUND] Reset done - new round");
        
        // current actor posts small blind, next one posts big blind
        postSmallBlind();
        nextActor(actor);
        
        postBigBlind();
        System.out.println("[ROUND] Blinds posted");
        
        // deal cards to active players
        dealCards();
        System.out.println("[ROUND] Cards dealt");
        
        // do first betting round
        continueRound = betting();
        System.out.println("[ROUND] 1st betting ended");
        
        if(continueRound) {
            // FLOP, deal 3 cards to board
            dealCommunityCards(3);
            System.out.println("[ROUND] Flop dealt");
            
            continueRound = betting();
            System.out.println("[ROUND] 2nd betting ended");
            
            if(continueRound) {
                // TURN, deal one card to board
                dealCommunityCards(1);
                System.out.println("[ROUND] Turn dealt");
                
                // do second betting
                continueRound = betting();
                System.out.println("[ROUND] 3rd betting ended");
                
                if(continueRound) {
                    // RIVER, deal one card to board
                    dealCommunityCards(1);
                    System.out.println("[ROUND] River dealt");
                    
                    // do third, last betting
                    continueRound = betting();
                    System.out.println("[ROUND] 4th betting ended");
                    if(continueRound) {
                        // SHOWDOWN, evaluate players
                        showdown();
                        System.out.println("[ROUND] Showdown ended");
                    }
                }
            }
        }
    }
    
    /**
     * Prepares table for next round
     */
    private void resetRound() {
        // Preparing table for next hand
        board.clear();
        pots.clear();
        
        informAllPlayers("RESETROUND");
        
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
        informAllPlayers("MONEY Player" + playersList.indexOf(actor) + " " + actor.getMoney());
        informAllPlayers("POT " + getTotalPot());
        informAllPlayers("ACTION Player" + playersList.indexOf(actor) + " SmallBlind");
        informAllPlayers("BET Player" + playersList.indexOf(actor) + " $" + smallBlind);
        informAllPlayers("INFO " + actor.getName() + " posts small blind of " + smallBlind);
        pause(sleeptime);
    }
    
    /**
     * Posts Big Blind
     */
    private void postBigBlind() {
        actor.bigBlind(bigBlind);
        contributePot(bigBlind);
        informAllPlayers("MONEY Player" + playersList.indexOf(actor) + " " + actor.getMoney());
        informAllPlayers("POT " + getTotalPot());
        informAllPlayers("ACTION Player" + playersList.indexOf(actor) + " BigBlind");
        informAllPlayers("BET Player" + playersList.indexOf(actor) + " $" + bigBlind);
        informAllPlayers("INFO " + actor.getName() + " posts big blind of " + bigBlind);
        pause(sleeptime);
    }
    
    /**
     * Deals 2 cards to each active player
     */
    private void dealCards() {
        for (Player player : activePlayers) {
            Card[] cards = new Card[2];
            cards[0] = deck.drawFromDeck();
            cards[1] = deck.drawFromDeck();
            
            String message = "YOURCARDS " + cards[0].toString() + " " + cards[1].toString();
            
            informPlayer(message, player, false);
            player.setHandCards(cards);
            pause(sleeptime);
        }
    }
    
    /**
     * Does the betting
     */
    private boolean betting() {
        int playersToBet = activePlayersNum;
        boolean continueRound = true;
        
        // if it's not the first betting, reset bet and set actor to next to
        // dealer
        // if it is a first betting bet and actor were set earlier to proper
        // ones
        if (board.size() != 0) {
            bet = 0;
            nextActor(dealer);
        }
        
        while (playersToBet > 0) {
            nextActor(actor);
            String response;
            Action action;
            int actionBet;
            
            // Actor is all-in, so he checks
            // TODO: debug NullPointerException
            if (actor.isAllIn()) {
                action = Action.CHECK;
                playersToBet--;
            }
            // Actor can choose how to act
            else {
                String legalActions = getLegalActions(actor);
                response = informPlayer(("NOWACT " + minBet + "#" + legalActions), actor, true);
                action = getActionFromResponse(response);
                actionBet = getBetFromResponse(response);
                
                // if player wants to raise for more than he have, its all-in
                if(actionBet > actor.getMoney()) {
                	actionBet = actor.getMoney();
                	action = Action.ALL_IN;
                }
                
                playersToBet--;
                
                if (action == Action.CHECK) {
                    // nothing to do, dude
                } else if (action == Action.ALL_IN) {
                	actionBet = actor.getMoney();
                    actor.allIn();
                    int raiseAmount = actionBet - (bet - actor.getBet());
                    bet += raiseAmount;
                    minBet = raiseAmount;
                    actor.setBet(actor.getBet() + actionBet);
                    actor.pay(actionBet);
                    contributePot(actionBet);
                    // all players get another round
                    playersToBet = activePlayersNum;
                } else if (action == Action.CALL) {
                    int toSettleBet = (bet - actor.getBet());
                    if(toSettleBet == actor.getMoney()) {
                        actor.isAllIn();
                        action = Action.ALL_IN;
                    }
                    actor.pay(toSettleBet);
                    actor.setBet(actor.getBet() + toSettleBet);
                    contributePot(toSettleBet);
                } else if (action == Action.BET) {
                    int betAmount = actionBet;
                    actor.setBet(betAmount);
                    actor.pay(betAmount);
                    contributePot(betAmount);
                    bet = betAmount;
                    minBet = betAmount;
                    // all players get another round
                    playersToBet = activePlayersNum;
                } else if (action == Action.RAISE) {
                    int raiseAmount = actionBet;
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
                    actor.setLastAction(action);
                    activePlayers.remove(actor);
                    activePlayersNum = activePlayers.size();
                    actorPosition--;
                    
                    // only one player left, give him the money
                    if (activePlayersNum == 1) {
                        Player winner = activePlayers.get(0);
                        int winAmount = getTotalPot();
                        winner.win(winAmount);
                        playersToBet = 0;
                        
                        // inform about actors action
                        String message = "ACTION " + "Player" + playersList.indexOf(actor) + " FOLD";
                        informAllPlayers(message);
                        // inform about players bet amount
                        message = "BET " + "Player" + playersList.indexOf(actor) + " $" + actor.getBet();
                        informAllPlayers(message);
                        // update players money
                        message = "MONEY Player" + playersList.indexOf(actor) + " " + actor.getMoney();
                        informAllPlayers(message);
                        // display info about play
                        message = "INFO " + actor.getName() + " " + actor.getLastAction().toString();
                        informAllPlayers(message);
                        pause(sleeptime);
                        
                        message = "INFO " + winner.getName() + " wins $" + winAmount;
                        informAllPlayers(message);
                        
                        message = "MONEY Player" + playersList.indexOf(winner) + " " + winner.getMoney();
                        informAllPlayers(message);
                        
                        informAllPlayers("POT " + getTotalPot());
                        
                        pause(sleeptime * 2);
                        
                        continueRound = true;
                    }
                }
            }
            actor.setLastAction(action);
            if (playersToBet > 0) {
                // inform about actors action
                String message = "ACTION " + "Player" + playersList.indexOf(actor) + " " + actor.getLastAction().getName();
                informAllPlayers(message);
                // inform about players bet amount
                message = "BET " + "Player" + playersList.indexOf(actor) + " $" + actor.getBet();
                informAllPlayers(message);
                // update players money
                message = "MONEY Player" + playersList.indexOf(actor) + " " + actor.getMoney();
                informAllPlayers(message);
                // display info about play
                message = "INFO " + actor.getName() + " " + actor.getLastAction().toString();
                informAllPlayers(message);
                // update pot value
                message = "POT " + getTotalPot();
                informAllPlayers(message);
                
                pause(sleeptime);
            }
        }
        
        // reset bets
        for (Player player : activePlayers) {
            player.setBet(0);
            player.setLastAction(null);
        }
        informAllPlayers("RESETBETS");
        
        return continueRound;
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
    private String getLegalActions(Player actor) {
        String legalActions = "";
        // if player is all-in he can only check and wait for showdown
        if (actor.getLastAction() == Action.ALL_IN) {
            legalActions = legalActions.concat(" btnCheck");
        }
        // else if player is not all-in and can something more
        else {
            // if no bet was placed yet (so no first betting as then it is equal
            // to big blind)
            if (bet == 0) {
                legalActions = legalActions.concat(" btnCheck");
                legalActions = legalActions.concat(" btnBet");
            }
            // if there is some bet posted
            // and actor have more money than difference between his and table
            // bet
            else if ((bet - actor.getBet()) < actor.getMoney()) {
                // and actor's bet is not equal to that on table
                if (actor.getBet() < bet) {
                    legalActions = legalActions.concat(" btnCall");
                    legalActions = legalActions.concat(" btnRaise");
                }
                // or his bet is equal to that on table
                else {
                    legalActions = legalActions.concat(" btnCheck");
                    legalActions = legalActions.concat(" btnRaise");
                }
            }
            // if actor have money equal to difference between his and table bet
            else if ((bet - actor.getBet()) == actor.getMoney()) {
                legalActions = legalActions.concat(" btnCall");
            }
            // and every active not all-in player can fold and go all-in
            legalActions = legalActions.concat(" btnAllIn");
            legalActions = legalActions.concat(" btnFold");
        }
        return legalActions;
    }
    
    /**
     * Gets action from message received from client
     * 
     * @param response
     *            string output from client
     * @return action enum
     */
    private Action getActionFromResponse(String response) {
        Action action = null;
        
        if (response.startsWith("BET"))
            action = Action.BET;
        if (response.startsWith("CHECK"))
            action = Action.CHECK;
        if (response.startsWith("CALL"))
            action = Action.CALL;
        if (response.startsWith("RAISE"))
            action = Action.RAISE;
        if (response.startsWith("ALL_IN"))
            action = Action.ALL_IN;
        if (response.startsWith("FOLD"))
            action = Action.FOLD;
            
        return action;
    }
    
    /**
     * Gets bet amount from message received from client
     * 
     * @param response
     *            string output from client
     * @return bet amount
     */
    private int getBetFromResponse(String response) {
        int index = response.indexOf(" ") + 1;
        int bet = 0;
        try {
            bet = Integer.parseInt(response.substring(index));
        } catch (NumberFormatException e) {
        }
        
        return bet;
    }
    
    /**
     * Deals community cards amount to board
     * 
     * @param amount
     *            number of cards to deal
     */
    private void dealCommunityCards(int amount) {
        for (int j = 0; j < amount; j++) {
            Card card = deck.drawFromDeck();
            board.add(card);
            int index = board.size();
            informAllPlayers("CARDCOMMUNITY Card" + index + " " + card.toString());
            pause(sleeptime);
        }
    }
    
    /**
     * Deals money to winners
     */
    private void showdown() {
        informAllPlayers("INFO Showdown!");
        pause(sleeptime);
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
            Card[] cards = player.getHandCards();
            informAllPlayers("CARDPLAYER Player" + playersList.indexOf(player) + " " + cards[0].toString() + " "
                    + cards[1].toString());
            informAllPlayers("VALUEPLAYER Player" + playersList.indexOf(player) + " " + handValue.name());
            informAllPlayers("INFO " + player.getName() + " has " + handValue.toString());
            pause(sleeptime);
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
            informAllPlayers("INFO " + winner.getName() + " wins $" + potWinning);
            informAllPlayers("MONEY Player" + playersList.indexOf(winner) + " " + winner.getMoney());
            informAllPlayers("POT " + getTotalPot());
            pause(sleeptime * 2);
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
        System.out.println("[DEALER] " + dealer.getName());
        String message = "DEALER Player" + playersList.indexOf(dealer);
        informAllPlayers(message);
    }
    
    /**
     * Moves actor to next from active players
     * 
     * @param nextToWho
     *            sets actor to player next to that player
     */
    private void nextActor(Player nextToWho) {
        actorPosition = ((activePlayers.indexOf(nextToWho) + 1) % activePlayers.size());
        actor = activePlayers.get(actorPosition);
        System.out.println("[ACTOR] " + actor.getName());
        String message = "ACTOR Player" + playersList.indexOf(actor);
        informAllPlayers(message);
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
    
    public void pause(long sleeptime) {
        Object obj = new Object();
        if (sleeptime > 0) {
            synchronized (obj) {
                try {
                    obj.wait(sleeptime);
                } catch (InterruptedException ex) {
                    // ToCatchOrNot
                }
            }
        }
    }
}
