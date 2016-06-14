package modelDTO.parser;

import java.util.ArrayList;
import java.util.List;

import client.view.ClientView;
import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import client.view.notifies.PoliticsCardsNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.GenericPlayerDTO;
import modelDTO.gameTableDTO.RegionDTO;

public class BuildByKingParser implements ActionParserVisitor {

	private BuildByKingDTO selectedAction;
	private Object currentParameter;
	private ClientView view;
	private GameDTO game;
	
	public BuildByKingParser(BuildByKingDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}

	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	
	
	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Ok! you have chosen to build an emporium with the help of the king.");
		
		if (!this.game.getClientPlayer().getHand().isEmpty()) {
			
			this.view.displayMessage("Now I need some other infos, like:");
		
			this.view.displayMessage("the name of the city in which you want to build");
			List<CityDTO> acceptableCities=new ArrayList<>();
			for (RegionDTO region : this.game.getClientGameTable().getClientRegions())
				for (CityDTO city : region.getCities())
					if (city.getBuildedEmporiums().isEmpty())
						acceptableCities.add(city);
			this.selectedAction.setSelectedCity(this.view.askForCity(acceptableCities));
			
			this.view.displayMessage("the colour of the cards you want to descard");
			this.selectedAction.setCardsToDescard(this.view.askForPoliticsCards
					(this.game.getClientPlayer().getHand()));
			
			this.selectedAction.parametersSetted();
		
		}
		else 
			this.view.displayMessage("but it seems that you haven't any politics card in your hand! Select another action please");
		
		return this.selectedAction;
	}

}
