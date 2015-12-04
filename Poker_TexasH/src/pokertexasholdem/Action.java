package pokertexasholdem;

public enum Action {
	CHECK("Check", "checks"), 
	BET("Bet", "bets"), 
	RAISE("Raise", "raises"), 
	CALL("Call", "calls"), 
	FOLD("Fold", "folds"), 
	ALL_IN("All-in", "goes all-in"), 
	SMALL_BLIND("Small Blind", "posts small blind"), 
	BIG_BLIND("Big Blind", "posts big blind");

	private String name;
	private String verb;

	Action(String name, String verb) {
		this.name = name;
		this.verb = verb;
	}

	public String getName() {
		return name;
	}

	@Override
	public String toString() {
		return verb;
	}
}
