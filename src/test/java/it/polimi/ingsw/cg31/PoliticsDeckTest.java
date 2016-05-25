package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import server.model.gameTable.CardColour;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.PoliticsDeck;

public class PoliticsDeckTest {

	@Test
	public void testPickCard() {
		CardColour a=new CardColour("a");
		
		Set<CardColour> colors= new HashSet<CardColour>();
		colors.add(a);
		
		PoliticsDeck deck=new PoliticsDeck(colors);
		PoliticsCard card=new PoliticsCard(a);
		assertEquals(card, deck.pickCard());
		
	}
	
	@Test
	public void testRandomColour() {
		CardColour a=new CardColour("a");
		CardColour b=new CardColour("b");
		
		Set<CardColour> colors= new HashSet<CardColour>();

		colors.add(b);
		
		PoliticsDeck deck=new PoliticsDeck(colors);
		CardColour x=deck.randomColour();
		if(x==a)
			assertTrue(true);
		else{
		if(x==b)
			assertTrue(true);
		else
			assertTrue(false);	
		}
	}

}
