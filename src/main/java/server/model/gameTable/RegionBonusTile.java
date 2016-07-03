package server.model.gameTable;

import server.model.bonuses.ScoreBonus;

/**
 * models the bonus tiles of the regions
 * its attributes are the region and the ScoreBonus(super)
 * @author andreapasquali
 *
 */
public class RegionBonusTile extends BonusTile{

	private final String region;
	
	/**
	 * constructor of the RegionBonusTile
	 * @param bonus is the Score Bonus of the tile
	 * @param region is the RegionBoard of the tile
	 */
	public RegionBonusTile(ScoreBonus bonus, String region) {
		super(bonus);
		this.region=region;
	}

	public String getRegion() {
		return region;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result + ((region == null) ? 0 : region.hashCode());
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
		RegionBonusTile other = (RegionBonusTile) obj;
		if (region == null) {
			if (other.region != null)
				return false;
		} else if (!region.equals(other.region))
			return false;
		return true;
	}
	
	

}
