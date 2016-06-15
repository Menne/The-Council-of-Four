package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.PlayerAcceptedNotify;

public class PlayerAcceptedDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2394921097870988146L;
	private final ClientPlayerDTO playerDTOtoupdate;
	private final String message;
	
	public PlayerAcceptedDTONotify(ClientPlayerDTO clientPlayerDTO) {
		this.playerDTOtoupdate=clientPlayerDTO;
		this.message="Hi, " + clientPlayerDTO.getName() + ", you are the player number: " + clientPlayerDTO.getPlayerNumber() +
				"\nWe are waiting for more players...";
	}
	
	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.getClientPlayer().setPlayerNumber(this.playerDTOtoupdate.getPlayerNumber());
		gameDTOtoupdate.notifyObserver(new PlayerAcceptedNotify(this.message));
	}

}
