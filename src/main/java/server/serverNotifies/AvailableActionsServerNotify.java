package server.serverNotifies;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import clientUpdates.AvailableActionsUpdate;
import clientUpdates.ClientUpdate;
import server.model.actions.Action;
import server.model.player.Player;

/**
 * This class contains the logic for notifying a ClientView which actions the user can do
 * @author cg31
 *
 */
public class AvailableActionsServerNotify implements ServerViewNotify {
	
	private final List<Action> availableActions;
	private final List<Player> interestedPlayers;
	private String message;
	
	/**
	 * Constructor of AvailableActionsNotify
	 * @param availableActions are the available actions in the current game state
	 * @param message is a notification the system offers to the player to explain in which phase of the game he is
	 */
	public AvailableActionsServerNotify(List<Action> availableActions, List<Player> interestedPlayers, String message) {
		this.availableActions=availableActions;
		this.interestedPlayers=interestedPlayers;
		this.message=message;
	}

	@Override
	public ClientUpdate toClientNotify() {
		List<ActionDTO> availableActionsDTO=new ArrayList<>();
		for (Action action : this.availableActions)
			availableActionsDTO.add(action.map());
		return new AvailableActionsUpdate(availableActionsDTO, this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
