package actions;

import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import model.Game;

/**
 * It's the quick action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillorByAssistant extends QuickAction {

	private final Councillor newCouncillor;
	private final CouncilBalcony councilBalcony;
	
	public ElectCouncillorByAssistant(Game game, Councillor newCouncillor, CouncilBalcony councilBalcony){
		super(game);
		this.newCouncillor=newCouncillor;
		this.councilBalcony=councilBalcony;
	}

	/**
	  * Substitutes a given councillor in one of the balconies of the game,
	  * adds the old substituted councillor in the reserve,
	  * decrement an assistant to the current player.
	  * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() {
		Councillor oldCouncillor;
		if((!this.game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(newCouncillor))||
				(!this.game.getCurrentPlayer().decrementAssistants(1)))
			return false;
		oldCouncillor=this.councilBalcony.substituteCouncillor(newCouncillor);
		this.game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		this.game.getCurrentPlayer().decrementAssistants(1);
		return true;
	}

}