package client.modelDTO.playerDTO;

import client.modelDTO.ModelDTO;
import server.model.bonus.ScoreBonus;

public class BonusTile implements ModelDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2423281690426357532L;
	private final String type;
	private final ScoreBonus bonus;
	
	public BonusTile(String type, ScoreBonus bonus) {
		this.type=type;
		this.bonus=bonus;
	}

	public String getType() {
		return type;
	}

	public ScoreBonus getBonus() {
		return bonus;
	}
	
	

}
