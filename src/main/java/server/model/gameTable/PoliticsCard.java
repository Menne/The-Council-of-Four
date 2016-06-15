package server.model.gameTable;

import server.model.market.Marketable;
import server.model.player.Player;

/**
	 * this class models a politics card
	 * @author Emanuele
	 *
	 */

public class PoliticsCard implements Marketable{
	
	private final CardColour colour;
	
	/**
	 * constructor of a politics card
	 * @param colour is the colour of the card (NB same colours of concillours!)
	 */
	public PoliticsCard(CardColour colour) {
		this.colour=colour;
	}
	
	public CardColour getColour() {
		return colour;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((colour == null) ? 0 : colour.hashCode());
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
		PoliticsCard other = (PoliticsCard) obj;
		if (colour == null) {
			if (other.colour != null)
				return false;
		} else if (!colour.equals(other.colour))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return colour.toString();
	}

	@Override
	public void addObjectToPlayer(Player player) {
		
		player.getHand().add(this);
		
	}

	
	@Override
	public void removeObjectFromPlayer(Player player) {

		player.getHand().remove(this);
		
	}
	
	
	
	
}