package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import org.junit.Test;

import model.gameTable.CardColour;

public class CardColourTest {

	@Test
	public void testCardColourConstructorAndGetter() {
		CardColour colour= new CardColour("blu");
		assertEquals("blu", colour.getColour());
	}
	

}
