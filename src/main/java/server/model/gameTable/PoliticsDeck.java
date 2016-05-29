package server.model.gameTable;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;


	/**
	 * Is a generator of random Politics Card
	 * @author Emanuele
	 *
	 */

public class PoliticsDeck{

	private final Set<CardColour> cardColours;
	
	public PoliticsDeck(Set<CardColour> cardColours) {
		this.cardColours=cardColours;
	}
	
	/**
	 * Private because used just from the pick card method.
	 * @return a random colour among the available colours.
	 */
	public CardColour randomColour(){
		int rnd=new Random().nextInt(cardColours.size());
		Iterator<CardColour> iter=this.cardColours.iterator();
		for(int i=0; i<rnd; i++)
			iter.next();
		return iter.next();
	}
	
	/**
	 * Generate a random politics card
	 * @return a random politics card
	 */
	public PoliticsCard pickCard(){
		return new PoliticsCard(this.randomColour());
	}
	
}