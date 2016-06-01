package client.view.notifies;

import java.util.List;

import client.view.socket.CLI;
import modelDTO.actionsDTO.ActionDTO;

public class ClientAvailableActionsNotify implements ClientViewNotify {

	private List<ActionDTO> availableActions;

	public ClientAvailableActionsNotify(List<ActionDTO> availableActions) {
		this.availableActions=availableActions;
	}

	@Override
	public void stamp(CLI view) {
		System.out.println("\nYou have the following available actions. Choose one of them:\n"+ this.availableActions);
	}

}
