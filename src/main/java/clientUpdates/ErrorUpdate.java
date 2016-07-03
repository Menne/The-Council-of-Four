package clientUpdates;

import client.clientNotifies.ErrorClientNotify;
import client.modelDTO.GameDTO;

/**
 * This class represents an error message sent from the server to the client
 * @author cg31
 *
 */
public class ErrorUpdate implements ClientUpdate{

	private static final long serialVersionUID = -4180795696157550098L;
	private final String message;

	/**
	 * Constructor of ErrorDTONotify
	 * @param message is the error message
	 */
	public ErrorUpdate(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.notifyObserver(new ErrorClientNotify(this.message));
	}

	
}
