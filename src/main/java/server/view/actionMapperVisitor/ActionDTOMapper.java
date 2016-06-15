package server.view.actionMapperVisitor;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.AssistantDTO;
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
import server.model.player.Player;

/**
 * This class implements the interface ActionMapperVisitor and provides all the logic for mapping
 * the parameters of the actions DTO into their corresponding real parameters of the real action
 * @author cg31
 *
 */
public class ActionDTOMapper implements ActionMapperVisitor{

	private Game game;

	public ActionDTOMapper(Game game) {
		this.game=game;
	}

	/**
	 * This method creates a new AcquirePermitTile action according to the AcquirePermitTileDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * return an AcquirePermitTile action with all the parameters set
	 */
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

	/**
	 * This method creates a new BuildByKing action according to the BuildByKingDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * return an BuildByKing action with all the parameters set
	 */
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

	/**
	 * This method creates a new BuildByPermitTile action according to the BuildByPermitTileDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an BuildByPermitTile action with all the parameters set
	 */
	@Override
	public BuildByPermitTile map(BuildByPermitTileDTO selectedActionDTO) {
		BuildByPermitTile action =new BuildByPermitTile();
		
		for (City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if (city.getName().equals(selectedActionDTO.getSelectedCity().getName()))
				action.setSelectedCity(city);
		
		for (PermitTile permitTile : this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
			if (permitTile.getBonuses().equals(selectedActionDTO.getSelectedPermitTile().getBonuses()) && //L'ERRORE é QUI!!
					this.checkBuildableCities(permitTile.getBuildableCities(), selectedActionDTO.getSelectedPermitTile()))
				action.setSelectedPermitTile(permitTile);
		return action;
	}

	/**
	 * This method creates a new ChangePermitTiles action according to the ChangePermitTilesDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ChangePermitTiles action with all the parameters set
	 */
	@Override
	public ChangePermitTiles map(ChangePermitTilesDTO selectedActionDTO) {
		ChangePermitTiles action=new ChangePermitTiles();
		
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(region.getName().equals(selectedActionDTO.getSelectedRegion().getName()))
				action.setSelectedRegion(region);
		return action;
	}

	/**
	 * This method creates a new ElectCouncillor action according to the ElectCouncillorDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ElectCouncillor action with all the parameters set
	 */
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

	/**
	 * This method creates a new ElectCouncillorByAssistant action according to the ElectCouncillorByAssistantDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ElectCouncillorByAssistant action with all the parameters set
	 */
	@Override
	public ElectCouncillorByAssistant map(ElectCouncillorByAssistantDTO selectedActionDTO) {
		ElectCouncillorByAssistant action =new ElectCouncillorByAssistant();
		
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

	/**
	 * This method creates a new MakeAnOffer action according to the MakeAnOfferDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an MakeAnOffer action with all the parameters set
	 */
	@Override
	public MakeAnOffer map(MakeAnOfferDTO selectedActionDTO) {

		MakeAnOffer action=new MakeAnOffer();
		
		for (OfferDTO currentOfferDTO : selectedActionDTO.getOfferedObjectsDTO()) {
			
			Player offeringPlayer=this.game.getCurrentPlayer();
			int price=currentOfferDTO.getPrice();
			
			if (currentOfferDTO.getOfferedObjectDTO() instanceof CardColourDTO) {
				CardColourDTO offeringCardDTO=(CardColourDTO) currentOfferDTO.getOfferedObjectDTO();
				action.addOfferToList(new Offer(offeringPlayer, 
						new PoliticsCard(new CardColour(offeringCardDTO.getName())), price));
			}
			if (currentOfferDTO.getOfferedObjectDTO() instanceof PermitTileDTO) {
				PermitTileDTO offeringPermitTileDTO=(PermitTileDTO) currentOfferDTO.getOfferedObjectDTO();
				for (PermitTile permitTile : this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
					if (permitTile.getBonuses().equals(offeringPermitTileDTO.getBonuses()) &&
							this.checkBuildableCities(permitTile.getBuildableCities(), offeringPermitTileDTO))
						action.addOfferToList(new Offer(offeringPlayer, permitTile, price));

			}
			if (currentOfferDTO.getOfferedObjectDTO() instanceof AssistantDTO)
				action.addOfferToList(new Offer(offeringPlayer, new Assistant(), price));
		}
		return action;
	}

	/**
	 * This method creates a new AcceptAnOffer action according to the AcceptAnOfferDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an AcceptAnOffer action with all the parameters set
	 */
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
				if (permitTile.getBonuses().equals(offeringPermitTileDTO.getBonuses()) &&
					this.checkBuildableCities(permitTile.getBuildableCities(), offeringPermitTileDTO))
				action.setOffer(new Offer(this.setOfferingPlayer(selectedActionDTO.getOffer()), permitTile, selectedActionDTO.getOffer().getPrice()));
		}
		if (selectedActionDTO.getOffer().getOfferedObjectDTO() instanceof AssistantDTO)
			action.setOffer(new Offer(this.setOfferingPlayer(selectedActionDTO.getOffer()), new Assistant(), selectedActionDTO.getOffer().getPrice()));
		
		return action;
	}

	/**
	 * This method creates a new ChooseCityBonusAction action according to the ChooseCityBonusActionDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an ChooseCityBonusAction action with all the parameters set
	 */
	@Override
	public ChooseCityBonusAction map(ChooseCityActionDTO selectedActionDTO) {
		ChooseCityBonusAction action=new ChooseCityBonusAction();
		
		for (City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getName().equals(selectedActionDTO.getSelectedCity().getName()))
				action.setSelectedCity(city);
		
		return action;
	}

	/**
	 * This method creates a new PurchasedPermitTileAction action according to the PurchasedPermitTileActionDTO received,
	 * then maps all the parameters DTO into their corresponding real objects, setting them to the real action created
	 * @param selectedActionDTO is the action selected by the client whose parameters need to be translated
	 * @return an PurchasedPermitTileAction action with all the parameters set
	 */
	@Override
	public PurchasedPermitTileAction map(PurchasedPermitTileActionDTO selectedActionDTO) {
		PurchasedPermitTileAction action=new PurchasedPermitTileAction();
		
		for (PermitTile permitTile : this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
			if (permitTile.getBonuses().equals(selectedActionDTO.getSelectedPermitTile().getBonuses())&&
					checkBuildableCities(permitTile.getBuildableCities(), selectedActionDTO.getSelectedPermitTile()))
				action.setSelectedPermitTile(permitTile);
		
		return action;
	}

	
	/**
	 * This method checks if the cites in the permit tile DTO are equal to the ones in the real permit tile
	 * @param realBuildableCities are the cities in the real permit tile
	 * @param permitTileDTO is the permit tile whose city have to be analyzed
	 * @return true if the cities on the two permit tiles are the same, false otherwise
	 */
	private boolean checkBuildableCities(Set<City> realBuildableCities, PermitTileDTO permitTileDTO){
		Set<String> realBuildableCitiesString=new HashSet<>();
		Set<String> buildableCitiesDTOString=new HashSet<>();
		for (City city : realBuildableCities)
			realBuildableCitiesString.add(city.getName());
		for (CityDTO cityDTO : permitTileDTO.getBuildablecities())
			buildableCitiesDTOString.add(cityDTO.getName());
		return realBuildableCitiesString.equals(buildableCitiesDTOString);
	}
	
	/**
	 * This method checks if the colour of the selected councillor matchers with the one in the real balcony
	 * @param realBalcony is the real balcony of the game
	 * @param selectedAction is the action DTO from which get the councillor
	 * @return true if the checking went fine, false otherwise
	 */
	private boolean checkCouncilBalcony(CouncilBalcony realBalcony, ElectCouncillorDTO selectedActionDTO){
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			if (!realBalcony.getCouncillors()[i].getColour().getColour().equals(selectedActionDTO.getCouncilBalcony()[i].getName()))
				return false;
		return true;
	}
	
	/**
	 * This method checks if the colour of the selected councillor matchers with the one in the real balcony
	 * @param realBalcony is the real balcony of the game
	 * @param selectedAction is the action DTO from which get the councillor
	 * @return true if the checking went fine, false otherwise
	 */
	private boolean checkCouncilBalcony(CouncilBalcony realBalcony, ElectCouncillorByAssistantDTO selectedActionDTO) {
		for (int i=0; i<CouncilBalcony.getNumberofcouncillors(); i++)
			if (!realBalcony.getCouncillors()[i].getColour().getColour().equals(selectedActionDTO.getCouncilBalcony()[i].getName()))
				return false;
		return true;
	}
	
	/**
	 * This method returns the player who is offering an offer of the market
	 * @param offerDTO is the offer of which we want to know the offering player
	 * @return the offering player
	 */
	private Player setOfferingPlayer(OfferDTO offerDTO) {
		for (Player player : game.getPlayers())
			if (offerDTO.getOfferingPlayer().equals(player.getName()))
				return player;
		throw new IllegalArgumentException("the offering player is not valid");
	}

}
