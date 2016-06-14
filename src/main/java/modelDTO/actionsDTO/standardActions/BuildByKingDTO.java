package modelDTO.actionsDTO.standardActions;

import java.util.List;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.BuildByKingParser;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

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
	public String toString() {
		return "m4: build an emporium with the help of the king";
	}

	@Override
	public ActionParserVisitor setParser(ClientView view, GameDTO game) {
		return new BuildByKingParser(this, view, game);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
}
