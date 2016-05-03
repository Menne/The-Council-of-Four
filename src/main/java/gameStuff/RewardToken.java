
package gameStuff;

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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((RewardTokenBonus == null) ? 0 : RewardTokenBonus.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RewardToken other = (RewardToken) obj;
		if (RewardTokenBonus == null) {
			if (other.RewardTokenBonus != null)
				return false;
		} else if (!RewardTokenBonus.equals(other.RewardTokenBonus))
			return false;
		return true;
	}


}