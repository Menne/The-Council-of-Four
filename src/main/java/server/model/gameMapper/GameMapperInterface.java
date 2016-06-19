package server.model.gameMapper;

import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import server.model.Game;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.CityColour;
import server.model.gameTable.Councillor;
import server.model.gameTable.PermitTile;
import server.model.gameTable.RegionBoard;
import server.model.gameTable.RewardToken;
import server.model.market.Market;
import server.model.market.Offer;
import server.model.player.Player;

public interface GameMapperInterface {

	public GameTableDTO gameTableMap(Game realObject);
	public MarketDTO marketMap(Market realObject);
	public ClientPlayerDTO clientPlayerMap(Player realObject);
	public CardColourDTO cardColourMap(CardColour realObject);
	public CityColourDTO cityColourMap(CityColour realObject);
	public CityDTO cityMap(City realObject);
	public GenericPlayerDTO genericPlayerMap(Player realObject);
	public PermitTileDTO permitTileMap(PermitTile realObject);
	public RegionDTO regionMap(RegionBoard realObject);
	public OfferDTO offerMap(Offer realOffer);
	public CouncillorDTO councillorMap(Councillor realCouncillor);
	public RewardTokenDTO rewardTokenMap(RewardToken realToken);
}