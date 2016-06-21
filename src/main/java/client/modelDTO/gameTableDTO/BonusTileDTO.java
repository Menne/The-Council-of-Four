package client.modelDTO.gameTableDTO;

import client.modelDTO.ModelDTO;
import server.model.bonus.ScoreBonus;

public class BonusTileDTO implements ModelDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2423281690426357532L;
	private final String type;
	private final ScoreBonus bonus;
	
	public BonusTileDTO(String type, ScoreBonus bonus) {
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
