package server.model.gameTable;

import java.util.Set;

import server.model.bonus.Bonus;

/**
 * modelizes reward tokens as a set of bonuses
 * @author andreapasquali
 *
 */
public class RewardToken {

	private final Set<Bonus> rewardTokenBonus;
	
	public RewardToken(Set<Bonus> rewardTokenBonus){
		this.rewardTokenBonus=rewardTokenBonus;
	}
	
	public Set<Bonus> getRewardTokenBonus() {
		return rewardTokenBonus;
	}
}
