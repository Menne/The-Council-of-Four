package clientUpdates;

import client.clientNotifies.MessageClientNotify;
import client.modelDTO.GameDTO;

/**
 * This class represents a system message sent from the server to the client
 * @author cg31
 *
 */
public class MessageUpdate implements ClientUpdate{

	private static final long serialVersionUID = -51775887051624563L;
	private String message;

	/**
	 * Constructor of MessageDTONotify
	 * @param message is the system message
	 */
	public MessageUpdate(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new MessageClientNotify(this.message));
	}

	
}
