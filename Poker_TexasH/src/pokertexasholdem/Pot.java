package pokertexasholdem;

import java.util.HashSet;
import java.util.Set;

public class Pot {
	private int bet = 0;
	private Set<Player> contributorsSet;
	
	public Pot( int bet ) {
	    this.bet = bet;
	    contributorsSet = new HashSet<>();
	}
	
	public void addToPot( int value ) {
		bet += value;
	}
	
	public void addContributor( Player player ) {
            contributorsSet.add(player);
        }
	
	public boolean hasContributed( Player player ) {
	    return contributorsSet.contains(player);
	}
	
	/**
	 * @param player
	 *     Player with partial call, bet or raise
	 * @param partialBet
	 *     amount of partial bet
	 * @return
	 *     new pot with the remainder
	 */
	public Pot split( Player player, int partialBet ) {
	    Pot pot = new Pot( bet - partialBet );
	    
	    for( Player contributor : contributorsSet ) {
	        pot.addContributor(contributor);
	    }
	    
	    bet = partialBet;
	    contributorsSet.add(player);
	    
	    return pot;
	}
	
	public void clearPot() {
		bet = 0;
	}
	
	public int getBet() {
		return bet;
	}
	
	/**
	 * Multiplies pot's bet by number of players that contributed to it
	 * 
	 * @return
	 *     total pot value
	 */
	public int getValue() {
	    return ( bet * contributorsSet.size() );
	}

	public void setPot(int bet) {
		this.bet = bet;
	}
	
	public Set<Player> getContributorsSet() {
                return contributorsSet;
        }
	
	@Override
	public String toString() {
		return ( "Pot: " + (bet * contributorsSet.size()) );
	}
	
}
