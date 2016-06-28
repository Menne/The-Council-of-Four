package server.model.market;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import server.model.Game;
import server.model.player.Player;

/**
 * models the Market: its attributes are the selling players, the buying players and the list of offers
 * @author andreapasquali
 *
 */
public class Market {
	
	private final List<Offer> offersList;
	private final List<Player> sellingPlayerList;
	private final List<Player> buyingPlayerList;
	
	/**
	 * constructor of the Market;
	 * it initializes the offering players list, the buying players list and the offers list empty 
	 */
	public Market(){
		this.offersList=new ArrayList<>();
		this.sellingPlayerList=new ArrayList<>();
		this.buyingPlayerList=new ArrayList<>();
	}

	/**
	 * adds an offer to the offerrsList
	 * @param offer is the offer to add
	 */
	public void addOffer(Offer offer){
		this.offersList.add(offer);
	}
	
	/**
	 * it gives to buying player the object and decrements his coins; removes from the selling player 
	 * the object and increment his coins
	 * @param offer is the object to buy
	 * @param buyingPlayer is the player who accept the offer
	 */
	public void removeOffer(Offer offer){
		for (Offer offerInList : this.offersList)
			if (offer.equals(offerInList)) {
				this.offersList.remove(offer);
				break;
			}
	}
	/**
	 * add player to buying player list
	 * @param player
	 */
	public void addPlayer(Player player){
		this.buyingPlayerList.add(player);
	}
	
	/**
	 * orders the Selling player list using the player number
	 * (it keeps the order of the game)
	 */
	public void sortSellingPlayerList(){
		Collections.sort(this.sellingPlayerList, new Comparator<Player>(){

			@Override
			public int compare(Player arg0, Player arg1) {
				if(arg0.getPlayerNumber()==arg1.getPlayerNumber())
					return 0;
				return arg0.getPlayerNumber()<arg1.getPlayerNumber() ? -1 : 1;
			}
		     
		});
	}
	
	/**
	 * shuffles the buyngPlayerList
	 */
	public void shuffleBuyingPlayerList(){
		Collections.shuffle(this.buyingPlayerList);
	}
	
	/**
	 * clears the offers list, the selling player list and the buying player list
	 */
	public void clearMarket(){
		this.offersList.clear();
		this.buyingPlayerList.clear();
		this.sellingPlayerList.clear();
	}
	
	/**
	 * removes the current player of the game form the selling player list
	 * @param game
	 */
	public void sellingNextPlayer(Game game){
		game.setCurrentPlayer(this.sellingPlayerList.remove(0));
	}
	
	/**
	 * removes the current player of the game form the buying player list
	 * @param game
	 */
	public void buyingNextPlayer(Game game){
		game.setCurrentPlayer(this.buyingPlayerList.remove(0));
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
