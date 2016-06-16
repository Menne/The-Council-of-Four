package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientChatMessageNotify;

public class ChatMessageDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 135075862500266046L;
	private final String message;

	public ChatMessageDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientChatMessageNotify(this.message));
	}

}
