package pokertexasholdem;

import java.util.Comparator;

public class CardComparator implements Comparator<Card> {

    @Override
    public int compare(Card card1, Card card2) {
        return (card1.getRate() < card2.getRate() || ( card1.getRate() == card2.getRate() && card1.getSuitRate() < card2.getSuitRate() )) ? -1 : (card1.getRate() > card2.getRate() || ( card1.getRate() == card2.getRate() && card1.getSuitRate() > card2.getSuitRate() )) ? 1 : 0;
    }
}
