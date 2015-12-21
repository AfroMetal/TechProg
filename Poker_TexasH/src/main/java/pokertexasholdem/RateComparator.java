package pokertexasholdem;

import java.util.Comparator;

public class RateComparator implements Comparator<Card> {

	@Override
	public int compare(Card card1, Card card2) {
		return (card1.getRate() < card2.getRate()) ? 1 : (card1.getRate() == card2.getRate() && card1.getSuit() == card2.getSuit()) ? 0 : -1;
	}
}
