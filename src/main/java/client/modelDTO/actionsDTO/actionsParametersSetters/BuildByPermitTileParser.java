package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.view.ClientView;

public class BuildByPermitTileParser implements ActionParserVisitor {

	private BuildByPermitTileDTO selectedAction;

	public BuildByPermitTileParser(BuildByPermitTileDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	
	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to build an emporium with a permit tile.");
			
		if (!game.getClientPlayer().getAvailablePermitTiles().isEmpty()) {
			
			view.displayMessage("Now I need some other infos, like:");
		
			view.displayMessage("the permit tile you want to use");
			PermitTileDTO permitTileTranslated=view.askForPermitTile();
			this.selectedAction.setSelectedPermitTile(permitTileTranslated);
			
			view.displayMessage("the name of the city in which you want to build");
			List<CityDTO> acceptableCities=new ArrayList<>();
			for (CityDTO acceptableCity : permitTileTranslated.getBuildablecities())
				acceptableCities.add(acceptableCity);
			this.selectedAction.setSelectedCity(view.askForCity(acceptableCities));
			
			this.selectedAction.parametersSetted();
		
		}
		else 
			view.displayMessage("but it seems that you haven't any permit tile turned up! Select another action please");
		
		return this.selectedAction;
	}

}
