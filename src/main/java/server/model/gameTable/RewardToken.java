package server.model.gameTable;

import java.util.Set;

import server.model.bonuses.Bonus;

/**
 * modelizes reward tokens as a set of bonuses
 * @author cg31
 *
 */
public class RewardToken {

	private final Set<Bonus> rewardTokenBonus;
	
	/**
	 * constructor of RewardToken
	 * @param rewardTokenBonus is the set of bonuses of the rewardToken
	 */
	public RewardToken(Set<Bonus> rewardTokenBonus){
		this.rewardTokenBonus=rewardTokenBonus;
	}
	
	public Set<Bonus> getRewardTokenBonus() {
		return this.rewardTokenBonus;
	}

	@Override
	public String toString() {
		return rewardTokenBonus.toString();
	}
	
	
}
