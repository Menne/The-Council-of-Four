package clientUpdates;

import java.util.List;

import client.clientNotifies.AvailableActionsClientNotify;
import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;

/**
 * This class represents an update of player's available actions from the server to the client
 * @author cg31
 *
 */
public class AvailableActionsUpdate implements ClientUpdate {

	private static final long serialVersionUID = 7448658563042575903L;
	private final List<ActionDTO> availableActions;
	private final String message;
	
	/**
	 * Constructor of AvailableActionsDTONotify
	 * @param availableActionsDTO are the current available actions
	 * @param message is the information message sent form the server
	 */
	public AvailableActionsUpdate(List<ActionDTO> availableActionsDTO, String message) {
		this.availableActions=availableActionsDTO;
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.setAvailableActions(this.availableActions);
		gameDTOtoupdate.notifyObserver(new AvailableActionsClientNotify(gameDTOtoupdate.getAvailableActions(), this.message));
	}

}
