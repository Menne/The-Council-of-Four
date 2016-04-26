package gameStuff;

import java.util.Collections;
import java.util.List;
import java.util.ArrayList;

	/**
	 * this class models an abstract deck
	 * @author Emanuele
	 *
	 */

public abstract class Deck<T> {

	protected final List<T> cards;
	
	/**
	 * constructor of Deck
	 * @param cards is the list of cards in a generic deck
	 */
	public Deck(List<T> cards) {
		this.cards=cards;
	}

	/**
	 * shuffles the deck
	 */
	public void shuffle(List<T> cards) {
		Collections.shuffle(cards);
	}
		
	/**
	 * pick a generic card, it's not specified if you pick from the 
	 * politics deck or from the permit tiles deck. returns a card
	 * @return the card you have picked up
	 */
	public T pickCard() {
		T pickedCard=this.cards.get(this.cards.indexOf(this.cards.size()));
		this.cards.remove(this.cards.indexOf(this.cards.size()));
		return pickedCard;
	}
	
	public void addCard (T cardToAdd) {
		this.cards.add(0,cardToAdd);
	}

	public List<T> getCards() {
		return cards;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((cards == null) ? 0 : cards.hashCode());
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
		return true;
	}
	
}