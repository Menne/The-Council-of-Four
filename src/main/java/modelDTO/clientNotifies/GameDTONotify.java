package modelDTO.clientNotifies;

import client.view.notifies.GameUpdatedNotify;
import modelDTO.GameDTO;
import modelDTO.gameTableDTO.PlayerDTO;

public class GameDTONotify implements ClientNotify{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -2738890859980426145L;
	private GameDTO updatedGame;
	
	public GameDTONotify(GameDTO updatedGame) {
		this.updatedGame=updatedGame;
	}

	@Override
	public void act(GameDTO gameDTOtoupdate, PlayerDTO playerDTO) {
		gameDTOtoupdate.setAvailableActions(this.updatedGame.getAvailableActions());
		gameDTOtoupdate.setClientCouncillorReserve(this.updatedGame.getClientCouncillorReserve());
		gameDTOtoupdate.setClientKingBalcony(this.updatedGame.getClientKingBalcony());
		gameDTOtoupdate.setClientNobilityTrack(this.updatedGame.getClientNobilityTrack());
		gameDTOtoupdate.setClientPlayers(this.updatedGame.getClientPlayers());
		gameDTOtoupdate.setClientRegions(this.updatedGame.getClientRegions());
		gameDTOtoupdate.setCurrentPlayer(this.updatedGame.getCurrentPlayer());
		
		gameDTOtoupdate.notifyObserver(new GameUpdatedNotify(gameDTOtoupdate));
	}


	
}
