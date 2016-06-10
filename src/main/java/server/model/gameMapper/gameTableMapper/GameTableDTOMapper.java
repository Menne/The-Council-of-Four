package server.model.gameMapper.gameTableMapper;

import java.util.ArrayList;
import java.util.Set;

import modelDTO.gameTableDTO.GameTableDTO;
import players.Player;
import server.model.Game;
import server.model.bonus.Bonus;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.RegionBoard;

public class GameTableDTOMapper {
	
	private CardColourDTOMapper cardColourMapper;
	private GenericPlayerDTOMapper genericPlayerMapper;
	private RegionDTOMapper regionMapper;

	public GameTableDTOMapper() {
		this.cardColourMapper=new CardColourDTOMapper();
		this.genericPlayerMapper=new GenericPlayerDTOMapper();
		this.regionMapper=new RegionDTOMapper();
	}
	
	public GameTableDTO map(Game realGame) {
		GameTableDTO gameTableDTO=new GameTableDTO();
	
		for (RegionBoard region : realGame.getGameTable().getRegionBoards())
			gameTableDTO.getClientRegions().add(this.regionMapper.map(region));
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			gameTableDTO.getClientKingBalcony()[i]=this.cardColourMapper.map
					(realGame.getGameTable().getCouncilOfKing().getCouncillors()[i].getColour());
		for (Councillor councillor : realGame.getGameTable().getCouncilReserve().getCouncillors())
			gameTableDTO.getClientCouncillorReserve().add
					(this.cardColourMapper.map(councillor.getColour()));
		for (Player player : realGame.getPlayers())
			gameTableDTO.getClientPlayers().add(this.genericPlayerMapper.map(player));
		gameTableDTO.setClientNobilityTrack((ArrayList<Set<Bonus>>) realGame.getGameTable().getNobilityTrack().getTrack());
		gameTableDTO.setCurrentPlayer(realGame.getCurrentPlayer().getName());
		gameTableDTO.setKing(realGame.getGameTable().getKing().getCity().getName());
		
		return gameTableDTO;
		
	}

}
