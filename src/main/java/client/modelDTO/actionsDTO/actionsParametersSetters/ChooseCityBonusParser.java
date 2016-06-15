package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;

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
