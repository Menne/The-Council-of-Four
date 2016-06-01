package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.EngageAssistantDTO;
import players.Player;
import server.model.Game;
import server.model.actions.QuickAction;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.ErrorNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;
/**
 * It's the quick action "engage assistants" it operates on the 
 * protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class EngageAssistant extends QuickAction {

	private static final int necessaryCoins=3;

	
	private boolean checkCoins(Game game){
		return game.getCurrentPlayer().getCoins()>=necessaryCoins;
	}

	/**
	 * Decrements 3 coins to the current player and gives him an assistant.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) {
		
		if (!this.checkCoins(game)){
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough coins!", Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementCoins(necessaryCoins);
		game.getCurrentPlayer().incrementAssistants(1);
		
		this.nextState(game);

		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new PlayerNotify(game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new AvailableActionsNotify(game.getState().getAcceptableActions(game), 
				Arrays.asList(game.getCurrentPlayer())));
		
		return true;
	}
	
	
	@Override
	public ActionDTO map() {
		return new EngageAssistantDTO();
	}
	
}