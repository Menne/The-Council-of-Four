package actions;

import java.util.ArrayList;
import java.util.List;

import bonus.Bonus;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

/**
 * This class is the main action "acquire permit tile", it operates on the 
 * protected attribute game through the method executeAction.
 * @author Emanuele
 *
 */

public class AcquirePermitTile extends MainAction {

	private Integer numberOfPermitTile;
	private List<PoliticsCard> cardsToDescard;
	private RegionBoard chosenRegion;

	/**
	 * constructor of the action
	 * @param game is the private attribute inherited from the superclass
	 * @param permitTileToPick is the permit tile you want to pick
	 * @param cardsToDescard are the cards in the hand you use to satisfy the councillors
	 * @param balconyToSatisfy is the council balcony to satisfy
	 *
	 */
	public AcquirePermitTile(Game game) {
		super(game);
	}
	
	
	
	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile = numberOfPermitTile;
	}



	public void setCardsToDescard(List<PoliticsCard> cardsToDescard) {
		this.cardsToDescard = cardsToDescard;
	}



	public void setChosenRegion(RegionBoard chosenRegion) {
		this.chosenRegion = chosenRegion;
	}



	/**
	 * Acquire a permit tile from a designated region board by satisfying the councillors:
	 * 4 cards of the same colour of the councillors,
	 * 3 cards of the same colour of the councillors + use 4 coins,
	 * 2 cards of the same colour of the councillors + use 7 coins,
	 * 1 card of the same colour of the councillors + use 10 coins;
	 * each rainbow card requires 1 additional coin each to use.
	 */
	public boolean executeAction() throws NullPointerException{
		if(this.numberOfPermitTile==null||
				this.cardsToDescard==null||
				this.chosenRegion==null)
			throw new NullPointerException("Paramters not setted");
		
		if (!(this.CheckEnoughCoins() && this.CheckHandSatisfiesBalcony()))
			return false;
		
		for (Bonus bonusToAssign : this.chosenRegion.getUncoveredPermitTiles()[numberOfPermitTile].getBonus())
			bonusToAssign.assignBonus(this.game);
		this.game.getCurrentPlayer().decrementCoins(CoinsToPay());
		for (PoliticsCard card : cardsToDescard)
			this.game.getCurrentPlayer().removeCardFromHand(card);
		this.game.getCurrentPlayer().addTile(this.chosenRegion.pickUncoveredPermitTile(this.numberOfPermitTile));
		this.chosenRegion.uncoverPermitTiles();
	    return true;
	}
	
	/**
	 * Calculate the coins you have to pay according to the cards you
	 * selected and the number of rainbow politics cards
	 * @return the amount of coins
	 */
	private int CoinsToPay() {
		int coinsToPay;
		if (this.cardsToDescard.size()==CouncilBalcony.getNumberofcouncillors())
			coinsToPay=0;
		else {
			this.chosenRegion.getRegionBalcony();
			coinsToPay=((CouncilBalcony.getNumberofcouncillors()-(this.cardsToDescard.size()))*3)+1;
		}
		for (PoliticsCard card : cardsToDescard)
			if (card.getColour().getColour() == "rainbow")
				coinsToPay++;
		return coinsToPay;
	}
	
	/**
	 * checks if the player has enough coins
	 */
	private boolean CheckEnoughCoins() {
		return this.game.getCurrentPlayer().getCoins() >= 
				CoinsToPay();
	}
	
	/**
	 * checks if the player hands cards' colour match with the colour of councillors
	 * of the selected balcony
	 */
	private boolean CheckHandSatisfiesBalcony() {
		List<Councillor> temporaryBalcony=new ArrayList<Councillor>();
		int satisfyCounter=0;
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors(); i++)
			temporaryBalcony.add(chosenRegion.getRegionBalcony().getCouncillors()[i]);
		
		for (PoliticsCard politicsCardInHand: cardsToDescard) {
			if (politicsCardInHand.getColour().getColour() == "rainbow")
				satisfyCounter++;
			for (Councillor councillorToCheck : temporaryBalcony)
				if (councillorToCheck.getColour().equals(politicsCardInHand.getColour())) {
					temporaryBalcony.remove(councillorToCheck);
					satisfyCounter++;
				}
			}
		return satisfyCounter == this.cardsToDescard.size();
	}
}