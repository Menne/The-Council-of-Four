package server.view.notifies;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.clientNotifies.ClientNotify;
import client.modelDTO.clientNotifies.EndGameDTONotifies;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import server.model.Game;
import server.model.player.Player;

public class EndGameNotify implements ViewNotify {

	private final List<Player> finalRankingTable;
	private Game game;
	
	public EndGameNotify(Game game, List<Player> finalRankingTable) {
		this.game=game;
		this.finalRankingTable=finalRankingTable;
	}
	
	@Override
	public ClientNotify toClientNotify() {
		ArrayList<GenericPlayerDTO> finalRankingTableDTO=new ArrayList<>();
		for (Player player : this.finalRankingTable)
			finalRankingTableDTO.add(this.game.getGameMapper().genericPlayerMap(player));
		return new EndGameDTONotifies(finalRankingTableDTO);
		
	}

	@Override
	public List<Player> sendTo() {
		return finalRankingTable;
	}

}
