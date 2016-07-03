package clientUpdates;

import client.clientNotifies.ChatMessageClientNotify;
import client.modelDTO.GameDTO;

/**
 * This class represents a chat message sent from the server to the client
 * @author cg31
 *
 */
public class ChatMessageUpdate implements ClientUpdate {

	/**
	 * 
	 */
	private static final long serialVersionUID = 135075862500266046L;
	private final String message;

	/**
	 * Constructor of ChatMessageDTONotify
	 * @param message is the chat message
	 */
	public ChatMessageUpdate(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ChatMessageClientNotify(this.message));
	}

}
