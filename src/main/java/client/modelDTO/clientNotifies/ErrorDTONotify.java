package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.view.notifies.ClientErrorNotify;

/**
 * This class represents an error message sent from the server to the client
 * @author cg31
 *
 */
public class ErrorDTONotify implements ClientNotify{

	private static final long serialVersionUID = -4180795696157550098L;
	private final String message;

	/**
	 * Constructor of ErrorDTONotify
	 * @param message is the error message
	 */
	public ErrorDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ClientErrorNotify(this.message));
	}

	
}
