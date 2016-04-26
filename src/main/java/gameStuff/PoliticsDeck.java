package gameStuff;

import java.util.List;
import java.util.ArrayList;
import java.util.Collection;

import players.Player;

	/**
	 * models the politics deck
	 * @author Emanuele
	 *
	 */

public class PoliticsDeck extends Deck {

	/**
	 * constructor of the politics deck
	 * @param cards is the list of cards in the politics deck
	 */
	public PoliticsDeck(List<PoliticsCard> cards) {
		super(cards);
	}
	
	
	/**
	 * pick a politics card, if the politics deck is empty 
	 * it will be recomposed. returns a card
	 * @cards is the list of Card
	 * @return the card you have picked 
	 */
	@Override
	public PoliticsCard pickCard() {
		PoliticsCard pickedCard=this.cards.get(this.cards.indexOf(this.cards.size()));
		this.cards.remove(this.cards.indexOf(this.cards.size()));
		return pickedCard;
	}
	
	/**
	 * when you use a politics card then you add it to the discard deck
	 * @param card1 is the card you have used
	 * @param discard is the discard deck in which you are putting the used card
	 */
	public void addCard (Card discardedCard) {
		this.cards.add(0);
	}
	
	
}