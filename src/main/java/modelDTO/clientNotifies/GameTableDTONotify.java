package modelDTO.clientNotifies;

import client.view.notifies.ClientGameTableNotify;
import modelDTO.GameDTO;
import modelDTO.gameTableDTO.GameTableDTO;

public class GameTableDTONotify implements ClientNotify{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -912988632170214482L;
	private GameTableDTO updatedGame;
	
	public GameTableDTONotify(GameTableDTO gameTableDTO) {
		this.updatedGame=gameTableDTO;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.getClientGameTable().setClientCouncillorReserve(this.updatedGame.getClientCouncillorReserve());
		gameDTOtoupdate.getClientGameTable().setClientKingBalcony(this.updatedGame.getClientKingBalcony());
		gameDTOtoupdate.getClientGameTable().setClientNobilityTrack(this.updatedGame.getClientNobilityTrack());
		gameDTOtoupdate.getClientGameTable().setClientPlayers(this.updatedGame.getClientPlayers());
		gameDTOtoupdate.getClientGameTable().setClientRegions(this.updatedGame.getClientRegions());
		gameDTOtoupdate.getClientGameTable().setCurrentPlayer(this.updatedGame.getCurrentPlayer());

		gameDTOtoupdate.notifyObserver(new ClientGameTableNotify(gameDTOtoupdate.getClientGameTable()));
	}


	
}
