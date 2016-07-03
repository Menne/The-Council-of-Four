package server.serverNotifies;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import clientUpdates.ClientUpdate;
import clientUpdates.EndGameUpdate;
import server.model.Game;
import server.model.player.Player;

/**
 * This class provides the logic to notify the server view that the game has just finished
 * @author cg31
 *
 */
public class EndGameServerNotify implements ServerViewNotify {

	private final List<Player> finalRankingTable;
	private Game game;
	
	/**
	 * constructor of EndGameNotify
	 * @param game is the updated game status
	 * @param finalRankingTable is the final status of the players of the game
	 */
	public EndGameServerNotify(Game game, List<Player> finalRankingTable) {
		this.game=game;
		this.finalRankingTable=finalRankingTable;
	}
	
	@Override
	public ClientUpdate toClientNotify() {
		ArrayList<GenericPlayerDTO> finalRankingTableDTO=new ArrayList<>();
		for (Player player : this.finalRankingTable)
			finalRankingTableDTO.add(this.game.getGameMapper().genericPlayerMap(player));
		return new EndGameUpdate();
		
	}

	@Override
	public List<Player> sendTo() {
		return finalRankingTable;
	}

}
