package server.model.gameMapper;

import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GameTableDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.marketDTO.MarketDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;
import server.model.Game;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.CityColour;
import server.model.gameTable.PermitTile;
import server.model.gameTable.RegionBoard;
import server.model.market.Market;
import server.model.market.Offer;

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
	
}