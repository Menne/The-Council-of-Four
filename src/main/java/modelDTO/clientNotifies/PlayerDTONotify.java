package modelDTO.clientNotifies;

import client.view.notifies.PlayerUpdatedNotify;
import modelDTO.GameDTO;
import modelDTO.gameTableDTO.PlayerDTO;

public class PlayerDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2394921097870988146L;
	
	private final PlayerDTO playerDTO;
	private final String message;
	
	public PlayerDTONotify(PlayerDTO playerDTO) {
		this.playerDTO=playerDTO;
		this.message="Hi,"+ playerDTO.getName()+" you are the player number: "+playerDTO.getPlayerNumber()+
				"we are waiting for more players...";
	}
	
	@Override
	public void act(GameDTO gameDTOtoupdate, PlayerDTO playerDTO) {
		playerDTO.setAssistants(this.playerDTO.getAssistants());
		playerDTO.setAvailablePermitTiles(this.playerDTO.getAvailablePermitTiles());
		playerDTO.setCoins(this.playerDTO.getCoins());
		playerDTO.setEmporiums(this.playerDTO.getCoins());
		playerDTO.setHand(this.playerDTO.getHand());
		playerDTO.setName(this.playerDTO.getName());
		playerDTO.setNobility(this.playerDTO.getNobility());
		playerDTO.setPlayerNumber(this.playerDTO.getPlayerNumber());
		playerDTO.setScore(this.playerDTO.getScore());

		gameDTOtoupdate.notifyObserver(new PlayerUpdatedNotify(this.message));
	}

}
