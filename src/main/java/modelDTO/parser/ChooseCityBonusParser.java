package modelDTO.parser;

import java.util.ArrayList;
import java.util.List;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.gameTableDTO.RegionDTO;

public class ChooseCityBonusParser implements ActionParserVisitor {

	private ChooseCityActionDTO selectedAction;
	private GameDTO game;
	private String currentParameter;

	public ChooseCityBonusParser(ChooseCityActionDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.game=game;
	}

	@Override
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	@Override
	public ActionDTO setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify
				("City bonus earned! You have the possibility to choose from the cities in which you have built"
						+ "an emporium, and get the bonuses associated to that"));
		
		List<String> acceptableCityNames=new ArrayList<String>();
		for (RegionDTO region : this.game.getClientGameTable().getClientRegions())
			for (CityDTO city : region.getCities())
				if(city.getBuildedEmporiums().isEmpty())
					acceptableCityNames.add(city.getName());
				else
					for (GenericPlayerDTO emporium : city.getBuildedEmporiums())
						if (emporium.equals(this.game.getClientPlayer()))
							acceptableCityNames.add(city.getName());
				
		if (!acceptableCityNames.isEmpty()) {
		
			this.game.notifyObserver(new ParametersNotify(parser.acceptableCities(), this));
			this.selectedAction.setCity(parser.cityTranslator(currentParameter));
		
			this.selectedAction.parametersSetted();
		}
		
		else 
			this.game.notifyObserver(new ActionNotify
					("But it seems you haven't built an emporium yet"));
		
		return selectedAction;
	}

}
