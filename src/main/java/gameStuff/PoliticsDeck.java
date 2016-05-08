package gameStuff;

import java.util.Iterator;
import java.util.Random;
import java.util.Set;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;


	/**
	 * Is a generator of random Politics Card
	 * @author Emanuele
	 *
	 */
@XmlAccessorType(XmlAccessType.FIELD)
public class PoliticsDeck{
	
	@XmlElementWrapper(name="cardColours")
	@XmlElement(name="CardColour")
	private final Set<CardColour> cardColours;
	
	public PoliticsDeck(Set<CardColour> cardColours) {
		this.cardColours=cardColours;
	}
	
	/**
	 * Private because used just from the pick card method.
	 * @return a random colour among the available colours.
	 */
	private CardColour randomColour(){
		int rnd=new Random().nextInt(cardColours.size());
		int i=0;
		Iterator<CardColour> iter=this.cardColours.iterator();
		for(i=0; i<rnd; i++)
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