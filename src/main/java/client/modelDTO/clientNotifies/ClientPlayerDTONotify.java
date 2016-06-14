package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.ClientPlayerNotify;

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
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.setClientPlayer(this.updatedPlayer);
		gameDTOtoupdate.notifyObserver(new ClientPlayerNotify(gameDTOtoupdate.getClientPlayer()));
	}

}
