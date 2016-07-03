package modelDTOTests;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.RewardTokenDTO;
import server.model.bonuses.Bonus;
import server.model.bonuses.ScoreBonus;

public class RewardTokenDTOTest {

	@Test
	public void testGetterSetterHashCodeToStringEquals() {
		RewardTokenDTO tokenDTO= new RewardTokenDTO();
		Set<Bonus> bonuses= new HashSet<>();
		Bonus bonus= new ScoreBonus(1);
		bonuses.add(bonus);
		RewardTokenDTO tokenDTO1= new RewardTokenDTO(bonuses);
		tokenDTO.setBonuses(bonuses);
		assertTrue(tokenDTO.getBonuses()==bonuses);
		assertTrue(tokenDTO.equals(tokenDTO1));
		assertEquals(63, tokenDTO.hashCode());
		assertEquals(bonuses.toString(), tokenDTO.toString());
	}

}
