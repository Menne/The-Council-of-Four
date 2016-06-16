package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.gameTable.CardColour;

public class CardColourTest {

	@Test
	public void testCardColourConstructorAndGetter() {
		CardColour colour= new CardColour("blu");
		assertTrue("blu"==colour.getColour());
	}
	
	@Test
	public void testHashCodeEqualsAndToString() {
		CardColour colour1= new CardColour("blu");
		CardColour colour2= new CardColour("blu");
		assertEquals(97674, colour1.hashCode());
		assertTrue(colour1.equals(colour2));
		assertEquals(colour1.getColour(), colour1.toString());
	}
}
