package server.model.gameTable;

import java.util.HashSet;
import java.util.Set;

import server.model.bonuses.Bonus;
import server.model.market.Marketable;
import server.model.player.Player;

/**
	 * this class models a permit tile
	 * contains methods for the market (add to player and remove from player)
	 * @author Emanuele
	 *
	 */

public class PermitTile implements Marketable{

	private final Set<City> buildableCities;
	private final Set<Bonus> bonuses;
	
	/**
	 * constructor of a permit tile
	 * @param buildableCities is the set of cities where you can build in
	 * @param bonus is the bonus associated to the permit tile 
	 */
	public PermitTile(Set<City> buildableCities, Set<Bonus> bonuses, PermitDeck deck) {
		this.buildableCities=buildableCities;
		this.bonuses=bonuses;
		deck.getPermitTiles().add(this);
	}
	
	public Set<City> getBuildableCities() {
		return this.buildableCities;
	}
	
	public Set<Bonus> getBonuses() {
		return this.bonuses;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((bonuses == null) ? 0 : bonuses.hashCode());
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
		if (bonuses == null) {
			if (other.bonuses != null)
				return false;
		} else if (!bonuses.equals(other.bonuses))
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
		if(!player.getPlayersPermitTilesTurnedUp().contains(this))
			throw new IllegalArgumentException("player hasn't this tile turned up");
		player.getPlayersPermitTilesTurnedUp().remove(this);
	}
	
	@Override
	public String toString() {
		Set<String> cities=new HashSet<>();
		for(City city : buildableCities)
			cities.add(city.getName());
		return cities + "\t" + bonuses;
	}
	
	
}