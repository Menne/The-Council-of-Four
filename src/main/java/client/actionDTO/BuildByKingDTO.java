package client.actionDTO;

import java.util.ArrayList;
import java.util.List;

import client.ModelDTO.CardColourDTO;
import client.ModelDTO.CityDTO;
import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.BuildByKingParser;
import model.Game;
import model.actions.Action;
import model.actions.standardAction.BuildByKing;
import model.gameTable.CardColour;
import model.gameTable.City;
import model.gameTable.PoliticsCard;

public class BuildByKingDTO implements ActionDTO{

	private CityDTO selectedCity;
	private List<CardColourDTO> cardsToDescard;

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

	@Override
	public Action map(Game game) {
		BuildByKing action=new BuildByKing();
		
		for(City city : game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getName().equals(this.selectedCity.getName()))
				action.setSelectedCity(city);
		
		List<PoliticsCard> convertedCards =new ArrayList<PoliticsCard>();
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
