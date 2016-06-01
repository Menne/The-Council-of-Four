package modelDTO.actionsDTO.standardActions;

import java.util.ArrayList;
import java.util.List;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.BuildByKingParser;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardActions.BuildByKing;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.PoliticsCard;

public class BuildByKingDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3717084594434999743L;
	private CityDTO selectedCity;
	private List<CardColourDTO> cardsToDescard;
	private boolean parametersSetted=false;

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public List<CardColourDTO> getCardsToDescard() {
		return cardsToDescard;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}

	public void setCardsToDescard(List<CardColourDTO> cardsToDescard) {
		this.cardsToDescard = cardsToDescard;
	}
	
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public Action map(Game game) {
		BuildByKing action=new BuildByKing();
		
		for(City city : game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getName().equals(this.selectedCity.getName()))
				action.setSelectedCity(city);
		
		List<PoliticsCard> convertedCards =new ArrayList<>();
		for(CardColourDTO cardColourDTO : this.cardsToDescard)
			convertedCards.add(new PoliticsCard(new CardColour(cardColourDTO.getName())));
		action.setCardsToDescard(convertedCards);
		
		return action;
	}
	
	
	@Override
	public String toString() {
		return "m4: build an emporium with the help of the king";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new BuildByKingParser(this, game);
	}
	
}
