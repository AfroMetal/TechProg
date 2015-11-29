package pokertexasholdem;

public class Pot {
	private int pot = 0;
	
	public void addToPot( int value ) {
		pot += value;
	}
	
	public void clearPot() {
		pot = 0;
	}
	
	public int getPot() {
		return pot;
	}

	public void setPot(int pot) {
		this.pot = pot;
	}
	
	@Override
	public String toString() {
		return ("Current pot = " + pot);
	}
}
