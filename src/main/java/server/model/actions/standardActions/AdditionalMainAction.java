package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.AddictionalMainActionDTO;
import players.Player;
import server.model.Game;
import server.model.actions.QuickAction;
import server.view.notifies.ErrorNotify;
import server.view.notifies.GameNotify;

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
	@Override
	public boolean executeAction(Game game) {
		List<Player> interestedPlayers=new ArrayList<>();
		interestedPlayers.add(game.getCurrentPlayer());
		
		if(!this.checkAssistants(game)){
			game.notifyObserver(new ErrorNotify("You can't do this action",interestedPlayers));
			return false;
		}	
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		game.setState(game.getState().additionalMainActionTransition());
		game.notifyObserver(new GameNotify(game,interestedPlayers));
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