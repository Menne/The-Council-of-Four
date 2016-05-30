package server.view.notifies;

import java.util.ArrayList;
import java.util.List;

import modelDTO.actionsDTO.ActionDTO;
import modelDTO.clientNotifies.AvailableActionsDTONotify;
import modelDTO.clientNotifies.ClientNotify;
import players.Player;
import server.model.Game;
import server.model.actions.Action;

public class AvailableActionsNotify implements ViewNotify {
	
	private final Game game;
	private final List<Player> interestedPlayers;
	
	public AvailableActionsNotify(Game game, List<Player> interestedPlayers) {
		this.game=game;
		this.interestedPlayers=interestedPlayers;
	}

	@Override
	public ClientNotify toClientNotify() {
		List<ActionDTO> availableActionsDTO=new ArrayList<ActionDTO>();
		for (Action action : this.game.getState().getAcceptableActions(this.game))
			availableActionsDTO.add(action.map());
		System.out.println("server: ho aggiornato le azioni  "+availableActionsDTO);
		return new AvailableActionsDTONotify(availableActionsDTO);
	}

	@Override
	public List<Player> sendTo() {
		return this.interestedPlayers;
	}

}
