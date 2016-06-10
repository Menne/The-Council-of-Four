package server.view.mapperVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.playerDTO.AssistantDTO;
import players.Player;
import server.model.Game;
import server.model.actions.bonusActions.ChooseCityBonusAction;
import server.model.actions.bonusActions.PurchasedPermitTileAction;
import server.model.actions.marketActions.AcceptAnOffer;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.gameTable.Assistant;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;
import server.model.market.Offer;

public class ActionDTOMapper implements ActionMapperVisitor{

	private Game game;

	public ActionDTOMapper(Game game) {
		this.game=game;
	}

	@Override
	public AcquirePermitTile map(AcquirePermitTileDTO selectedActionDTO) {
		AcquirePermitTile action = new AcquirePermitTile();
		
		action.setNumberOfPermitTile(selectedActionDTO.getNumberOfPermitTiles());
		
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(region.getName().equals(selectedActionDTO.getChoosenRegion().getName()))
				action.setChosenRegion(region);
		
		List<PoliticsCard> convertedCards =new ArrayList<>();
		for(CardColourDTO cardColourDTO : selectedActionDTO.getCardsToDescard())
			convertedCards.add(new PoliticsCard(new CardColour(cardColourDTO.getName())));
		action.setCardsToDescard(convertedCards);
		
		return action;
	}

	
	@Override
	public BuildByKing map(BuildByKingDTO selectedActionDTO) {
		BuildByKing action=new BuildByKing();
		
		for (City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if (city.getName().equals(selectedActionDTO.getSelectedCity().getName()))
				action.setSelectedCity(city);
		
		List<PoliticsCard> convertedCards =new ArrayList<>();
		for (CardColourDTO cardColourDTO : selectedActionDTO.getCardsToDescard())
			convertedCards.add(new PoliticsCard(new CardColour(cardColourDTO.getName())));
		action.setCardsToDescard(convertedCards);
		
		return action;
	}

	@Override
	public BuildByPermitTile map(BuildByPermitTileDTO selectedActionDTO) {
		BuildByPermitTile action =new BuildByPermitTile();
		
		for (City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if (city.getName().equals(selectedActionDTO.getSelectedCity().getName()))
				action.setSelectedCity(city);
		
		for (PermitTile permitTile : this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
			if (permitTile.getBonus().equals(selectedActionDTO.getSelectedPermitTile().getBonuses())&&
					this.checkBuildableCities(permitTile.getBuildableCities(), selectedActionDTO.getSelectedPermitTile()))
				action.setSelectedPermitTile(permitTile);
		return action;
	}

	@Override
	public ChangePermitTiles map(ChangePermitTilesDTO selectedActionDTO) {
		ChangePermitTiles action=new ChangePermitTiles();
		
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(region.getName().equals(selectedActionDTO.getSelectedRegion().getName()))
				action.setSelectedRegion(region);
		return action;
	}

	@Override
	public ElectCouncillor map(ElectCouncillorDTO selectedActionDTO) {
		ElectCouncillor action = new ElectCouncillor();
		
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(checkCouncilBalcony(region.getRegionBalcony(), selectedActionDTO))
				action.setCouncilBalcony(region.getRegionBalcony());
		if (checkCouncilBalcony(this.game.getGameTable().getCouncilOfKing(), selectedActionDTO))
			action.setCouncilBalcony(this.game.getGameTable().getCouncilOfKing());
		
		for (Councillor councillor : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if(councillor.getColour().getColour().equals(selectedActionDTO.getNewCouncillor().getName()))
				action.setNewCouncillor(councillor);
			
		return action;
	}

	@Override
	public ElectCouncillorByAssistant map(ElectCouncillorByAssistantDTO selectedActionDTO) {
		ElectCouncillorByAssistant action =new ElectCouncillorByAssistant();
		
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(checkCouncilBalcony(region.getRegionBalcony(), selectedActionDTO))
				action.setCouncilBalcony(region.getRegionBalcony());
		if(checkCouncilBalcony(this.game.getGameTable().getCouncilOfKing(), selectedActionDTO))
			action.setCouncilBalcony(this.game.getGameTable().getCouncilOfKing());
		
		for(Councillor councillor : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if(councillor.getColour().getColour().equals(selectedActionDTO.getNewCouncillor().getName()))
				action.setNewCouncillor(councillor);
			
		return action;
	}

	@Override
	public MakeAnOffer map(MakeAnOfferDTO selectedActionDTO) {

		MakeAnOffer action=new MakeAnOffer();
		
		for (OfferDTO currentOfferDTO : selectedActionDTO.getOfferedObjectsDTO()) {
			
			Player offeringPlayer=this.game.getCurrentPlayer();
			int price=currentOfferDTO.getPrice();
			
			if (currentOfferDTO.getOfferedObjectDTO() instanceof CardColourDTO) {;
				CardColourDTO offeringCardDTO=(CardColourDTO) currentOfferDTO.getOfferedObjectDTO();
				action.addOfferToList(new Offer(offeringPlayer, 
						new PoliticsCard(new CardColour(offeringCardDTO.getName())), price));
			}
			if (currentOfferDTO.getOfferedObjectDTO() instanceof PermitTileDTO) {
				PermitTileDTO offeringPermitTileDTO=(PermitTileDTO) currentOfferDTO.getOfferedObjectDTO();
				for (PermitTile permitTile : this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
					if (permitTile.getBonus().equals(offeringPermitTileDTO.getBonuses()) &&
							this.checkBuildableCities(permitTile.getBuildableCities(), offeringPermitTileDTO))
						action.addOfferToList(new Offer(offeringPlayer, permitTile, price));

			}
			if (currentOfferDTO.getOfferedObjectDTO() instanceof AssistantDTO) {
				action.addOfferToList(new Offer(offeringPlayer, new Assistant(), price));
			}

		}
		
		return action;
	}

	@Override
	public AcceptAnOffer map(AcceptAnOfferDTO selectedActionDTO) {
		AcceptAnOffer action = new AcceptAnOffer();
		
		if (selectedActionDTO.getOffer().getOfferedObjectDTO() instanceof CardColourDTO) {
			CardColourDTO offeringCardDTO=(CardColourDTO) selectedActionDTO.getOffer().getOfferedObjectDTO();
			action.setOffer(new Offer(this.setOfferingPlayer(selectedActionDTO.getOffer()), 
					new PoliticsCard(new CardColour(offeringCardDTO.getName())), selectedActionDTO.getOffer().getPrice()));
		}
		if (selectedActionDTO.getOffer().getOfferedObjectDTO() instanceof PermitTileDTO) {
			PermitTileDTO offeringPermitTileDTO=(PermitTileDTO) selectedActionDTO.getOffer().getOfferedObjectDTO();
			for (PermitTile permitTile : this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
				if (permitTile.getBonus().equals(offeringPermitTileDTO.getBonuses()) &&
					this.checkBuildableCities(permitTile.getBuildableCities(), offeringPermitTileDTO))
				action.setOffer(new Offer(this.setOfferingPlayer(selectedActionDTO.getOffer()), permitTile, selectedActionDTO.getOffer().getPrice()));
		}
		if (selectedActionDTO.getOffer().getOfferedObjectDTO() instanceof AssistantDTO)
			action.setOffer(new Offer(this.setOfferingPlayer(selectedActionDTO.getOffer()), new Assistant(), selectedActionDTO.getOffer().getPrice()));
		
		return action;
	}

	@Override
	public ChooseCityBonusAction map(ChooseCityActionDTO selectedActionDTO) {
		ChooseCityBonusAction action=new ChooseCityBonusAction();
		
		for (City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getName().equals(selectedActionDTO.getSelectedCity().getName()))
				action.setSelectedCity(city);
		
		return action;
	}

	@Override
	public PurchasedPermitTileAction map(PurchasedPermitTileActionDTO selectedActionDTO) {
		PurchasedPermitTileAction action=new PurchasedPermitTileAction();
		
		for (PermitTile permitTile : this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
			if (permitTile.getBonus().equals(selectedActionDTO.getSelectedPermitTile().getBonuses())&&
					checkBuildableCities(permitTile.getBuildableCities(), selectedActionDTO.getSelectedPermitTile()))
				action.setSelectedPermitTile(permitTile);
		
		return action;
	}

	
	
	private boolean checkBuildableCities(Set<City> realBuildableCities, PermitTileDTO permitTileDTO){
		Set<String> realBuildableCitiesString =new HashSet<>();
		Set<String> buildableCitiesDTOString = new HashSet<>();
		for(City city : realBuildableCities)
			realBuildableCitiesString.add(city.getName());
		for(CityDTO cityDTO : permitTileDTO.getBuildablecities())
			buildableCitiesDTOString.add(cityDTO.getName());
		return realBuildableCitiesString.equals(buildableCitiesDTOString);
	}
	
	private boolean checkCouncilBalcony(CouncilBalcony realBalcony, ElectCouncillorDTO selectedActionDTO){
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			if (!realBalcony.getCouncillors()[i].getColour().getColour().equals(selectedActionDTO.getCouncilBalcony()[i].getName()))
				return false;
		return true;
	}
	
	private boolean checkCouncilBalcony(CouncilBalcony realBalcony, ElectCouncillorByAssistantDTO selectedActionDTO) {
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			if (!realBalcony.getCouncillors()[i].getColour().getColour().equals(selectedActionDTO.getCouncilBalcony()[i].getName()))
				return false;
		return true;
	}
	
	private Player setOfferingPlayer(OfferDTO offerDTO) {
		for (Player player : game.getPlayers())
			if (offerDTO.getOfferingPlayer().equals(player.getName()))
				return player;
		throw new IllegalArgumentException("the offering player is not valid");
	}
	

}
