package server.model.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import players.Player;

public class Market {
	
	private final List<Offer> offersList;
	private final List<Player> sellingPlayerList;
	private final List<Player> buyingPlayerList;
	private Player currentPlayer;
	
	public Market(List<Player> players){
		this.offersList=new ArrayList<Offer>();
		this.sellingPlayerList=players;
		this.buyingPlayerList=new ArrayList<Player>();
		this.buyingPlayerList.addAll(players);
	}



	public Player getCurrentPlayer() {
		return currentPlayer;
	}


	public void addOffer(Player offeringPlayer, Marketable offeredObject, int price){
		this.offersList.add(new Offer(offeringPlayer,offeredObject,price));
	}
	
	/**
	 * it gives to buying player the object and decrements his coins; removes from the selling player 
	 * the object and increment his coins
	 * @param offer is the object to buy
	 * @param buyingPlayer is the player who accept the offer
	 */
	public void buyOffer(Offer offer, Player buyingPlayer){
		buyingPlayer.decrementCoins(offer.getPrice());
		offer.getOfferingPlayer().incrementCoins(offer.getPrice());
		offer.getOfferedObject().addObjectToPlayer(buyingPlayer);
		offer.getOfferedObject().removeObjectFromPlayer(offer.getOfferingPlayer());
		this.offersList.remove(offer);
	}
	
	public void addPlayer(Player player){
		this.buyingPlayerList.add(player);
	}
	
	public void shuffleBuyingPlayerList(){
		Collections.shuffle(this.buyingPlayerList);
	}
	
	public void clearMarket(){
		this.offersList.clear();
	}
	
	public void sellingNextPlayer(){
		this.currentPlayer=this.sellingPlayerList.remove(0);
	}
	
	public boolean isSellingPhaseFinished(){
		return this.sellingPlayerList.isEmpty();
	}
	
	public void buyingNextPlayer(){
		this.currentPlayer=this.buyingPlayerList.remove(0);
	}
	
	public boolean isBuyingPhaseFinished(){
		return this.buyingPlayerList.isEmpty();
	}

	public List<Offer> getOffersList() {
		return offersList;
	}

	public List<Player> getSellingPlayerList() {
		return sellingPlayerList;
	}

	public List<Player> getBuyingPlayerList() {
		return buyingPlayerList;
	}
	
	
}
