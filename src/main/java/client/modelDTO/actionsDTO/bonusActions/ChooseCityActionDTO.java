package client.modelDTO.actionsDTO.bonusActions;

import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.ActionWithParameters;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.modelDTO.actionsDTO.actionsParametersSetters.ChooseCityBonusParser;
import client.modelDTO.gameTableDTO.CityDTO;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class ChooseCityActionDTO implements ActionDTO, ActionWithParameters{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8237414941174350412L;
	private List<CityDTO> selectedCities;
	private boolean parametersSetted=false;
	private final int numberOfCities;

	public ChooseCityActionDTO(int numberOfCities) {
		this.numberOfCities=numberOfCities;
	}

	@Override
	public ActionParserVisitor setParser() {
		return new ChooseCityBonusParser(this);
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
	public boolean checkIfParametersSetted() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return mapper.map(this);
	}

}
