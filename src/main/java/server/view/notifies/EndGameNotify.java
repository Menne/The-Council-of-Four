package server.view.notifies;

import java.util.ArrayList;
import java.util.List;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.EndGameDTONotifies;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import players.Player;

public class EndGameNotify implements ViewNotify {

	private final List<Player> finalRankingTable;
	
	public EndGameNotify(List<Player> finalRankingTable) {
		this.finalRankingTable=finalRankingTable;
	}
	
	@Override
	public ClientNotify toClientNotify() {
		ArrayList<GenericPlayerDTO> finalRankingTableDTO=new ArrayList<>();
		for(Player player : finalRankingTable){
			GenericPlayerDTO playerDTO=new GenericPlayerDTO();
			playerDTO.map(player);
			finalRankingTableDTO.add(playerDTO);
		}
		return new EndGameDTONotifies(finalRankingTableDTO);
		
	}

	@Override
	public List<Player> sendTo() {
		return finalRankingTable;
	}

}
