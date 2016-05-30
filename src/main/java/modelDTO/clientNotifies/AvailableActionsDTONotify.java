package modelDTO.clientNotifies;

import java.util.List;

import client.view.notifies.ClientAvailableActionsNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;

public class AvailableActionsDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7448658563042575903L;
	private List<ActionDTO> availableActions;

	public AvailableActionsDTONotify(List<ActionDTO> availableActionsDTO) {
		this.availableActions=availableActionsDTO;
	}

	@Override
	public void act(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.setAvailableActions(this.availableActions);
		gameDTOtoupdate.notifyObserver(new ClientAvailableActionsNotify(gameDTOtoupdate.getAvailableActions()));
	}

}
