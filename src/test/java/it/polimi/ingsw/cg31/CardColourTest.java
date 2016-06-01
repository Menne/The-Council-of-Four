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
}
