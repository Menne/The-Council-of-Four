package server.model.market;

import server.model.player.Player;

public class Offer {

	private final Player offeringPlayer;
	private final Marketable offeredObject;
	private final int price;
	
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
		if (offeringPlayer == null) {
			if (other.offeringPlayer != null)
				return false;
		} else if (!offeringPlayer.equals(other.offeringPlayer))
			return false;
		if (price != other.price)
			return false;
		return true;
	}
	
}
