package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientPlayerNotify;

/**
 * This class represents an update of player's status from the server to the client
 * @author cg31
 *
 */
public class ClientPlayerDTONotify implements ClientNotify {

	private static final long serialVersionUID = 8227302198361328107L;
	private final ClientPlayerDTO updatedPlayer;

	/**
	 * Constructor of ClientPlayerDTONotify
	 * @param clientPlayerDTO is the updated player status
	 */
	public ClientPlayerDTONotify(ClientPlayerDTO clientPlayerDTO) {
		this.updatedPlayer=clientPlayerDTO;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.setClientPlayer(this.updatedPlayer);
		gameDTOtoupdate.notifyObserver(new ClientPlayerNotify());
	}

}
