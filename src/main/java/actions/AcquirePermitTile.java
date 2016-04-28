package actions;

import java.util.List;
import java.util.ArrayList;

import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.PermitTile;
import gameStuff.PoliticsCard;
import model.Game;

/**
 * This class is the main action "acquire permit tile", it operates on the 
 * protected attribute game through the method executeAction.
 * @author Emanuele
 *
 */

public class AcquirePermitTile extends MainAction {

	private PermitTile permitTileToPick;
	private List<PoliticsCard> cardsToDescard;
	private CouncilBalcony balconyToSatisfy;

	/**
	 * constructor of the action
	 * @param game is the private attribute inherited from the superclass
	 * @param permitTileToPick is the permit tile you want to pick
	 * @param cardsToDescard are the cards in the hand you use to satisfy the councillors
	 * @param balconyToSatisfy is the council balcony to satisfy
	 *
	 */
	public AcquirePermitTile(Game game, PermitTile permitTileToPick, 
			List<PoliticsCard> cardsToDescard, CouncilBalcony balconyToSatisfy) {
		super(game);
		this.permitTileToPick=permitTileToPick;
		this.cardsToDescard=cardsToDescard;
		this.balconyToSatisfy=balconyToSatisfy;
	}
	
	/**
	 * Acquire a permit tile from a designated region board by satisfying the councillors:
	 * 4 cards of the same colour of the councillors,
	 * 3 cards of the same colour of the councillors + use 4 coins,
	 * 2 cards of the same colour of the councillors + use 7 coins,
	 * 1 card of the same colour of the councillors + use 10 coins;
	 * each rainbow card requires 1 additional coin each to use.
	 */
	public boolean executeAction() {

		if (!(this.CheckHandCorrected() || this.CheckEnoughCoins() || this.CheckHandSatisfiesBalcony()))
			return false;
		
		this.game.getCurrentPlayer().addTile(permitTileToPick);
		for (PoliticsCard card : cardsToDescard)
			this.game.getCurrentPlayer().removeCardFromHand(card);
		this.game.getCurrentPlayer().decrementCoins(((4-(cardsToDescard.size()))*3)+1);
	    return true;
	}
	
	/*
	 * checks if the player has selected correct cards
	 */
	public boolean CheckHandCorrected() {
		return this.game.getCurrentPlayer().getHand()
				.contains(cardsToDescard);
	}
	
	/*
	 * checks if the player has enough coins
	 */
	public boolean CheckEnoughCoins() {
		return this.game.getCurrentPlayer().getCoins() >= 
				cardsToDescard.size();
	}
	
	/*
	 * checks if the player hands cards' colour match with the colour of councillors
	 * of the selected balcony
	 */
	public boolean CheckHandSatisfiesBalcony() {
		for (int i=0; i<=balconyToSatisfy.getNumberofcouncillors(); i++)
			for (PoliticsCard PoliticsCardsInHand: cardsToDescard)
				if (!(balconyToSatisfy.getCouncillors()[i].getColour() == PoliticsCardsInHand.getColour()))
					return false;
		return true;
	}
	
}