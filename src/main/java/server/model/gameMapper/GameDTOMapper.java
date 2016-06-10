package server.model.gameMapper;

import java.util.ArrayList;
import java.util.Set;

import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GameTableDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.marketDTO.MarketDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.playerDTO.AssistantDTO;
import modelDTO.playerDTO.ClientPlayerDTO;
import players.Player;
import server.model.Game;
import server.model.bonus.Bonus;
import server.model.gameTable.Assistant;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.CityColour;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.Emporium;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;
import server.model.market.Market;
import server.model.market.Offer;

public class GameDTOMapper implements GameMapperInterface {

	@Override
	public GameTableDTO gameTableMap(Game realGame) {
		GameTableDTO gameTableDTO=new GameTableDTO();
	
		for (RegionBoard region : realGame.getGameTable().getRegionBoards())
			gameTableDTO.getClientRegions().add(this.regionMap(region));
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			gameTableDTO.getClientKingBalcony()[i]=this.cardColourMap
					(realGame.getGameTable().getCouncilOfKing().getCouncillors()[i].getColour());
		for (Councillor councillor : realGame.getGameTable().getCouncilReserve().getCouncillors())
			gameTableDTO.getClientCouncillorReserve().add
					(this.cardColourMap(councillor.getColour()));
		for (Player player : realGame.getPlayers())
			gameTableDTO.getClientPlayers().add(this.genericPlayerMap(player));
		gameTableDTO.setClientNobilityTrack((ArrayList<Set<Bonus>>) realGame.getGameTable().getNobilityTrack().getTrack());
		gameTableDTO.setCurrentPlayer(realGame.getCurrentPlayer().getName());
		gameTableDTO.setKing(realGame.getGameTable().getKing().getCity().getName());
		
		return gameTableDTO;
	}

	@Override
	public MarketDTO marketMap(Market realObject) {
		MarketDTO marketDTO=new MarketDTO();
		
		for (Offer realOffer : realObject.getOffersList())
			marketDTO.getOffersList().add(this.offerMap(realOffer));
		for (Player realPlayer : realObject.getSellingPlayerList())
			marketDTO.getSellingPlayerList().add(realPlayer.getName());
		for (Player realPlayer : realObject.getBuyingPlayerList())
			marketDTO.getBuyingPlayerList().add(realPlayer.getName());
		
		return marketDTO;
	}

	@Override
	public ClientPlayerDTO clientPlayerMap(Player realObject) {
		ClientPlayerDTO clientPlayerDTO=new ClientPlayerDTO();
		
		clientPlayerDTO.setName(realObject.getName());
		clientPlayerDTO.setPlayerNumber(realObject.getPlayerNumber());
		for (PoliticsCard card : realObject.getHand())
			clientPlayerDTO.getHand().add(this.cardColourMap(card.getColour()));
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedDown())
			clientPlayerDTO.getCoveredPermitTiles().add(this.permitTileMap(permitTile));
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedUp())
			clientPlayerDTO.getCoveredPermitTiles().add(this.permitTileMap(permitTile));
		clientPlayerDTO.setAssistants(realObject.getNumberOfAssistants());
		
		return clientPlayerDTO;	
	}

	@Override
	public CardColourDTO cardColourMap(CardColour realObject) {
		CardColourDTO cardColourDTO=new CardColourDTO();
		cardColourDTO.setName(realObject.getColour());
		return cardColourDTO;
	}

	@Override
	public CityColourDTO cityColourMap(CityColour realObject) {
		CityColourDTO cityColourDTO=new CityColourDTO();
		cityColourDTO.setName(realObject.getName());
		return cityColourDTO;
	}

	@Override
	public CityDTO cityMap(City realObject) {
		CityDTO cityDTO=new CityDTO();
		
		cityDTO.setName(realObject.getName());
		cityDTO.setColour(this.cityColourMap(realObject.getColour()));
		for (Emporium emporium : realObject.getCityEmporiums())
			cityDTO.getBuildedEmporiums().add
					(this.genericPlayerMap(emporium.getEmporiumsPlayer()));
		cityDTO.setRewardToken(realObject.getRewardToken());
		
		return cityDTO;
	}

	@Override
	public GenericPlayerDTO genericPlayerMap(Player realObject) {
		GenericPlayerDTO genericPlayerDTO=new GenericPlayerDTO();
		
		genericPlayerDTO.setName(realObject.getName());
		genericPlayerDTO.setPlayerNumber(realObject.getPlayerNumber());
		genericPlayerDTO.setAssistants(realObject.getNumberOfAssistants());
		genericPlayerDTO.setScore(realObject.getScore());
		genericPlayerDTO.setNobility(realObject.getNobility());
		genericPlayerDTO.setCoins(realObject.getCoins());
		genericPlayerDTO.setEmporiums(realObject.getRemainigEmporiums().size());
		genericPlayerDTO.setHand(realObject.getHand().size());
		for (PermitTile permitTile : realObject.getPlayersPermitTilesTurnedUp())
			genericPlayerDTO.getAvailablePermitTiles().add
					(this.permitTileMap(permitTile));
		
		return genericPlayerDTO;
	}

	@Override
	public PermitTileDTO permitTileMap(PermitTile realObject) {
		PermitTileDTO permitTileDTO=new PermitTileDTO();
		
		for(City city : realObject.getBuildableCities())
			permitTileDTO.getBuildablecities().add(this.cityMap(city));
		permitTileDTO.setBonuses(realObject.getBonus());
		
		return permitTileDTO;
	}

	@Override
	public RegionDTO regionMap(RegionBoard realObject) {
		RegionDTO regionDTO=new RegionDTO();
		
		regionDTO.setName(realObject.getName());
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			regionDTO.getBalcony()[i]=this.cardColourMap
					(realObject.getRegionBalcony().getCouncillors()[i].getColour());
		for (City city : realObject.getRegionCities())
			regionDTO.getCities().add(this.cityMap(city));
		for (int i=0; i<2; i++)
			regionDTO.getUncoveredPermitTiles()[i]=this.permitTileMap
					(realObject.getUncoveredPermitTiles()[i]);
		
		return regionDTO;
	}

	@Override
	public OfferDTO offerMap(Offer realOffer) {
		OfferDTO offerDTO=new OfferDTO();
		
		if (realOffer.getOfferedObject() instanceof PoliticsCard)
			offerDTO.setOfferedObjectDTO(this.cardColourMap
					(((PoliticsCard) realOffer.getOfferedObject()).getColour()));
		if (realOffer.getOfferedObject() instanceof PermitTile)
			offerDTO.setOfferedObjectDTO(this.permitTileMap
					((PermitTile) realOffer.getOfferedObject()));
		if (realOffer.getOfferedObject() instanceof Assistant)
			offerDTO.setOfferedObjectDTO(new AssistantDTO());
		
		offerDTO.setOfferingPlayer(realOffer.getOfferingPlayer().getName());
		offerDTO.setPrice(realOffer.getPrice());
		
		return offerDTO;
	}
	
}
