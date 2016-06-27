package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientMessageNotify;

public class MessageDTONotify implements ClientNotify{

	/**
	 * 
	 */
	private static final long serialVersionUID = -51775887051624563L;
	private String message;

	public MessageDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientMessageNotify(this.message));
	}

	
}
