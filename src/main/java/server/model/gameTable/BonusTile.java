package server.model.gameTable;

import server.model.bonuses.ScoreBonus;

/**
 * modelizes bonus tiles and has an attribute of type Score Bonus  
 * @author cg31
 *
 */
public abstract class BonusTile {

	protected final ScoreBonus bonus;
	
	/**
	 * Constructor of bonus tile 
	 * @param bonus is the Score Bonus of the tile
	 */
	public BonusTile(ScoreBonus bonus) {
		this.bonus=bonus;
	}

	public ScoreBonus getBonus() {
		return bonus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonus == null) ? 0 : bonus.hashCode());
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
		BonusTile other = (BonusTile) obj;
		if (bonus == null) {
			if (other.bonus != null)
				return false;
		} else if (!bonus.equals(other.bonus))
			return false;
		return true;
	}
	
	
}
