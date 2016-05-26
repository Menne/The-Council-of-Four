package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.EngageAssistantDTO;
import players.Player;
import server.model.Game;
import server.model.actions.QuickAction;
import server.view.notifies.GameNotify;
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
	public boolean executeAction(Game game) {
		List<Player> interestedPlayers=new ArrayList<Player>();
		interestedPlayers.add(game.getCurrentPlayer());
		
		if(!this.checkCoins(game)){
			this.sendErrorNotify(game,interestedPlayers);
			return false;
		}
		game.getCurrentPlayer().decrementCoins(necessaryCoins);
		game.getCurrentPlayer().incrementAssistants(1);
		
		this.nextState(game);
		game.notifyObserver(new GameNotify(game, interestedPlayers));
		return true;
	}
	
	@Override
	public String toString() {
		return "q1: engage an assistant";
	}

	@Override
	public ActionDTO map() {
		return new EngageAssistantDTO();
	}
}