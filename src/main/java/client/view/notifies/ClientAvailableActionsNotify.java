package client.view.notifies;

import java.util.List;
import java.util.Scanner;

import modelDTO.actionsDTO.ActionDTO;

public class ClientAvailableActionsNotify implements ClientViewNotify {

	private List<ActionDTO> availableActions;

	public ClientAvailableActionsNotify(List<ActionDTO> availableActions) {
		this.availableActions=availableActions;
	}

	@Override
	public void stamp(Scanner scanner) {
		System.out.println("\nYou have the following available actions. Choose one of them:\n"+ this.availableActions);
	}

}
