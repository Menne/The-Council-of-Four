package server.view.notifies;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.clientNotifies.AvailableActionsDTONotify;
import modelDTO.clientNotifies.ClientNotify;
import players.Player;
import server.model.actions.Action;

public class AvailableActionsNotify implements ViewNotify {
	
	private final List<Action> availableActions;
	private final List<Player> interestedPlayers;
	private String message;
	
	public AvailableActionsNotify(List<Action> availableActions, List<Player> interestedPlayers, String message) {
		this.availableActions=availableActions;
		this.interestedPlayers=interestedPlayers;
		this.message=message;
	}

	@Override
	public ClientNotify toClientNotify() {
		List<ActionDTO> availableActionsDTO=new ArrayList<ActionDTO>();
		for (Action action : this.availableActions)
			availableActionsDTO.add(action.map());
		return new AvailableActionsDTONotify(availableActionsDTO, this.message);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
