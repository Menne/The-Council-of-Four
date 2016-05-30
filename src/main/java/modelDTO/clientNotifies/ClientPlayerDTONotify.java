package modelDTO.clientNotifies;

import client.view.notifies.ClientPlayerNotify;
import modelDTO.GameDTO;
import modelDTO.playerDTO.ClientPlayerDTO;

public class ClientPlayerDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8227302198361328107L;
	private ClientPlayerDTO updatedPlayer;

	public ClientPlayerDTONotify(ClientPlayerDTO clientPlayerDTO) {
		this.updatedPlayer=clientPlayerDTO;
	}

	@Override
	public void act(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.setClientPlayer(this.updatedPlayer);
		gameDTOtoupdate.notifyObserver(new ClientPlayerNotify(gameDTOtoupdate.getClientPlayer()));
	}

}
