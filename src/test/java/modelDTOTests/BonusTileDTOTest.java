package modelDTOTests;

import static org.junit.Assert.*;

import org.junit.Test;

import client.modelDTO.gameTableDTO.BonusTileDTO;
import server.model.bonus.ScoreBonus;

public class BonusTileDTOTest {

	@Test
	public void testGetterAndSetter() {
		String type="king";
		ScoreBonus bonus= new ScoreBonus(1);
		BonusTileDTO bonusTile= new BonusTileDTO(type , bonus);
		BonusTileDTO bonusTile1= new BonusTileDTO(type , bonus);
		assertTrue(bonusTile.getBonus()==bonus);
		assertTrue(bonusTile.getType()==type);
		assertEquals(bonusTile.toString(),bonus.toString());
		assertTrue(bonusTile.equals(bonusTile1));
		assertEquals(3294008, bonusTile.hashCode());
	}

}
