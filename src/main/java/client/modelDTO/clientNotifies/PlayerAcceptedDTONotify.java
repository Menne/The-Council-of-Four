package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import client.view.notifies.PlayerAcceptedNotify;

/**
 * This class represent a notification from the server that the client player has accepted successfully
 * @author cg31
 *
 */
public class PlayerAcceptedDTONotify implements ClientNotify {

	private static final long serialVersionUID = 2394921097870988146L;
	private final ClientPlayerDTO playerDTOtoupdate;
	private final String message;
	private final boolean firstPlayer;
	
	/**
	 * Constructor of PlayerAcceptedDTONotify
	 * @param clientPlayerDTO is the first status of a player
	 */
	public PlayerAcceptedDTONotify(ClientPlayerDTO clientPlayerDTO) {
		this.playerDTOtoupdate=clientPlayerDTO;
		if(clientPlayerDTO.getPlayerNumber()==1)
			firstPlayer=true;
		else
			firstPlayer=false;
		this.message="Hi, " + clientPlayerDTO.getName() + ", you are the player number: " + clientPlayerDTO.getPlayerNumber() +
				"\nWe are waiting for more players...";
	}
	
	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.getClientPlayer().setPlayerNumber(this.playerDTOtoupdate.getPlayerNumber());
		gameDTOtoupdate.notifyObserver(new PlayerAcceptedNotify(this.message, firstPlayer));
	}

}
