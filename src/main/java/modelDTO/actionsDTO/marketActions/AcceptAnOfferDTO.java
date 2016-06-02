package modelDTO.actionsDTO.marketActions;

import java.util.HashSet;
import java.util.Set;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.parser.AcceptAnOfferParser;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.playerDTO.AssistantDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.marketActions.AcceptAnOffer;
import server.model.gameTable.Assistant;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.market.Offer;

public class AcceptAnOfferDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4616730650885295726L;
	private OfferDTO offerDTO;
	private boolean parametersSetted=false;

	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new AcceptAnOfferParser(this, game);
	}

	@Override
	public Action map(Game game) {

		AcceptAnOffer action = new AcceptAnOffer();
		
		if (this.offerDTO.getOfferedObject() instanceof CardColourDTO) {
			CardColourDTO offeringCardDTO=(CardColourDTO) this.offerDTO.getOfferedObject();
			action.setOffer(new Offer(game.getCurrentPlayer(), 
					new PoliticsCard(new CardColour(offeringCardDTO.getName())), this.offerDTO.getPrice()));
		}
		if (this.offerDTO.getOfferedObject() instanceof PermitTileDTO) {
			PermitTileDTO offeringPermitTileDTO=(PermitTileDTO) this.offerDTO.getOfferedObject();
			for (PermitTile permitTile : game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
				if (permitTile.getBonus().equals(offeringPermitTileDTO.getBonuses()) &&
					this.checkBuildableCities(offeringPermitTileDTO, permitTile.getBuildableCities()))
				action.setOffer(new Offer(game.getCurrentPlayer(), permitTile, this.offerDTO.getPrice()));
		}
		if (this.offerDTO.getOfferedObject() instanceof AssistantDTO) {
			action.setOffer(new Offer(game.getCurrentPlayer(), new Assistant(), this.offerDTO.getPrice()));
		}

		return action;
	}
	
	private boolean checkBuildableCities(PermitTileDTO offeringPermitTileDTO, Set<City> realBuildableCities) {
		Set<String> realBuildableCitiesString =new HashSet<>();
		Set<String> buildableCitiesDTOString = new HashSet<>();
		for (City city : realBuildableCities)
			realBuildableCitiesString.add(city.getName());
		for (CityDTO cityDTO : offeringPermitTileDTO.getBuildablecities())
			buildableCitiesDTOString.add(cityDTO.getName());
		return realBuildableCitiesString.equals(buildableCitiesDTOString);
	}
	
	
	public OfferDTO getOffer() {
		return offerDTO;
	}

	public void setOffer(OfferDTO offerDTO) {
		this.offerDTO=offerDTO;
	}
	
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
	}
	
	
	@Override
	public String toString() {
		return "ao: accept an offer";
	}

}
