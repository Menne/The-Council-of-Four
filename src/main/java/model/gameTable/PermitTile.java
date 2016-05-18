package model.gameTable;

import java.util.Set;
import model.bonus.Bonus;
import model.market.Marketable;
import players.Player;

/**
	 * this class models a permit tile
	 * @author Emanuele
	 *
	 */

public class PermitTile implements Marketable{
	
	private final Set<City> buildableCities;
	private final Set<Bonus> bonus;
	
	/**
	 * constructor of a permit tile
	 * @param buildableCities is the set of cities where you can build in
	 * @param bonus is the bonus associated to the permit tile 
	 */
	public PermitTile(Set<City> buildableCities, Set<Bonus> bonus, PermitDeck deck) {
		this.buildableCities=buildableCities;
		this.bonus=bonus;
		deck.getPermitTiles().add(this);
	}
	
	public Set<City> getBuildableCities() {
		return buildableCities;
	}
	
	public Set<Bonus> getBonus() {
		return bonus;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonus == null) ? 0 : bonus.hashCode());
		result = prime * result + ((buildableCities == null) ? 0 : buildableCities.hashCode());
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
		PermitTile other = (PermitTile) obj;
		if (bonus == null) {
			if (other.bonus != null)
				return false;
		} else if (!bonus.equals(other.bonus))
			return false;
		if (buildableCities == null) {
			if (other.buildableCities != null)
				return false;
		} else if (!buildableCities.equals(other.buildableCities))
			return false;
		return true;
	}

	
	@Override
	public void addObjectToPlayer(Player player) {
		player.getPlayersPermitTilesTurnedUp().add(this);
	}

	@Override
	public void removeObjectFromPlayer(Player player) {
		player.getPlayersPermitTilesTurnedUp().remove(this);
		
	}
	
	
}