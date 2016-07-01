package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientChatMessageNotify;

/**
 * This class represents a chat message sent from the server to the client
 * @author cg31
 *
 */
public class ChatMessageDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 135075862500266046L;
	private final String message;

	/**
	 * Constructor of ChatMessageDTONotify
	 * @param message is the chat message
	 */
	public ChatMessageDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientChatMessageNotify(this.message));
	}

}
