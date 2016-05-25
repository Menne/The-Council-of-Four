package client.actionDTO;

import java.util.List;

import client.ModelDTO.CardColourDTO;
import client.ModelDTO.CityDTO;
import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.BuildByKingParser;
import model.Game;
import model.actions.Action;
import model.gameTable.CardColour;

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
	public String toString() {
		return "m4: build an emporium with the help of the king";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new BuildByKingParser(this, game);
	}

	@Override
	public Action map(Game game) {
		// TODO Auto-generated method stub
		return null;
	}
	
}
