package server.model.actions.standardAction;

import client.actionDTO.NeedParameters;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import client.actionDTO.ActionDTO;
import client.actionDTO.ElectCouncillorDTO;
import view.GameNotify;

/**
 * It's the main action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillor extends MainAction implements NeedParameters{

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	private static final int givenCoins=4;
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	private boolean checkCouncillor(Game game) {
		return game.getGameTable().getCouncilReserve()
				.getCouncillors().contains(this.newCouncillor);
	}
	
	/**
	 * Substitutes a given councillor in one of the balconies of the game,
	 * adds the old substituted councillor in the reserve,
	 * gives to the current player 4 coins.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction(Game game) throws NullPointerException{
		if(this.newCouncillor==null || this.councilBalcony==null)
			throw new NullPointerException("Parameters not setted");
		Councillor oldCouncillor;
		if(!this.checkCouncillor(game)){
			this.sendErrorNotify(game);
			return false;
		}
		oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		game.getCurrentPlayer().incrementCoins(givenCoins);
		
		this.nextState(game);
		game.notifyObserver(new GameNotify(game));
		return true;
	}

	@Override
	public String toString() {
		return "m1: elect a councillor";
	}

	@Override
	public ActionDTO map() {
		return new ElectCouncillorDTO();
	}


}