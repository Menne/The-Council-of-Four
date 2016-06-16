package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.bonus.Bonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;
import server.view.notifies.ErrorNotify;

/**
 * This class is the main action "acquire permit tile", it operates on the 
 * protected attribute game through the method executeAction.
 * @author Emanuele
 *
 */

public class AcquirePermitTile extends MainAction {


	private Integer numberOfPermitTile;
	private RegionBoard chosenRegion;
	private List<PoliticsCard> cardsToDescard;
	
	
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
	@Override
	public boolean executeAction(Game game) {
		if (this.numberOfPermitTile==null||
				this.cardsToDescard==null||
				this.chosenRegion==null)
			throw new NullPointerException("Paramters not setted");
		
		if (!this.CheckEnoughCoins(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough coins!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		if (!this.CheckHandSatisfiesBalcony()) {
					game.notifyObserver(new ErrorNotify("It seems that the cards in you hand don't satisfy the councillors!. Try again or choose another action", 
							Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
					
		for (Bonus bonusToAssign : this.chosenRegion.getUncoveredPermitTiles()[numberOfPermitTile].getBonuses())
			bonusToAssign.assignBonus(game);
		game.getCurrentPlayer().decrementCoins(CoinsToPay());
		
		for (PoliticsCard card : cardsToDescard) {
			if (!game.getCurrentPlayer().getHand().contains(card))
				throw new IllegalArgumentException("Current player hasn't these cards");
			game.getCurrentPlayer().removeCardFromHand(card);
		}
		
		game.getCurrentPlayer().addTile(this.chosenRegion.pickUncoveredPermitTile(this.numberOfPermitTile));
		this.chosenRegion.uncoverPermitTiles();
		
		this.nextState(game);
		
		return true;
	}
	
	/**
	 * Calculate the coins you have to pay according to the cards you
	 * selected and the number of rainbow politics cards
	 * @return the amount of coins
	 * @throws IndexOutOfBoundsException if the list of cards to discard is empty
	 */
	private int CoinsToPay() {
		if (this.cardsToDescard.isEmpty())
			throw new IndexOutOfBoundsException("you have selected 0 cards to buy a permit tile");
		
		int coinsToPay;
		
		if (this.cardsToDescard.size()==CouncilBalcony.getNumberofcouncillors())
			coinsToPay=0;
		else {
			coinsToPay=((CouncilBalcony.getNumberofcouncillors()-(this.cardsToDescard.size()))*3)+1;
		}
		for (PoliticsCard card : cardsToDescard)
			if ("Rainbow".equals(card.getColour().getColour()))
				coinsToPay++;
		return coinsToPay;
	}
	
	/**
	 * checks if the player has enough coins
	 */
	private boolean CheckEnoughCoins(Game game) {
		return game.getCurrentPlayer().getCoins() >= CoinsToPay();
	}
	
	/**
	 * checks if the player hands cards' colour match with the colour of councillors
	 * of the selected balcony
	 */
	private boolean CheckHandSatisfiesBalcony() {
		List<Councillor> temporaryBalcony=new ArrayList<>();
		int satisfyCounter=0;
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors()-1; i++)
			temporaryBalcony.add(chosenRegion.getRegionBalcony().getCouncillors()[i]);		
		
		for (PoliticsCard politicsCardInHand: this.cardsToDescard) {
			if (politicsCardInHand.getColour().getColour().equals("Rainbow"))
				satisfyCounter++;		
			for (Councillor councillorToSatisfy : temporaryBalcony)
				if (councillorToSatisfy.getColour().getColour().equals(politicsCardInHand.getColour().getColour())) {
					temporaryBalcony.remove(councillorToSatisfy);
					satisfyCounter++;
					break;
				} 
		}
		return satisfyCounter==this.cardsToDescard.size();
	}

	
	@Override
	public ActionDTO map() {
		return new AcquirePermitTileDTO();
	}
	
}