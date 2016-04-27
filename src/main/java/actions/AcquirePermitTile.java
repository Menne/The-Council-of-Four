package actions;

import java.util.List;
import java.util.ArrayList;

import gameStuff.CouncilBalcony;
import gameStuff.PoliticsCard;
import model.Game;

/**
 * This class is the main action "acquire permit tile", it operates on the 
 * protected attribute game through the method executeAction.
 * @author Emanuele
 *
 */

public class AcquirePermitTile extends MainAction {

	/**
	 * Acquire a permit tile from a designated region board by satisfying the councillors:
	 * 4 cards of the same colour of the councillors,
	 * 3 cards of the same colour of the councillors + use 4 coins,
	 * 2 cards of the same colour of the councillors + use 7 coins,
	 * 1 card of the same colour of the councillors + use 10 coins;
	 * each rainbow card requires 1 additional coin each to use.
	 * @param p
	 */
	public AcquirePermitTile(Game game){
		super(game);
	}
	
	public boolean executeAction() {
		int satisfyCounter=0;
		CouncilBalcony balconyToSatisfy = this.game.getGameTable().getRegionBoards().getRegionBalcony();
		List<PoliticsCard> cardsToDescard = this.game.getCurrentPlayer().getHand();
		for(CouncilBalcony balconyToSatisfy : balconyToSatisfy) {
		   for(List<PoliticsCard> cardsToDescard: cardsToDescard) {
		       if(balconyToSatisfy.getCouncillors().getColour() == cardsToDescard.getCards().getColour()) {
		           satisfyCounter+=1;
		           this.game.getGameTable().getPoliticsDeck().addCard(cardsToDescard.remove(indexOf()));
		       }
		    this.game.getCurrentPlayer().decrementCoins(((4-(satisfyCounter))*3)+1);

		}
		
	}
	
}