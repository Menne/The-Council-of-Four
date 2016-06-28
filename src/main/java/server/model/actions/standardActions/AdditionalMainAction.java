package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.AddictionalMainActionDTO;
import server.model.Game;
import server.model.actions.QuickAction;
import server.model.player.Player;
import server.view.notifies.ErrorNotify;
import server.view.notifies.MessageNotify;
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
	@Override
	public boolean executeAction(Game game) {
		
		if (!this.checkAssistants(game)) {
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants!. Try again or choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		
		this.notifyPlayers(game);
		game.setState(game.getState().additionalMainActionTransition());
		game.getState().updateClients(game);
		
		return true;
	}
	
	
	private void notifyPlayers(Game game) {
		game.notifyObserver(new MessageNotify("Action completed succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageNotify(game.getCurrentPlayer().getName()
				+ "vgot an additional main action", otherPlayers));
	}

	@Override
	public ActionDTO map() {
		return new AddictionalMainActionDTO();
	}

}