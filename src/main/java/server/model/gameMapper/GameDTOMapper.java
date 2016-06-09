package server.model.gameMapper;

import server.model.gameMapper.gameTableMapper.GameTableDTOMapper;
import server.model.gameMapper.marketMapper.MarketDTOMapper;
import server.model.gameMapper.playerMapper.ClientPlayerDTOMapper;

public class GameDTOMapper {

	private GameTableDTOMapper GameTableDTOMapper;
	private ClientPlayerDTOMapper PlayerDTOMapper;
	private MarketDTOMapper MarketDTOMapper;

	public GameDTOMapper() {
		this.GameTableDTOMapper=new GameTableDTOMapper();
		this.PlayerDTOMapper=new ClientPlayerDTOMapper();
		this.MarketDTOMapper=new MarketDTOMapper();
	}

	public MarketDTOMapper getMarketDTOMapper() {
		return this.MarketDTOMapper;
	}

	public GameTableDTOMapper getGameTableDTOMapper() {
		return this.GameTableDTOMapper;
	}

	public ClientPlayerDTOMapper getPlayerDTOMapper() {
		return this.PlayerDTOMapper;
	}

}
