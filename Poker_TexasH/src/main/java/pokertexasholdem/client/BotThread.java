package pokertexasholdem.client;

import java.net.Socket;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.TreeSet;

import pokertexasholdem.Card;
import pokertexasholdem.RateComparator;
import pokertexasholdem.Card.Ranks;
import pokertexasholdem.Card.Suits;

public class BotThread extends ClientThread {
    
    private TreeSet<Card> cards;
    private final RateComparator rateComparator = new RateComparator();
    private final int tightness;
    private final int aggression;
    
    public BotThread( Client client, Socket socket ) {
        super(client, socket);
        final Random random = new Random();
        tightness = random.nextInt(51);
        System.out.println("[THREAD] Bot's tightness = " + tightness);
        aggression = random.nextInt(51) + 50;
        System.out.println("[THREAD] Bot's agression = " + aggression);
    }
    
    protected void onMessage() {
        if (message.startsWith("[CONNECTED]")) {
            connectionWindow.setBtnConnectEnabled(false);
            connectionWindow.setTitle("Waiting for opponents...");
            connectionWindow.setBtnConnectText("Connected!");
        }
        
        if (message.startsWith("[GAMESTARTED]")) {
            connectionWindow.setBtnConnectEnabled(false);
            connectionWindow.setTitle("Game started...");
            connectionWindow.setBtnConnectText(":(");
        }
        
        if (message.startsWith("CREATEGUI")) {
            // not implemented
        }
        
        if (message.startsWith("GAMEREADY")) {
            connectionWindow.setVisible(false);
        }
        
        if (message.startsWith("NOWACT")) {
            String[] parts = message.split("#");
            Integer minBet = Integer.parseInt(parts[0].substring(7));
            Integer bet = Integer.parseInt(parts[1]);
            String[] legalActions = parts[2].substring(1).split(" ");
            String answer = act(minBet, bet, legalActions);
            client.sendToServer(answer);
        }
        
        if (message.startsWith("MONEY")) {
            // not implemented
        }
        
        if (message.startsWith("NAME")) {
            // not implemented
        }
        
        if (message.startsWith("ACTION")) {
            // not implemented
        }
        
        if (message.startsWith("BET")) {
            // not implemented
        }
        
        if (message.startsWith("INFO")) {
            // not implemented
        }
        
        if (message.startsWith("DEALER")) {
            // not implemented
        }
        
        if (message.startsWith("ACTOR")) {
            // not implemented
        }
        
        if (message.startsWith("POT")) {
            // not implemented
        }
        
        if (message.startsWith("RESETBETS")) {
            // not implemented
        }
        
        if (message.startsWith("RESETROUND")) {
            cards = new TreeSet<Card>(rateComparator);
        }
        
        if (message.startsWith("CARDPLAYER")) {
            // not implemented
        }
        
        if (message.startsWith("VALUEPLAYER")) {
            // not implemented
        }
        
        if (message.startsWith("CARDCOMMUNITY")) {
         // parts[0] = "Card#" parts[1] = <rank><suit>
            String[] parts = message.substring(14).split(" ");
            
            Ranks rank = null;
            // ♠♣♥♦
            Suits suit = null;
     
            System.out.println("[THREAD] Got card: " + parts[1]);
            
            if (parts[1].startsWith("2")) { rank = Ranks.RANK2; }
            if (parts[1].startsWith("3")) { rank = Ranks.RANK3; }
            if (parts[1].startsWith("4")) { rank = Ranks.RANK4; }
            if (parts[1].startsWith("5")) { rank = Ranks.RANK5; }
            if (parts[1].startsWith("6")) { rank = Ranks.RANK6; }
            if (parts[1].startsWith("7")) { rank = Ranks.RANK7; }
            if (parts[1].startsWith("8")) { rank = Ranks.RANK8; }
            if (parts[1].startsWith("9")) { rank = Ranks.RANK9; }
            if (parts[1].startsWith("10")) { rank = Ranks.RANK10; }
            if (parts[1].startsWith("J")) { rank = Ranks.RANKJ; }
            if (parts[1].startsWith("Q")) { rank = Ranks.RANKQ; }
            if (parts[1].startsWith("K")) { rank = Ranks.RANKK; }
            if (parts[1].startsWith("A")) { rank = Ranks.RANKA; }
            
            if (parts[1].endsWith("♠")) { suit = Suits.SPADES; }
            if (parts[1].endsWith("♣")) { suit = Suits.CLUBS; }
            if (parts[1].endsWith("♥")) { suit = Suits.HEARTS; }
            if (parts[1].endsWith("♦")) { suit = Suits.DIAMONDS; }
            
            cards.add(new Card(rank, suit));
        }
        
        if (message.startsWith("YOURCARDS")) {
            // parts[0] = <rankCard1><suitCard1> parts[1] =
            // <rankCard2><suitCard2>
            String[] parts = message.substring(10).split(" ");
            parts[0] = "5♣";
            parts[1] = "5♥";
            Ranks rank = null;
            // ♠♣♥♦
            Suits suit = null;
            
            System.out.println("[THREAD] Got card: " + parts[0]);
            System.out.println("[THREAD] Got card: " + parts[1]);
            
            if (parts[0].startsWith("2")) { rank = Ranks.RANK2; }
            if (parts[0].startsWith("3")) { rank = Ranks.RANK3; }
            if (parts[0].startsWith("4")) { rank = Ranks.RANK4; }
            if (parts[0].startsWith("5")) { rank = Ranks.RANK5; }
            if (parts[0].startsWith("6")) { rank = Ranks.RANK6; }
            if (parts[0].startsWith("7")) { rank = Ranks.RANK7; }
            if (parts[0].startsWith("8")) { rank = Ranks.RANK8; }
            if (parts[0].startsWith("9")) { rank = Ranks.RANK9; }
            if (parts[0].startsWith("10")) { rank = Ranks.RANK10; }
            if (parts[0].startsWith("J")) { rank = Ranks.RANKJ; }
            if (parts[0].startsWith("Q")) { rank = Ranks.RANKQ; }
            if (parts[0].startsWith("K")) { rank = Ranks.RANKK; }
            if (parts[0].startsWith("A")) { rank = Ranks.RANKA; }
            
            if (parts[0].endsWith("♠")) { suit = Suits.SPADES; }
            if (parts[0].endsWith("♣")) { suit = Suits.CLUBS; }
            if (parts[0].endsWith("♥")) { suit = Suits.HEARTS; }
            if (parts[0].endsWith("♦")) { suit = Suits.DIAMONDS; }
            
            cards.add(new Card(rank, suit));
            
            if (parts[1].startsWith("2")) { rank = Ranks.RANK2; }
            if (parts[1].startsWith("3")) { rank = Ranks.RANK3; }
            if (parts[1].startsWith("4")) { rank = Ranks.RANK4; }
            if (parts[1].startsWith("5")) { rank = Ranks.RANK5; }
            if (parts[1].startsWith("6")) { rank = Ranks.RANK6; }
            if (parts[1].startsWith("7")) { rank = Ranks.RANK7; }
            if (parts[1].startsWith("8")) { rank = Ranks.RANK8; }
            if (parts[1].startsWith("9")) { rank = Ranks.RANK9; }
            if (parts[1].startsWith("10")) { rank = Ranks.RANK10; }
            if (parts[1].startsWith("J")) { rank = Ranks.RANKJ; }
            if (parts[1].startsWith("Q")) { rank = Ranks.RANKQ; }
            if (parts[1].startsWith("K")) { rank = Ranks.RANKK; }
            if (parts[1].startsWith("A")) { rank = Ranks.RANKA; }
            
            if (parts[1].endsWith("♠")) { suit = Suits.SPADES; }
            if (parts[1].endsWith("♣")) { suit = Suits.CLUBS; }
            if (parts[1].endsWith("♥")) { suit = Suits.HEARTS; }
            if (parts[1].endsWith("♦")) { suit = Suits.DIAMONDS; }
            
            cards.add(new Card(rank, suit));
        }
    }
    
    private String act(Integer minBet, Integer currentBet, String[] legalActions) {
        String message = "";
        List<String> legalActionsList = Arrays.asList(legalActions);
        String action = "FOLD ";
        String amount = "0";
        
        if (legalActionsList.size() == 1) {
            // No choice, must check.
            action = "CHECK ";
        } else {
            double chenScore = getChenScore(cards);
            System.out.println("[ACT] Got chen score = " + chenScore);
            double chenScoreToPlay = tightness * 0.15;
            System.out.println("[ACT] Chen score to play = " + chenScoreToPlay);
            if ((chenScore < chenScoreToPlay)) {
                if (legalActionsList.contains("btnCheck")) {
                    // Always check for free if possible.
                    action = "CHECK ";
                } else {
                    // Bad hole cards; play tight.
                    action = "FOLD ";
                }
            } else {
                // Good enough hole cards, play hand.
                if ((chenScore - (chenScoreToPlay + 2)) >= 0) {
                    System.out.println("[ACT] Good cards, play hand");
                    // Very good hole cards; bet or raise!
                    if (aggression == 0) {
                        // Never bet.
                        if (legalActionsList.contains("btnCall")) {
                            action = "CALL ";
                        } else if (legalActionsList.contains("btnCheck")) {
                            action = "CHECK ";
                        }
                    } else if (aggression == 100) {
                        // Always go all-in!
                        int betAmount = minBet * 100;
                        if (legalActionsList.contains("btnAllIn")) {
                            action = "ALL_IN ";
                            amount = Integer.toString(betAmount);
                        } else if (legalActionsList.contains("btnBet")) {
                            action = "BET ";
                            amount = Integer.toString(betAmount);
                        } else if (legalActionsList.contains("btnRaise")) {
                            action = "RAISE ";
                            amount = Integer.toString(betAmount);
                        } else if (legalActionsList.contains("btnCall")) {
                            action = "CALL ";
                        } else if (legalActionsList.contains("btnCheck")) {
                            action = "CHECK ";
                        }
                    } else {
                        int betAmount = minBet;
                        int betLevel = aggression / 20;
                        for (int i = 0; i < betLevel; i++) {
                            betAmount += betAmount;
                        }
                        
                        if (currentBet.compareTo(betAmount) < 0) {
                            if (legalActionsList.contains("btnBet")) {
                                action = "BET ";
                                amount = Integer.toString(betAmount);
                            } else if (legalActionsList.contains("btnRaise")) {
                                action = "RAISE ";
                                amount = Integer.toString(betAmount);
                            } else if (legalActionsList.contains("btnCall")) {
                                action = "CALL ";
                            } else if (legalActionsList.contains("btnCheck")) {
                                action = "CHECK ";
                            }
                        } else {
                            if (legalActionsList.contains("btnCall")) {
                                action = "CALL ";
                            } else if (legalActionsList.contains("btnCheck")) {
                                action = "CHECK ";
                            }
                        }
                    }
                } else {
                    // Decent hole cards; check or call.
                    System.out.println("[ACT] Decent cards, check or call.");
                    if (legalActionsList.contains("btnCheck")) {
                        action = "CHECK ";
                    } else if (legalActionsList.contains("btnCall")) {
                        action = "CALL ";
                    }
                }
            }
        }
        message = action + amount;
        System.out.println("[ACT] Message: " + message);
        return message;
    }
    
    public double getChenScore(TreeSet<Card> cards) {
        if (cards.size() < 2 || cards.size() > 7) {
            throw new IllegalArgumentException("Invalid number of cards: " + cards.size());
        }
        // Analyze cards.
        int rank1 = cards.last().getRate();
        Card.Suits suit1 = cards.last().getSuit();
        int rank2 = cards.lower(cards.last()).getRate();
        Card.Suits suit2 = cards.lower(cards.last()).getSuit();
        int highRank = Math.max(rank1, rank2);
        int lowRank = Math.min(rank1, rank2);
        int rankDiff = highRank - lowRank;
        int gap = (rankDiff > 1) ? rankDiff - 1 : 0;
        boolean isPair = (rank1 == rank2);
        boolean isSuited = (suit1.equals(suit2));
        
        double score = 0.0;
        
        // 1. Base score highest rank only
        if (highRank == Ranks.RANKA.getRate()) {
            score = 10.0;
        } else if (highRank == Ranks.RANKK.getRate()) {
            score = 8.0;
        } else if (highRank == Ranks.RANKQ.getRate()) {
            score = 7.0;
        } else if (highRank == Ranks.RANKJ.getRate()) {
            score = 6.0;
        } else {
            score = (highRank + 2) / 2.0;
        }
        
        // 2. If pair, double score, with minimum score of 5.
        if (isPair) {
            score *= 2.0;
            if (score < 5.0) {
                score = 5.0;
            }
        }
        
        // 3. If suited, add 2 points.
        if (isSuited) {
            score += 2.0;
        }
        
        // 4. Subtract points for gap.
        if (gap == 1) {
            score -= 1.0;
        } else if (gap == 2) {
            score -= 2.0;
        } else if (gap == 3) {
            score -= 4.0;
        } else if (gap > 3) {
            score -= 5.0;
        }
        
        // 5. Add 1 point for a 0 or 1 gap and both cards lower than a Queen.
        if (!isPair && gap < 2 && rank1 < Ranks.RANKQ.getRate() && rank2 < Ranks.RANKQ.getRate()) {
            score += 1.0;
        }
        
        // Minimum score is 0.
        if (score < 0.0) {
            score = 0.0;
        }
        
        // 6. Round half point scores up.
        System.out.println("[CHEN SCORE] " + score);
        return Math.round(score);
    }
    
}
