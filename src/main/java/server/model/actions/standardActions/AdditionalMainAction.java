package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddictionalMainActionDTO;
import players.Player;
import server.model.Game;
import server.model.actions.QuickAction;
import server.view.notifies.AvailableActionsNotify;
import server.view.notifies.ErrorNotify;
import server.view.notifies.GameTableNotify;
import server.view.notifies.PlayerNotify;

/**
 * It's the quick action "additional main action" it operates
 * on the protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class AdditionalMainAction extends QuickAction {

	private static final int necessaryAssistants=3;
	
	private boolean checkAssistants(Game game){
		if(game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants)
			return true;
		else
			return false;
	}
	
	/**
	 * Decrements the necessaryAssistants to the current player and give him
	 * an additional main action
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction(Game game) {
		List<Player> interestedPlayers=new ArrayList<Player>();
		interestedPlayers.add(game.getCurrentPlayer());
		
		if(!this.checkAssistants(game)){
			game.notifyObserver(new ErrorNotify("You can't do this action",interestedPlayers));
			return false;
		}	
		
		game.setState(game.getState().additionalMainActionTransition());
		
		game.notifyObserver(new GameTableNotify(game, new ArrayList<Player>(game.getPlayers())));
		game.notifyObserver(new AvailableActionsNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
		game.notifyObserver(new PlayerNotify(game, new ArrayList<Player>(Arrays.asList(game.getCurrentPlayer()))));
		
		return true;
	}
	
	@Override
	public String toString() {
		return "q4: get an additionalo main action";
	}

	@Override
	public ActionDTO map() {
		return new AddictionalMainActionDTO();
	}


}