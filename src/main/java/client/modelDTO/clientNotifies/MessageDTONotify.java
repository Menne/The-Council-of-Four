package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientMessageNotify;

/**
 * This class represents a system message sent from the server to the client
 * @author cg31
 *
 */
public class MessageDTONotify implements ClientNotify{

	private static final long serialVersionUID = -51775887051624563L;
	private String message;

	/**
	 * Constructor of MessageDTONotify
	 * @param message is the system message
	 */
	public MessageDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientMessageNotify(this.message));
	}

	
}
