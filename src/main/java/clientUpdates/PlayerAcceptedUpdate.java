package clientUpdates;

import client.clientNotifies.PlayerAcceptedClientNotify;
import client.modelDTO.GameDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;

/**
 * This class represent a notification from the server that the client player has accepted successfully
 * @author cg31
 *
 */
public class PlayerAcceptedUpdate implements ClientUpdate {

	private static final long serialVersionUID = 2394921097870988146L;
	private final ClientPlayerDTO playerDTOtoupdate;
	private final String message;
	private final boolean firstPlayer;
	
	/**
	 * Constructor of PlayerAcceptedDTONotify
	 * @param clientPlayerDTO is the first status of a player
	 */
	public PlayerAcceptedUpdate(ClientPlayerDTO clientPlayerDTO) {
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
		gameDTOtoupdate.notifyObserver(new PlayerAcceptedClientNotify(this.message, firstPlayer));
	}

}
