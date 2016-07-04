package server.model.gameTable;

import server.model.bonuses.ScoreBonus;

/**
 * Models colour bonus tiles and has an attribute String that rappresent the colour of the tile
 * @author cg31
 *
 */
public class ColourBonusTile extends BonusTile {
	
	public final String colour;
	
	/**
	 * constructor of the ColourBonusTile
	 * @param bonus ScoreBonus of the tile
	 * @param colour of the tile
	 */
	public ColourBonusTile(ScoreBonus bonus, String colour) {
		super(bonus);
		this.colour=colour;
	}

	public String getColour() {
		return colour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		ColourBonusTile other = (ColourBonusTile) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		return true;
	}
	
	

	
}
