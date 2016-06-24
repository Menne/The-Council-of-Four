package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import org.junit.Test;

import server.model.bonus.ScoreBonus;
import server.model.gameTable.RegionBonusTile;

public class RegionBonusTileTest {

	@Test
	public void testGetterHashCodeEquals() {
		String region="Sea";
		ScoreBonus bonus= new ScoreBonus(1);
		RegionBonusTile tile= new RegionBonusTile(bonus, region);
		RegionBonusTile tile1= new RegionBonusTile(bonus, region);
		assertTrue(tile.getBonus()== bonus);
		assertTrue(tile.getRegion()==region);
		assertEquals(84944, tile.hashCode());
		assertTrue(tile.equals(tile1));
	}

}
