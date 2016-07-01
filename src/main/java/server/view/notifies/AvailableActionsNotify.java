package server.view.notifies;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.clientNotifies.AvailableActionsDTONotify;
import client.modelDTO.clientNotifies.ClientNotify;
import server.model.actions.Action;
import server.model.player.Player;

/**
 * This class contains the logic for notifying a ClientView which actions the user can do
 * @author cg31
 *
 */
public class AvailableActionsNotify implements ViewNotify {
	
	private final List<Action> availableActions;
	private final List<Player> interestedPlayers;
	private String message;
	
	/**
	 * Constructor of AvailableActionsNotify
	 * @param availableActions are the available actions in the current game state
	 * @param message is a notification the system offers to the player to explain in which phase of the game he is
	 */
	public AvailableActionsNotify(List<Action> availableActions, List<Player> interestedPlayers, String message) {
		this.availableActions=availableActions;
		this.interestedPlayers=interestedPlayers;
		this.message=message;
	}

	@Override
	public ClientNotify toClientNotify() {
		List<ActionDTO> availableActionsDTO=new ArrayList<>();
		for (Action action : this.availableActions)
			availableActionsDTO.add(action.map());
		return new AvailableActionsDTONotify(availableActionsDTO, this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
