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
	public static final int necessaryAssistants=1;
	
	public ElectCouncillorByAssistant(Game game, Councillor newCouncillor, CouncilBalcony councilBalcony){
		super(game);
		this.newCouncillor=newCouncillor;
		this.councilBalcony=councilBalcony;
	}
	
	private boolean checkAssistants(){
		return this.game.getCurrentPlayer().getAssistants()>=necessaryAssistants;
	}
	
	private boolean checkCouncillor(){
		return this.game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(this.newCouncillor);
	}

	/**
	  * Substitutes a given councillor in one of the balconies of the game,
	  * adds the old substituted councillor in the reserve,
	  * decrement an assistant to the current player.
	  * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() {
		Councillor oldCouncillor;
		if((!this.checkAssistants())||(!this.checkCouncillor()))
			return false;
		oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		this.game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		this.game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		return true;
	}

}