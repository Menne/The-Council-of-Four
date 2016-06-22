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

	@Override
	public String toString() {
		return bonus.toString();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonus == null) ? 0 : bonus.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		BonusTileDTO other = (BonusTileDTO) obj;
		if (bonus == null) {
			if (other.bonus != null)
				return false;
		} else if (!bonus.equals(other.bonus))
			return false;
		if (type == null) {
			if (other.type != null)
				return false;
		} else if (!type.equals(other.type))
			return false;
		return true;
	}
	
	

}
