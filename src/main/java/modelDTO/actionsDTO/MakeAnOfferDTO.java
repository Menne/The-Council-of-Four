package modelDTO.actionsDTO;

import java.util.HashSet;
import java.util.Set;

import modelDTO.GameDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.marketDTO.MarketableDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.MakeAnOfferParser;
import modelDTO.playerDTO.AssistantDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.gameTable.Assistant;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;

public class MakeAnOfferDTO implements ActionDTO, ActionWithParameters {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 7027014433305067100L;
	private MarketableDTO offeringObjectDTO;
	private int price;

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new MakeAnOfferParser(this, game);
	}

	@Override
	public Action map(Game game) {
		
		MakeAnOffer action = new MakeAnOffer();
		
		if (this.offeringObjectDTO instanceof CardColourDTO) {
			CardColourDTO offeringCardDTO=(CardColourDTO) offeringObjectDTO;
			PoliticsCard convertedOffer=new PoliticsCard(new CardColour(offeringCardDTO.getName()));
			action.setOfferingObject(convertedOffer);
		}
		if (this.offeringObjectDTO instanceof PermitTileDTO) {
			PermitTileDTO offeringPermitTileDTO=(PermitTileDTO) offeringObjectDTO;
			for(PermitTile permitTile : game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
				if(permitTile.getBonus().equals(offeringPermitTileDTO.getBonuses())&&
					this.checkBuildableCities(offeringPermitTileDTO, permitTile.getBuildableCities()))
						action.setOfferingObject(permitTile);
		}
		if (this.offeringObjectDTO instanceof AssistantDTO) {
			action.setOfferingObject(new Assistant());
		}
		
		action.setPrice(this.price);
		
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
	
	public void setOfferingObject(MarketableDTO offeringObjectDTO) {
		this.offeringObjectDTO=offeringObjectDTO;
	}

	public void setPrice(int price) {
		this.price=price;
	}


}
