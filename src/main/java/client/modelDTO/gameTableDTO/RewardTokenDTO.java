package client.modelDTO.gameTableDTO;

import java.util.Set;

import client.modelDTO.ModelDTO;
import server.model.bonus.Bonus;

public class RewardTokenDTO implements ModelDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8196968018708236782L;
	
	private Set<Bonus> bonuses;
	
	public RewardTokenDTO() {
	}
	
	public RewardTokenDTO(Set<Bonus> bonuses) {
		this.bonuses=bonuses;
	}

	public Set<Bonus> getBonuses() {
		return bonuses;
	}

	public void setBonuses(Set<Bonus> bonuses) {
		this.bonuses = bonuses;
	}
	
	

}
