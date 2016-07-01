package client.modelDTO.clientNotifies;

import client.modelDTO.GameDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.view.notifies.ClientGameTableNotify;

/**
 * This class represents an update of client game status from the server to the client
 * @author cg31
 *
 */
public class GameTableDTONotify implements ClientNotify{
	
	private static final long serialVersionUID = -912988632170214482L;
	private final GameTableDTO updatedGame;
	private final boolean startGame;
	
	/**
	 * Constructor of GameTableDTONotify
	 * @param gameTableDTO is the updated status of the client game
	 * @param startGame is a flag that indicates if the game has just started
	 */
	public GameTableDTONotify(GameTableDTO gameTableDTO, boolean startGame) {
		this.updatedGame=gameTableDTO;
		this.startGame=startGame;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		gameDTOtoupdate.getClientGameTable().setClientCouncillorReserve(this.updatedGame.getClientCouncillorReserve());
		gameDTOtoupdate.getClientGameTable().setClientKingBalcony(this.updatedGame.getClientKingBalcony());
		gameDTOtoupdate.getClientGameTable().setClientNobilityTrack(this.updatedGame.getClientNobilityTrack());
		gameDTOtoupdate.getClientGameTable().setClientPlayers(this.updatedGame.getClientPlayers());
		gameDTOtoupdate.getClientGameTable().setClientRegions(this.updatedGame.getClientRegions());
		gameDTOtoupdate.getClientGameTable().setCurrentPlayer(this.updatedGame.getCurrentPlayer());
		gameDTOtoupdate.getClientGameTable().setKing(this.updatedGame.getKing());
		gameDTOtoupdate.getClientGameTable().setNextKingRewardTile(this.updatedGame.getNextKingRewardTile());
		gameDTOtoupdate.getClientGameTable().setColourBonuses(this.updatedGame.getColourBonuses());
		gameDTOtoupdate.getClientGameTable().setMapNumber(this.updatedGame.getMapNumber());

		gameDTOtoupdate.notifyObserver(new ClientGameTableNotify(this.startGame));
	}


	
}
