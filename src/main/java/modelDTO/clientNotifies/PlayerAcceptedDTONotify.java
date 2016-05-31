package modelDTO.clientNotifies;

import client.view.notifies.PlayerAcceptedNotify;
import modelDTO.GameDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;

public class PlayerAcceptedDTONotify implements ClientNotify {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2394921097870988146L;
	private final GenericPlayerDTO playerDTO;
	private final String message;
	
	public PlayerAcceptedDTONotify(GenericPlayerDTO playerDTO) {
		this.playerDTO=playerDTO;
		this.message="Hi,"+ playerDTO.getName()+" you are the player number: "+playerDTO.getPlayerNumber()+
				"\nWe are waiting for more players...";
	}
	
	@Override
	public void act(GameDTO gameDTOtoupdate) {
		playerDTO.setPlayerNumber(this.playerDTO.getPlayerNumber());
		gameDTOtoupdate.notifyObserver(new PlayerAcceptedNotify(this.message));
	}

}
