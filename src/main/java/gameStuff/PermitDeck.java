package gameStuff;

import java.util.Collection;
import java.util.Set;
import java.util.HashSet;
import java.util.List;

	/**
	 * deck of permit tiles
	 * @author Emanuele
	 *
	 */

public class PermitDeck extends Deck {

	/**
	 * constructor of the permit tiles deck
	 * @param numberOfElements is the number of elements of the deck
	 * @param cards are the cards of the deck
	 */
	public PermitDeck(int numberOfElements, List<PermitTile> cards) {
		super(numberOfElements, cards);
		//for (int i=0; i < numberOfElements; ++i)
		//	  cards.add(new PermitTile);
	}
	
	/**
	 * pick the tiles uncovered and add them to the bottom of the deck
	 * @param c1 the first card you add to the bottom
	 * @param c2 the second card you add to the bottom
	 */
	public void addOnBottom(PermitTile tile1, PermitTile tile2) {
		Set<PermitTile> toBottom= new HashSet<PermitTile>();
		PermitDeck.addAll(toBottom);
	}

		
		
}