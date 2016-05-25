package server.model.market;

import players.Player;

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
	
	
}
