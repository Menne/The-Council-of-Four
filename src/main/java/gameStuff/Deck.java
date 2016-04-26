package gameStuff;

import java.util.Set;
import java.util.Collections;
import java.util.List;

	/**
	 * this class models an abstract deck
	 * @author Emanuele
	 *
	 */

public abstract class Deck {

	private final int numberOfElements;
	private final List<Card> cards;
	
	/**
	 * constructor of Deck
	 * @param numberOfElements is the number of cards in the deck
	 * @param cards is the list of cards in the deck
	 */
	public Deck(int numberOfElements, List<Card> cards) {
		this.numberOfElements=numberOfElements;
		for (int i=0; i < numberOfElements; ++i)
			  cards.add(new Card());
	}

	/**
	 * shuffles the deck
	 */
	public void shuffle(List<Card> cards) {
		Collections.shuffle(cards);
	}
		
	/**
	 * pick a generic card, it's not specified if you pick from the 
	 * politics deck or from the permit tiles deck. returns a card
	 * @return the card you have picked up
	 */
	public Card pickCard() {
		int i=numberOfElements;
		Card pickedCard=cards.get(i);
		cards.remove(i);
		i--;
		return pickedCard;
	}

	public int getNumberOfElements() {
		return numberOfElements;
	}

	public List<Card> getCards() {
		return cards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
		result = prime * result + numberOfElements;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Deck other = (Deck) obj;
		if (cards == null) {
			if (other.cards != null)
				return false;
		} else if (!cards.equals(other.cards))
			return false;
		if (numberOfElements != other.numberOfElements)
			return false;
		return true;
	}
	
	
}