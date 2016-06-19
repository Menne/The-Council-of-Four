package server.model.gameTable;

import java.util.Set;

import server.model.bonus.Bonus;

/**
 * modelizes reward tokens as a set of bonuses
 * @author andreapasquali
 *
 */
public class RewardToken {

	private final Set<Bonus> rewardToken;
	
	public RewardToken(Set<Bonus> rewardTokenBonus){
		this.rewardToken=rewardTokenBonus;
	}
	
	public Set<Bonus> getRewardTokenBonus() {
		return rewardToken;
	}

	@Override
	public String toString() {
		return rewardToken.toString();
	}
	
	
}
