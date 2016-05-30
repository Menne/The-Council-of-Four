package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelDTO.actionsDTO.AcquirePermitTileDTO;
import modelDTO.actionsDTO.ActionDTO;
import players.Player;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.bonus.Bonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

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
	public boolean executeAction(Game game) throws NullPointerException{
		if(this.numberOfPermitTile==null||
				this.cardsToDescard==null||
				this.chosenRegion==null)
			throw new NullPointerException("Paramters not setted");
		
		if (!(this.CheckEnoughCoins(game) && this.CheckHandSatisfiesBalcony(game))){
			this.sendErrorNotify(game, new ArrayList<Player>(game.getPlayers()));
			return false;
		}
					
		for (Bonus bonusToAssign : this.chosenRegion.getUncoveredPermitTiles()[numberOfPermitTile].getBonus())
			bonusToAssign.assignBonus(game);
		game.getCurrentPlayer().decrementCoins(CoinsToPay());
		for (PoliticsCard card : cardsToDescard)
			game.getCurrentPlayer().removeCardFromHand(card);
		game.getCurrentPlayer().addTile(this.chosenRegion.pickUncoveredPermitTile(this.numberOfPermitTile));
		this.chosenRegion.uncoverPermitTiles();
		
		this.nextState(game);
		
		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new AvailableActionsNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
		game.notifyObserver(new PlayerNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
	    
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
	private boolean CheckEnoughCoins(Game game) {
		return game.getCurrentPlayer().getCoins() >= 
				CoinsToPay();
	}
	
	/**
	 * checks if the player hands cards' colour match with the colour of councillors
	 * of the selected balcony
	 */
	private boolean CheckHandSatisfiesBalcony(Game game) {
		List<Councillor> temporaryBalcony=new ArrayList<Councillor>();
		int satisfyCounter=0;
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors()-1; i++)
			temporaryBalcony.add(game.getGameTable().getCouncilOfKing().getCouncillors()[i]);
		
		for (PoliticsCard politicsCardInHand: cardsToDescard) {
			if (politicsCardInHand.getColour().getColour() == "rainbow")
				satisfyCounter++;
			for (int j=0; j<=temporaryBalcony.size()-1;) {
				if (temporaryBalcony.get(j).getColour().equals(politicsCardInHand.getColour())) {
					temporaryBalcony.remove(temporaryBalcony.get(j));
					satisfyCounter++;
				}
				else
					j++;
			}
		}
		return satisfyCounter == this.cardsToDescard.size();
	}

	@Override
	public String toString() {
		return "m2: acquire a permit tile";
	}



	@Override
	public ActionDTO map() {
		return new AcquirePermitTileDTO();
	}
	
}