package client.view.notifies;

import java.util.List;
import client.view.ClientView;
import modelDTO.actionsDTO.ActionDTO;

public class ClientAvailableActionsNotify implements ClientViewNotify {

	private List<ActionDTO> availableActions;
	private String message;

	public ClientAvailableActionsNotify(List<ActionDTO> availableActions, String message) {
		this.availableActions=availableActions;
		this.message=message;
	}

	@Override
	public void updateView(ClientView view) {
		view.displayMessage(this.message);
		view.displayAvailableActions(this.availableActions);
	}

}
