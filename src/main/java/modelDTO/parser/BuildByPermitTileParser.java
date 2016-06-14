package modelDTO.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

import client.view.ClientView;
import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;

public class BuildByPermitTileParser implements ActionParserVisitor {

	private BuildByPermitTileDTO selectedAction;
	private Object currentParameter;
	private ClientView view;
	private GameDTO game;

	public BuildByPermitTileParser(BuildByPermitTileDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}

	
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	
	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Ok! you have chosen to build an emporium with a permit tile.");
			
		if (!this.game.getClientPlayer().getAvailablePermitTiles().isEmpty()) {
			
			this.view.displayMessage("Now I need some other infos, like:");
		
			this.view.displayMessage("the permit tile you want to use");
			PermitTileDTO permitTileTranslated=this.view.askForPermitTile
					(this.game.getClientPlayer().getAvailablePermitTiles());
			this.selectedAction.setSelectedPermitTile(permitTileTranslated);
			
			this.view.displayMessage("the name of the city in which you want to build");
			List<CityDTO> acceptableCities=new ArrayList<>();
			for (CityDTO acceptableCity : permitTileTranslated.getBuildablecities())
				acceptableCities.add(acceptableCity);
			this.selectedAction.setSelectedCity(this.view.askForCity(acceptableCities));
			
			this.selectedAction.parametersSetted();
		
		}
		else 
			this.view.displayMessage("but it seems that you haven't any permit tile turned up! Select another action please");
		
		return this.selectedAction;
	}

}
