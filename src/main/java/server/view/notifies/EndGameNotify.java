package server.view.notifies;

import java.util.ArrayList;
import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.EndGameDTONotifies;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import players.Player;
import server.model.Game;

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
