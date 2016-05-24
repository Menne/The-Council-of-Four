package client.actionDTO;

import java.util.List;

import client.ModelDTO.CityDTO;
import model.gameTable.CardColour;

public class BuildByKingDTO implements ActionDTO{

	private CityDTO selectedCity;
	private List<CardColour> cardsToDescard;

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public List<CardColour> getCardsToDescard() {
		return cardsToDescard;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}

	public void setCardsToDescard(List<CardColour> cardsToDescard) {
		this.cardsToDescard = cardsToDescard;
	}

	@Override
	public String toString() {
		return "m4: build an emporium with the help of the king";
	}
	
}
