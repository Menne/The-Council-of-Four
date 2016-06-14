package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientErrorNotify;

public class ErrorDTONotify implements ClientNotify{

	/**
	 * 
	 */
	private static final long serialVersionUID = -4180795696157550098L;
	private String message;

	public ErrorDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientErrorNotify(this.message));
	}

	
}
