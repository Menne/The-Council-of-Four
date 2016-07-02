package client.modelDTO.gameTableDTO;

import java.util.Set;

import client.modelDTO.ModelDTO;
import server.model.bonus.Bonus;

/**
 * This class provides all the informations about a reward token, but without logic
 * @author cg31
 *
 */
public class RewardTokenDTO implements ModelDTO {

	private static final long serialVersionUID = 8196968018708236782L;
	private Set<Bonus> bonuses;
	
	/**
	 * Constructor of RewardTokenDTO
	 */
	public RewardTokenDTO() {
		//non-arguments constructor
	}
	
	/**
	 * Constructor of RewardTokenDTO
	 * @param bonuses are the bonuses of the token
	 */
	public RewardTokenDTO(Set<Bonus> bonuses) {
		this.bonuses=bonuses;
	}

	public Set<Bonus> getBonuses() {
		return this.bonuses;
	}

	public void setBonuses(Set<Bonus> bonuses) {
		this.bonuses = bonuses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonuses == null) ? 0 : bonuses.hashCode());
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
		RewardTokenDTO other = (RewardTokenDTO) obj;
		if (bonuses == null) {
			if (other.bonuses != null)
				return false;
		} else if (!bonuses.equals(other.bonuses))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return bonuses.toString();
	}
	
	

}
