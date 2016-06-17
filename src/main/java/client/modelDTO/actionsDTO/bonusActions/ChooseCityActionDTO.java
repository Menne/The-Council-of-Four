package client.modelDTO.actionsDTO.bonusActions;

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
	private CityDTO selectedCity;
	private boolean parametersSetted=false;

	@Override
	public ActionParserVisitor setParser() {
		return new ChooseCityBonusParser(this);
	}
	
	public void setCity(CityDTO selectedCity) {
		this.selectedCity=selectedCity;
	}

	public CityDTO getSelectedCity() {
		return this.selectedCity;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
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
