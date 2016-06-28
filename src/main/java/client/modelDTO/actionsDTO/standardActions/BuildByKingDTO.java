package client.modelDTO.actionsDTO.standardActions;

import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.BuildByKingParser;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class BuildByKingDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3717084594434999743L;
	private CityDTO selectedCity;
	private List<PoliticsCardDTO> cardsToDescard;
	private boolean parametersSetted=false;

	
	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public List<PoliticsCardDTO> getCardsToDescard() {
		return cardsToDescard;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}

	public void setCardsToDescard(List<PoliticsCardDTO> cardsToDescard) {
		this.cardsToDescard=cardsToDescard;
	}
	
	@Override
	public boolean checkIfParametersSetted() {
		return parametersSetted;
	}

	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "m4: build an emporium with the help of the king";
	}

	@Override
	public ActionParserVisitor setParser() {
		return new BuildByKingParser(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
}
