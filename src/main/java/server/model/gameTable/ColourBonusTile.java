package server.model.gameTable;

import server.model.bonus.ScoreBonus;

public class ColourBonusTile extends BonusTile {
	
	public final String colour;
	
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
