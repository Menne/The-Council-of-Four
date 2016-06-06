package server.model.actions.standardActions;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;

/**
 * It's the main action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillor extends MainAction {

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	private static final int givenCoins=4;
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	
	/**
	 * Substitutes a given councillor in one of the balconies of the game,
	 * adds the old substituted councillor in the reserve,
	 * gives to the current player 4 coins.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException{
		if (this.newCouncillor==null || this.councilBalcony==null)
			throw new NullPointerException("Parameters not setted");
		
		Councillor oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		for (Councillor councillor : game.getGameTable().getCouncilReserve().getCouncillors())
			if (councillor.getColour().getColour().equals(this.newCouncillor.getColour().getColour())) {
				game.getGameTable().getCouncilReserve().removeCouncillor(councillor);
				break;
			}
		game.getCurrentPlayer().incrementCoins(givenCoins);
		
		this.nextState(game);
		
		return true;
	}

	
	@Override
	public ActionDTO map() {
		return new ElectCouncillorDTO();
	}

}