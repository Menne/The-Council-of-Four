package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.view.ClientView;

public class BuildByKingParser implements ActionParserVisitor {

	private BuildByKingDTO selectedAction;
	
	public BuildByKingParser(BuildByKingDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	
	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to build an emporium with the help of the king.");
		
		if (!game.getClientPlayer().getHand().isEmpty()) {
			
			view.displayMessage("Now I need some other infos, like:");
		
			view.displayMessage("the name of the city in which you want to build");
			List<CityDTO> acceptableCities=new ArrayList<>();
			for (RegionDTO region : game.getClientGameTable().getClientRegions())
				for (CityDTO city : region.getCities())
					if (city.getBuildedEmporiums().isEmpty())
						acceptableCities.add(city);
			this.selectedAction.setSelectedCity(view.askForCity(acceptableCities));
			
			view.displayMessage("the colour of the cards you want to descard");
			this.selectedAction.setCardsToDescard(view.askForPoliticsCards());
			
			this.selectedAction.parametersSetted();
		
		}
		else 
			view.displayMessage("but it seems that you haven't any politics card in your hand! Select another action please");
		
		return this.selectedAction;
	}

}
