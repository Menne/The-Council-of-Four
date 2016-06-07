package modelDTO.actionsDTO.bonusActions;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.ChooseCityBonusParser;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.bonusActions.ChooseCityBonusAction;
import server.model.gameTable.City;

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

	@Override
	public boolean checkIfParametersSetted() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	@Override
	public Action map(Game game) {

		ChooseCityBonusAction action=new ChooseCityBonusAction();
		
		for(City city : game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getName().equals(this.selectedCity.getName()))
				action.setSelectedCity(city);
		
		return action;
	}

}
