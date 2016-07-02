package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a BuildByPermitTileDTO
 * @author cg31
 *
 */
public class BuildByPermitTileParser implements ActionParserVisitor {

	private BuildByPermitTileDTO selectedAction;

	/**
	 * Constructor of BuildByPermitTileDTO
	 * @param selectedAction is the action selected by the user
	 */
	public BuildByPermitTileParser(BuildByPermitTileDTO selectedAction) {
		this.selectedAction=selectedAction;
	}

	
	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to build an emporium with a permit tile.");
			
		if (!game.getClientPlayer().getAvailablePermitTiles().isEmpty()) {
			
			view.displayMessage("Select the permit tile you want to use to build an emporium");
			PermitTileDTO permitTileTranslated=view.askForPermitTile();
			this.selectedAction.setSelectedPermitTile(permitTileTranslated);
			
			view.displayMessage("Select the city in which you want to build");
			List<CityDTO> acceptableCities=new ArrayList<>();
			for (CityDTO acceptableCity : permitTileTranslated.getBuildablecities())
				acceptableCities.add(acceptableCity);
			this.selectedAction.setSelectedCity(view.askForCity(acceptableCities));
			
			this.selectedAction.parametersSet();
		
		}
		else 
			view.displayMessage("But it seems that you haven't any permit tile turned up! Select another action please");
	}

}
