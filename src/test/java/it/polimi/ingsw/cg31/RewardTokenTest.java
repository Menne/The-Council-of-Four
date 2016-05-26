package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;

import server.model.bonus.AssistantsBonus;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.RewardToken;

public class RewardTokenTest {

	@Test
	public void test() {
		Bonus bonus1=new ScoreBonus(1);
		Bonus bonus2=new AssistantsBonus(3);
		
		Set<Bonus> rewardTokenBonus=new HashSet<Bonus>();
		rewardTokenBonus.add(bonus1);
		rewardTokenBonus.add(bonus2);
		
		RewardToken token= new RewardToken(rewardTokenBonus);
		assertEquals(rewardTokenBonus, token.getRewardTokenBonus());
	}

}
