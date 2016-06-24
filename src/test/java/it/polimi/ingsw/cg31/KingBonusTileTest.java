package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.bonus.ScoreBonus;
import server.model.gameTable.KingBonusTile;

public class KingBonusTileTest {

	@Test
	public void testConstructor() {
		ScoreBonus bonus= new ScoreBonus(1);
		KingBonusTile tile= new KingBonusTile(bonus);
		assertTrue(tile.getBonus()==bonus);
	}

}
