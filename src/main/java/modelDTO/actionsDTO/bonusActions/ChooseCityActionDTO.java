package modelDTO.actionsDTO.bonusActions;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.ChooseCityBonusParser;
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
	public ActionParserVisitor setParser(GameDTO game) {
		return new ChooseCityBonusParser(this, game);
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
