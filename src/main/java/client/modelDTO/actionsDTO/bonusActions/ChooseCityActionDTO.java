package client.modelDTO.actionsDTO.bonusActions;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsParametersSetters.ActionParametersSetter;
import client.modelDTO.actionsParametersSetters.bonusActionsParametersSetters.ChooseCityBonusParametersSetter;
import client.modelDTO.gameTableDTO.CityDTO;
import server.model.actions.Action;
import server.model.mappers.ActionMapperVisitor;

/**
 * This class represents the DTO version of the ChooseCityAction bonus action, with all the DTO parameters 
 * necessary but without logic
 * @author cg31
 *
 */
public class ChooseCityActionDTO implements ActionDTO, ActionWithParameters {
	
	private static final long serialVersionUID = 8237414941174350412L;
	private List<CityDTO> selectedCities;
	private final int numberOfCities;
	private boolean parametersSetted=false;

	public ChooseCityActionDTO(int numberOfCities) {
		this.numberOfCities=numberOfCities;
		this.selectedCities=new ArrayList<>();
	}

	@Override
	public ActionParametersSetter setParser() {
		return new ChooseCityBonusParametersSetter(this);
	}
	
	public int getNumberOfCities() {
		return this.numberOfCities;
	}

	public List<CityDTO> getSelectedCities() {
		return this.selectedCities;
	}

	public void setSelectedCities(List<CityDTO> selectedCities) {
		this.selectedCities=selectedCities;
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
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}
	
	@Override
	public String toString() {
		return "b1: get bonus!";
	}

}
