
package model;

import java.util.Set;

import bonus.Bonus;

/**
 * 
 * @author andreapasquali
 *
 */
public class RewardToken {
	private final Set<Bonus> RewardTokenBonus;

	public RewardToken(Set<Bonus> RewardTokenBonus){
		this.RewardTokenBonus=RewardTokenBonus;
	}

	public Set<Bonus> getRewardTokenBonus() {
		return RewardTokenBonus;
	}


}