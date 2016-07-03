package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.bonuses.ScoreBonus;
import server.model.gameTable.ColourBonusTile;

public class ColourBonusTileTest {

	@Test
	public void testGetterEqualsHashCode() {
		String colour="blue";
		ScoreBonus bonus= new ScoreBonus(1);
		ColourBonusTile tile= new ColourBonusTile(bonus, colour);
		ColourBonusTile tile1= new ColourBonusTile(bonus, colour);
		assertTrue(tile.getBonus()==bonus);
		assertTrue(tile.getColour()==colour);
		assertEquals(3028987, tile.hashCode());
		assertTrue(tile.equals(tile1));
	}

}
