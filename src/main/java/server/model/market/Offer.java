package server.model.market;

import server.model.player.Player;

/**
 * models an offer of the market.
 * its attributes are the offering player, the offered object and its price
 * @author andreapasquali
 *
 */
public class Offer {

	private final Player offeringPlayer;
	private final Marketable offeredObject;
	private final int price;
	
	/**
	 * constructor of an Offer
	 * @param offeringPlayer is the player who sells the object
	 * @param offeredObject is the object offered
	 * @param price is the price of the offer
	 */
	public Offer(Player offeringPlayer, Marketable offeredObject, int price){
		this.offeredObject=offeredObject;
		this.offeringPlayer=offeringPlayer;
		this.price=price;
	}

	public Player getOfferingPlayer() {
		return offeringPlayer;
	}

	public Marketable getOfferedObject() {
		return offeredObject;
	}

	public int getPrice() {
		return price;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((offeredObject == null) ? 0 : offeredObject.hashCode());
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
		Offer other = (Offer) obj;
		if (offeredObject == null) {
			if (other.offeredObject != null)
				return false;
		} else if (!offeredObject.equals(other.offeredObject))
			return false;
		return true;
	}
	
}
