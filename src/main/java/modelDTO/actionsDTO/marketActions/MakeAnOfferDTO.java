package modelDTO.actionsDTO.marketActions;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.MakeAnOfferParser;
import modelDTO.playerDTO.AssistantDTO;
import players.Player;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.gameTable.Assistant;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.market.Offer;

public class MakeAnOfferDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7027014433305067100L;
	private List<OfferDTO> offeredObjectsDTO;
	private boolean parametersSetted=false;

	public MakeAnOfferDTO() {
		this.offeredObjectsDTO=new ArrayList<>();
	}
	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new MakeAnOfferParser(this, game);
	}

	@Override
	public Action map(Game game) {
		
		MakeAnOffer action=new MakeAnOffer();
		
		for (OfferDTO currentOfferDTO : offeredObjectsDTO) {
			
			Player offeringPlayer=game.getCurrentPlayer();
			int price=currentOfferDTO.getPrice();
			
			if (currentOfferDTO.getOfferedObjectDTO() instanceof CardColourDTO) {;
				CardColourDTO offeringCardDTO=(CardColourDTO) currentOfferDTO.getOfferedObjectDTO();
				action.addOfferToList(new Offer(offeringPlayer, 
						new PoliticsCard(new CardColour(offeringCardDTO.getName())), price));
			}
			if (currentOfferDTO.getOfferedObjectDTO() instanceof PermitTileDTO) {
				PermitTileDTO offeringPermitTileDTO=(PermitTileDTO) currentOfferDTO.getOfferedObjectDTO();
				for (PermitTile permitTile : game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
					if (permitTile.getBonus().equals(offeringPermitTileDTO.getBonuses()) &&
							this.checkBuildableCities(offeringPermitTileDTO, permitTile.getBuildableCities()))
						action.addOfferToList(new Offer(offeringPlayer, permitTile, price));

			}
			if (currentOfferDTO.getOfferedObjectDTO() instanceof AssistantDTO) {
				action.addOfferToList(new Offer(offeringPlayer, new Assistant(), price));
			}

		}
		
		return action;
	}

	private boolean checkBuildableCities(PermitTileDTO offeringPermitTileDTO, Set<City> realBuildableCities) {
		Set<String> realBuildableCitiesString =new HashSet<>();
		Set<String> buildableCitiesDTOString = new HashSet<>();
		for(City city : realBuildableCities)
			realBuildableCitiesString.add(city.getName());
		for(CityDTO cityDTO : offeringPermitTileDTO.getBuildablecities())
			buildableCitiesDTOString.add(cityDTO.getName());
		return realBuildableCitiesString.equals(buildableCitiesDTOString);
	}
	

	public List<OfferDTO> getOfferedObjectsDTO() {
		return this.offeredObjectsDTO;
	}

	public void setOfferedObjectsDTO(List<OfferDTO> offeredObjectsDTO) {
		this.offeredObjectsDTO=offeredObjectsDTO;
	}
	
	@Override
	public boolean checkIfParametersSetted() {
		return this.parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
	}
	
	public void addOfferToList(OfferDTO offerDTO) {
		this.offeredObjectsDTO.add(offerDTO);
	}

	@Override
	public String toString() {
		return "mo: make an offer";
	}

	
}
