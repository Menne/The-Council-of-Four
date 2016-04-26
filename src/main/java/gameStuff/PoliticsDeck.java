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
	 * @param numberOfElements is the number of politics cards in the deck
	 * @param cards is the list of cards in the politics deck
	 */
	public PoliticsDeck(int numberOfElements, List<Card> cards) {
		super(numberOfElements, cards);
		
		//for (int i=0; i < numberOfElements; ++i)
		//	  cards.add(new PoliticsCard);
	}
	
	
	/**
	 * pick a politics card, if the politics card is empty 
	 * it will be recomposed. returns a card
	 * @cards is the list of Card 
	 */
	@Override
	public void pickCard(List<Card> cards) {
		int i=getNumberOfElements();
		if (!cards.isEmpty()) {
			Card PickedCard=cards.get(i);
			cards.remove(i);
			Player.Hand.add(pickedCard);
			i--;
		}
		if (cards.isEmpty())
			cards.recomposeDeck(discard);
	}
	
	/**
	 * when you use a politics card then you add it to the discard deck
	 * @param card1 is the card you have used
	 * @param discard is the discard deck in which you are putting the used card
	 */
	public void addCard (Card discardedCard) {
		discard.add(discardedCard);
	}
	
	/**
	 * when a deck is out of cards, recompose a new deck 
	 * from discarded cards
	 */
	public void recomposeDeck() {
		ArrayList<PoliticsCard> politicsCards = new ArrayList<PoliticsCard>();
		for (int i=0; discard.ListSize(); i++) {
			politicsCards.add(Card);
		}
		cards.shuffle(politicsCards);
	}
	
	
}