package client.ModelDTO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.actionDTO.ActionDTO;
import model.Game;
import model.gameTable.CardColour;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.RegionBoard;
import players.Player;

public class GameDTO implements ModelDTO<Game>{
	
	private List<RegionDTO> clientRegions;
	private CardColourDTO[] clientKingBalcony;
	private List<CardColourDTO> clientCouncillorReserve;
//	private List<Set<String>> clientNobilityTrack;
	private List<PlayerDTO> clientPlayers;
	private PlayerDTO currentPlayer;
	private List<ActionDTO> availableActions;
	
	public GameDTO(){
		this.clientRegions=new ArrayList<RegionDTO>();
		this.clientKingBalcony=new CardColourDTO[4];
		this.clientCouncillorReserve=new ArrayList<CardColourDTO>();
//		this.clientNobilityTrack=new ArrayList<Set<String>>();
		this.clientPlayers=new ArrayList<PlayerDTO>();
	}

	@Override
	public void map(Game realObject) {
		for(RegionBoard region : realObject.getGameTable().getRegionBoards()){
			RegionDTO regionDTO=new RegionDTO();
			regionDTO.map(region);
			this.clientRegions.add(regionDTO);
		}
		
		for(int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++){
			CardColourDTO cardColourDTO=new CardColourDTO();
			cardColourDTO.map(realObject.getGameTable().getCouncilOfKing().getCouncillors()[i].getColour());
			this.clientKingBalcony[i]=cardColourDTO;
		}
		
		for(Councillor councillor : realObject.getGameTable().getCouncilReserve().getCouncillors()){
			CardColourDTO cardColourDTO=new CardColourDTO();
			cardColourDTO.map(councillor.getColour());
			this.clientCouncillorReserve.add(cardColourDTO);
		}
		
		for(Player player : realObject.getPlayers()){
			PlayerDTO playerDTO=new PlayerDTO();
			playerDTO.map(player);
			this.clientPlayers.add(playerDTO);
		}
	}

	
	@Override
	public String toString() {
		return "GameDTO [clientRegions=" + clientRegions + ", clientKingBalcony="
				+ Arrays.toString(clientKingBalcony) + ", clientCouncillorReserve=" + clientCouncillorReserve
				+ ", clientPlayers=" + clientPlayers + ", currentPlayer=" + currentPlayer + ", availableActions="
				+ availableActions + "]";
	}

	
	
	
}
