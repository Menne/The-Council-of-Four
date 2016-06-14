package modelDTO.parser;

import java.util.ArrayList;
import java.util.List;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.gameTableDTO.RegionDTO;

public class ChooseCityBonusParser implements ActionParserVisitor {

	private ChooseCityActionDTO selectedAction;
	private ClientView view;
	private GameDTO game;

	public ChooseCityBonusParser(ChooseCityActionDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}


	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("City bonus earned! You have the possibility to choose from the cities in which you have built"
						+ "an emporium, and get the bonuses associated to that");
		
		List<CityDTO> acceptableCities=new ArrayList<>();
		for (RegionDTO region : this.game.getClientGameTable().getClientRegions())
			for (CityDTO city : region.getCities())
				if(city.getBuildedEmporiums().isEmpty())
					acceptableCities.add(city);
				else
					for (GenericPlayerDTO emporium : city.getBuildedEmporiums())
						if (emporium.equals(this.game.getClientPlayer()))
							acceptableCities.add(city);
				
		if (!acceptableCities.isEmpty()) {
		
			this.selectedAction.setCity(this.view.askForCity(acceptableCities));
			
			this.selectedAction.parametersSetted();
		}
		
		else 
			this.view.displayMessage("But it seems you haven't built an emporium yet");
		
		return selectedAction;
	}

}
