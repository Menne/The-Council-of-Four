package client.modelDTO.actionsDTO.standardActions;

import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsParametersSetters.ActionParametersSetter;
import client.modelDTO.actionsParametersSetters.BuildByKingParametersSetter;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import server.model.actions.Action;
import server.model.mappers.ActionMapperVisitor;

/**
 * This class represents the DTO version of the BuildByKing action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class BuildByKingDTO implements ActionDTO, ActionWithParameters {

	private static final long serialVersionUID = -3717084594434999743L;
	private CityDTO selectedCity;
	private List<PoliticsCardDTO> cardsToDescard;
	private boolean parametersSetted=false;

	
	public CityDTO getSelectedCity() {
		return this.selectedCity;
	}

	public List<PoliticsCardDTO> getCardsToDescard() {
		return this.cardsToDescard;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}

	public void setCardsToDescard(List<PoliticsCardDTO> cardsToDescard) {
		this.cardsToDescard=cardsToDescard;
	}
	
	@Override
	public boolean checkIfParametersSet() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSet() {
		this.parametersSetted=true;
	}

	@Override
	public String toString() {
		return "m4: build an emporium with the help of the king";
	}

	@Override
	public ActionParametersSetter setParser() {
		return new BuildByKingParametersSetter(this);
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
}
