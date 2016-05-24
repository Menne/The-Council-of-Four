package client.actionDTO;

import java.util.List;

import client.ModelDTO.CityDTO;
import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.BuildByKingParser;
import model.gameTable.CardColour;

public class BuildByKingDTO implements ActionDTO{

	private final CityDTO selectedCity;
	private final List<CardColour> cardsToDescard;
	
	public BuildByKingDTO(CityDTO selectedCity, List<CardColour> cardsToDescard){
		this.selectedCity=selectedCity;
		this.cardsToDescard=cardsToDescard;
	}

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public List<CardColour> getCardsToDescard() {
		return cardsToDescard;
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
