package pokertexasholdem;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import pokertexasholdem.Card.Ranks;
import pokertexasholdem.Card.Suits;

public class HandValueEvaluatorTest {
    
    private static HandValueEvaluator handEvaluator;

    @Before
    public void setUp() throws Exception {
        handEvaluator = new HandValueEvaluator();
    }
    
    @Test
    public void testIsSpecialValue() {   
        Card card0 = new Card( Ranks.RANK3, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK8, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANKA, Suits.DIAMONDS);
        Card card3 = new Card( Ranks.RANKK, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK2, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANK5, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANKQ, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        assertEquals(HandValues.HIGH_CARD, handEvaluator.isSpecialValue());
    }
    
    @Test
    public void testSortByRank() {
        Card card0 = new Card( Ranks.RANK3, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK2, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANKA, Suits.DIAMONDS);
        Card card3 = new Card( Ranks.RANK8, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK9, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANKQ, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        assertEquals(Ranks.RANK3, handEvaluator.getCardsToEvaluate()[0].getRank());
        assertEquals(Ranks.RANK2, handEvaluator.getCardsToEvaluate()[1].getRank());
        assertEquals(Ranks.RANKA, handEvaluator.getCardsToEvaluate()[2].getRank());
        assertEquals(Ranks.RANK8, handEvaluator.getCardsToEvaluate()[3].getRank());
        assertEquals(Ranks.RANK9, handEvaluator.getCardsToEvaluate()[4].getRank());
        assertEquals(Ranks.RANKK, handEvaluator.getCardsToEvaluate()[5].getRank());
        assertEquals(Ranks.RANKQ, handEvaluator.getCardsToEvaluate()[6].getRank());
        
        handEvaluator.sortByRank();
        
        assertEquals(Ranks.RANKA, handEvaluator.getCardsToEvaluate()[0].getRank());
        assertEquals(Ranks.RANKK, handEvaluator.getCardsToEvaluate()[1].getRank());
        assertEquals(Ranks.RANKQ, handEvaluator.getCardsToEvaluate()[2].getRank());
        assertEquals(Ranks.RANK9, handEvaluator.getCardsToEvaluate()[3].getRank());
        assertEquals(Ranks.RANK8, handEvaluator.getCardsToEvaluate()[4].getRank());
        assertEquals(Ranks.RANK3, handEvaluator.getCardsToEvaluate()[5].getRank());
        assertEquals(Ranks.RANK2, handEvaluator.getCardsToEvaluate()[6].getRank());
    }
    
    @Test
    public void testIsOnePair() {
        Card card0 = new Card( Ranks.RANK3, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK2, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANK3, Suits.SPADES);
        Card card3 = new Card( Ranks.RANK8, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK9, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANKQ, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isOnePair());
        assertEquals(HandValues.ONE_PAIR, handEvaluator.getSpecialValue());
        assertEquals(HandValues.ONE_PAIR.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANKQ.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANK9.getRate(), handEvaluator.ratings[5]);
    }
    
    @Test
    public void testIsTwoPair() {
        Card card0 = new Card( Ranks.RANK3, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK2, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANK3, Suits.SPADES);
        Card card3 = new Card( Ranks.RANKK, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK9, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANKQ, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isTwoPair());
        assertEquals(HandValues.TWO_PAIR, handEvaluator.getSpecialValue());
        assertEquals(HandValues.TWO_PAIR.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANKQ.getRate(), handEvaluator.ratings[5]);
    }
    
    @Test
    public void testIsThreeOfAKind() {
        Card card0 = new Card( Ranks.RANK3, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK2, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANK3, Suits.SPADES);
        Card card3 = new Card( Ranks.RANKA, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK3, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANKQ, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isThreeOfAKind());
        assertEquals(HandValues.THREE_OF_A_KIND, handEvaluator.getSpecialValue());
        assertEquals(HandValues.THREE_OF_A_KIND.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANKA.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[5]);
    }
    
    @Test
    public void testIsFourOfAKind() {
        Card card0 = new Card( Ranks.RANK3, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK2, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANK3, Suits.CLUBS);
        Card card3 = new Card( Ranks.RANKA, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK3, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANK3, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isFourOfAKind());
        assertEquals(HandValues.FOUR_OF_A_KIND, handEvaluator.getSpecialValue());
        assertEquals(HandValues.FOUR_OF_A_KIND.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANKA.getRate(), handEvaluator.ratings[5]);
    }
    
    @Test
    public void testIsFullHouse() {
        Card card0 = new Card( Ranks.RANK3, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK2, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANK3, Suits.CLUBS);
        Card card3 = new Card( Ranks.RANKK, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK3, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANK3, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isFullHouse());
        assertEquals(HandValues.FULL_HOUSE, handEvaluator.getSpecialValue());
        assertEquals(HandValues.FULL_HOUSE.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[5]);
    }
    
    @Test
    public void testIsStraight() {
        Card card0 = new Card( Ranks.RANK9, Suits.DIAMONDS);
        Card card1 = new Card( Ranks.RANK10, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANK3, Suits.CLUBS);
        Card card3 = new Card( Ranks.RANKQ, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK3, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.DIAMONDS);
        Card card6 = new Card( Ranks.RANKJ, Suits.SPADES);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isStraight());
        assertEquals(HandValues.STRAIGHT, handEvaluator.getSpecialValue());
        assertEquals(HandValues.STRAIGHT.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANKQ.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANKJ.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANK10.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANK9.getRate(), handEvaluator.ratings[5]);
    }
    
    @Test
    public void testIsFlush() {
        Card card0 = new Card( Ranks.RANK9, Suits.HEARTS);
        Card card1 = new Card( Ranks.RANK10, Suits.CLUBS);
        Card card2 = new Card( Ranks.RANK3, Suits.CLUBS);
        Card card3 = new Card( Ranks.RANKQ, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK3, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.HEARTS);
        Card card6 = new Card( Ranks.RANKJ, Suits.HEARTS);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isFlush());
        assertEquals(HandValues.FLUSH, handEvaluator.getSpecialValue());
        assertEquals(HandValues.FLUSH.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANKQ.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANKJ.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANK9.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANK3.getRate(), handEvaluator.ratings[5]);
    }
    
    @Test
    public void testIsStraightFlush() {
        Card card0 = new Card( Ranks.RANK9, Suits.HEARTS);
        Card card1 = new Card( Ranks.RANK10, Suits.HEARTS);
        Card card2 = new Card( Ranks.RANK3, Suits.HEARTS);
        Card card3 = new Card( Ranks.RANKQ, Suits.HEARTS);
        Card card4 = new Card( Ranks.RANK3, Suits.HEARTS);
        Card card5 = new Card( Ranks.RANKK, Suits.HEARTS);
        Card card6 = new Card( Ranks.RANKJ, Suits.HEARTS);
        
        handEvaluator.addCardToEvaluate(card0, 0);
        handEvaluator.addCardToEvaluate(card1, 1);
        handEvaluator.addCardToEvaluate(card2, 2);
        handEvaluator.addCardToEvaluate(card3, 3);
        handEvaluator.addCardToEvaluate(card4, 4);
        handEvaluator.addCardToEvaluate(card5, 5);
        handEvaluator.addCardToEvaluate(card6, 6);
        
        handEvaluator.sortByRank();
        
        assertEquals(true, handEvaluator.isStraightFlush());
        assertEquals(HandValues.STRAIGHT_FLUSH, handEvaluator.getSpecialValue());
        assertEquals(HandValues.STRAIGHT_FLUSH.getRate(), handEvaluator.ratings[0]);
        assertEquals(Ranks.RANKK.getRate(), handEvaluator.ratings[1]);
        assertEquals(Ranks.RANKQ.getRate(), handEvaluator.ratings[2]);
        assertEquals(Ranks.RANKJ.getRate(), handEvaluator.ratings[3]);
        assertEquals(Ranks.RANK10.getRate(), handEvaluator.ratings[4]);
        assertEquals(Ranks.RANK9.getRate(), handEvaluator.ratings[5]);
    }
    
}
