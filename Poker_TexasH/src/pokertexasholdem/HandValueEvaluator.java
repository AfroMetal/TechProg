package pokertexasholdem;

import java.awt.List;
import java.util.ArrayList;
import java.util.Arrays;

import pokertexasholdem.Card.Suits;

public class HandValueEvaluator {

	/**
	 * Contains rating of Special Value at index 0, then ratings of Value cards,
	 * then other cards sorted high to low
	 */
	public int[] ratings;
	/**
	 * Will contain highest evaluated Special Value or High Card if none is
	 * found
	 */
	private HandValue evaluatedValue;
	/**
	 * Will be calculated value of all ratings used to compare hands
	 */
	private int summaryValue = 0;
	/**
	 * Array of cards to Evaluate made of 5 board and 2 player cards
	 */
	private Card[] cardsToEvaluate;
	/**
	 * Hand value factors to assure more important cards are more important
	 * (there are rates from 0-12 so multiplying by 13^rateimportancein6rates
	 * will do)
	 */
	private final int[] FACTORS = { 371293, 28561, 2197, 169, 13, 1 };

	public HandValueEvaluator() {
		cardsToEvaluate = new Card[7];
		ratings = new int[6];
	}

	public HandValueEvaluator(ArrayList<Card> board, Hand hand) {
		cardsToEvaluate = new Card[7];
		ratings = new int[6];
		int index = 0;
		for (Card card : board) {
			addCardToEvaluate(card, index);
			index++;
		}
		for (Card card : hand.getHand()) {
			addCardToEvaluate(card, index);
			index++;
		}
	}

	// allows to add Board cards and Player cards to make 7 cards array to
	// evaluate
	public void addCardToEvaluate(Card card, int index) {
		cardsToEvaluate[index] = card;
	}

	/**
	 * 
	 * @return
	 */
	public void calculateSummaryValue(){
		for(int i = 0; i < ratings.length; i++ ){
			summaryValue += ratings[i] * FACTORS[i];
		}
	}
	
	/**
	 * Checks all possible values
	 * 
	 * @return found value
	 */
	public HandValue isSpecialValue() {
		// when first (highest) Special Value is evaluated it will trigger if
		// statement and return itself
		// no more Values will be checked
		sortByRank();
		if (isStraightFlush() || isFourOfAKind() || isFullHouse() || isFlush() || isStraight() || isThreeOfAKind()
				|| isTwoPair() || isOnePair()) {
			calculateSummaryValue();
			return evaluatedValue;
		}
		// when no Special Value is found, High Card is triggered
		else {
			isHighCard();
			calculateSummaryValue();
			return evaluatedValue;
		}
	}

	/**
	 * Method sets evaluatedValue to High Card as it is only triggered when no
	 * other Special Value was found. ratings[0] is set to High Card rate and
	 * ratings[1-5] are 5 highest card's rates sorted high to low.
	 */
	public void isHighCard() {

		setEvaluatedValue(HandValue.HIGH_CARD);
		ratings[0] = evaluatedValue.getRate();

		int index = 1;
		for (Card card : cardsToEvaluate) {
			ratings[index++] = card.getRate();
			if (index > 5)
				break;
		}

	}

	public boolean isOnePair() {

		for (int i = 0; i <= 5; i++) {
			int firstCardRate = cardsToEvaluate[i].getRate();
			int secondCardRate = cardsToEvaluate[i + 1].getRate();

			if (firstCardRate == secondCardRate) {
				int pairRate = firstCardRate;
				setEvaluatedValue(HandValue.ONE_PAIR);
				ratings[0] = evaluatedValue.getRate();
				ratings[1] = pairRate;
				ratings[2] = pairRate;

				int index = 3;
				for (Card card : cardsToEvaluate) {
					int rate = card.getRate();
					if (rate != pairRate) {
						ratings[index++] = rate;
						if (index > 5)
							break;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isTwoPair() {

		for (int i = 0; i <= 5; i++) {
			int pairOneFirstCardRate = cardsToEvaluate[i].getRate();
			int pairOneSecondCardRate = cardsToEvaluate[i + 1].getRate();

			if (pairOneFirstCardRate == pairOneSecondCardRate) {
				int firstPairRate = pairOneFirstCardRate;

				for (int j = i + 2; j <= 5; j++) {
					int pairTwoFirstCardRate = cardsToEvaluate[j].getRate();
					int pairTwoSecondCardRate = cardsToEvaluate[j + 1].getRate();

					if (pairTwoFirstCardRate == pairTwoSecondCardRate) {
						int secondPairRate = pairTwoFirstCardRate;

						setEvaluatedValue(HandValue.TWO_PAIR);
						ratings[0] = evaluatedValue.getRate();
						ratings[1] = firstPairRate;
						ratings[2] = firstPairRate;
						ratings[3] = secondPairRate;
						ratings[4] = secondPairRate;

						for (Card card : cardsToEvaluate) {
							int rate = card.getRate();
							if (rate != firstPairRate && rate != secondPairRate) {
								ratings[5] = rate;
								break;
							}
						}
						return true;
					}
				}
			}
		}
		return false;
	}

	public boolean isThreeOfAKind() {

		for (int i = 0; i <= 4; i++) {
			int firstCardRate = cardsToEvaluate[i].getRate();
			int secondCardRate = cardsToEvaluate[i + 1].getRate();
			int thirdCardRate = cardsToEvaluate[i + 2].getRate();

			if (firstCardRate == secondCardRate && secondCardRate == thirdCardRate) {
				int threeOfAKindRate = firstCardRate;
				setEvaluatedValue(HandValue.THREE_OF_A_KIND);
				ratings[0] = evaluatedValue.getRate();
				ratings[1] = threeOfAKindRate;
				ratings[2] = threeOfAKindRate;
				ratings[3] = threeOfAKindRate;

				int index = 4;
				for (Card card : cardsToEvaluate) {
					int rate = card.getRate();
					if (rate != threeOfAKindRate) {
						ratings[index++] = rate;
						if (index > 5)
							break;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isStraight() {
		for (int i = 0; i <= 2; i++) {
			int firstCardRate = cardsToEvaluate[i].getRate();
			int secondCardRate = cardsToEvaluate[i + 1].getRate() + 1;
			int thirdCardRate = cardsToEvaluate[i + 2].getRate() + 2;
			int fourthCardRate = cardsToEvaluate[i + 3].getRate() + 3;
			int fifthCardRate = cardsToEvaluate[i + 4].getRate() + 4;

			if (firstCardRate == secondCardRate && secondCardRate == thirdCardRate && thirdCardRate == fourthCardRate
					&& fourthCardRate == fifthCardRate) {
				setEvaluatedValue(HandValue.STRAIGHT);
				ratings[0] = evaluatedValue.getRate();
				for (int j = 0; j <= 4; j++) {
					ratings[j + 1] = cardsToEvaluate[i + j].getRate();
				}
				return true;
			}
		}
		return false;
	}

	public boolean isFlush() {
		int flushCounter = 0;

		for (Suits suit : Suits.values()) {
			for (Card card : cardsToEvaluate) {
				if (card.getSuit() == suit) {
					flushCounter++;
					if (flushCounter == 5) {
						setEvaluatedValue(HandValue.FLUSH);
						ratings[0] = evaluatedValue.getRate();

						int index = 1;
						for (Card card2 : cardsToEvaluate) {
							if (card2.getSuit() == suit) {
								ratings[index++] = card2.getRate();
								if (index > 5)
									break;
							}
						}
						return true;
					}
				}
			}
			flushCounter = 0;
		}
		return false;
	}

	public boolean isFullHouse() {
		for (int i = 0; i <= 4; i++) {
			int tripletFirstCardRate = cardsToEvaluate[i].getRate();
			int tripletSecondCardRate = cardsToEvaluate[i + 1].getRate();
			int tripletThirdCardRate = cardsToEvaluate[i + 2].getRate();

			if (tripletFirstCardRate == tripletSecondCardRate && tripletSecondCardRate == tripletThirdCardRate) {
				int tripletRate = tripletFirstCardRate;

				for (int j = 0; j <= 5; j++) {

					if (cardsToEvaluate[j].getRate() != tripletRate) {
						int pairFirstCardRate = cardsToEvaluate[j].getRate();
						int pairSecondCardRate = cardsToEvaluate[j + 1].getRate();

						if (pairFirstCardRate == pairSecondCardRate) {
							int pairRate = pairFirstCardRate;
							setEvaluatedValue(HandValue.FULL_HOUSE);
							ratings[0] = evaluatedValue.getRate();
							ratings[1] = tripletRate;
							ratings[2] = tripletRate;
							ratings[3] = tripletRate;
							ratings[4] = pairRate;
							ratings[5] = pairRate;

							return true;
						}
					}
				}
			}
		}
		return false;
	}

	public boolean isFourOfAKind() {

		for (int i = 0; i <= 3; i++) {
			int firstCardRate = cardsToEvaluate[i].getRate();
			int secondCardRate = cardsToEvaluate[i + 1].getRate();
			int thirdCardRate = cardsToEvaluate[i + 2].getRate();
			int fourthCardRate = cardsToEvaluate[i + 3].getRate();

			if (firstCardRate == secondCardRate && secondCardRate == thirdCardRate && thirdCardRate == fourthCardRate) {
				int fourOfAKindRate = firstCardRate;
				setEvaluatedValue(HandValue.FOUR_OF_A_KIND);
				ratings[0] = evaluatedValue.getRate();
				ratings[1] = fourOfAKindRate;
				ratings[2] = fourOfAKindRate;
				ratings[3] = fourOfAKindRate;
				ratings[4] = fourOfAKindRate;

				for (Card card : cardsToEvaluate) {
					int rate = card.getRate();
					if (rate != fourOfAKindRate) {
						ratings[5] = rate;
						break;
					}
				}
				return true;
			}
		}
		return false;
	}

	public boolean isStraightFlush() {
		for (int i = 0; i <= 2; i++) {
			int firstCardRate = cardsToEvaluate[i].getRate();
			int secondCardRate = cardsToEvaluate[i + 1].getRate() + 1;
			int thirdCardRate = cardsToEvaluate[i + 2].getRate() + 2;
			int fourthCardRate = cardsToEvaluate[i + 3].getRate() + 3;
			int fifthCardRate = cardsToEvaluate[i + 4].getRate() + 4;

			if (firstCardRate == secondCardRate && secondCardRate == thirdCardRate && thirdCardRate == fourthCardRate
					&& fourthCardRate == fifthCardRate) {
				if (cardsToEvaluate[i].getSuit() == cardsToEvaluate[i + 1].getSuit()
						&& cardsToEvaluate[i + 1].getSuit() == cardsToEvaluate[i + 2].getSuit()
						&& cardsToEvaluate[i + 2].getSuit() == cardsToEvaluate[i + 3].getSuit()
						&& cardsToEvaluate[i + 3].getSuit() == cardsToEvaluate[i + 4].getSuit()) {
					setEvaluatedValue(HandValue.STRAIGHT_FLUSH);
					ratings[0] = evaluatedValue.getRate();
					for (int j = 0; j <= 4; j++) {
						ratings[j + 1] = cardsToEvaluate[i + j].getRate();
					}
					return true;
				}
				break;
			}
		}
		return false;
	}

	public void sortByRank() {
		Arrays.sort(cardsToEvaluate, new RateComparator());
	}

	public HandValue getSpecialValue() {
		return evaluatedValue;
	}

	public Card[] getCardsToEvaluate() {
		return cardsToEvaluate;
	}

	private void setEvaluatedValue(HandValue evaluatedValue) {
		this.evaluatedValue = evaluatedValue;
	}
	
	public int getSummaryValue() {
		return summaryValue;
	}

}
