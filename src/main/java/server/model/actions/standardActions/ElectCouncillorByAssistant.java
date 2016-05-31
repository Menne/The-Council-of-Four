package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ElectCouncillorByAssistantDTO;
import players.Player;
import server.model.Game;
import server.model.actions.QuickAction;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.ErrorNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

/**
 * It's the quick action "elect councillor" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the councillor to add,
 * and the balcony where he wants to add it. 
 * @author Luca
 *
 */
public class ElectCouncillorByAssistant extends QuickAction {

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	public static final int necessaryAssistants=1;
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	private boolean checkAssistants(Game game){
		return game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants;
	}

	/**
	  * Substitutes a given councillor in one of the balconies of the game,
	  * adds the old substituted councillor in the reserve,
	  * decrement an assistant to the current player.
	  * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException{
		if(this.councilBalcony==null || this.newCouncillor==null)
			throw new NullPointerException("Parameters not setted");

		if (!this.checkAssistants(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants!",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		Councillor oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		this.nextState(game);

		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		
		return true;
	}

	@Override
	public String toString() {
		return "q3: elect a councillor by sending an assistant";
	}

	@Override
	public ActionDTO map() {
		return new ElectCouncillorByAssistantDTO();
	}

}