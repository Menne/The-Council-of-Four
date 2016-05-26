package modelDTO.parser;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.BuildByPermitTileDTO;

public class BuildByPermitTileParser implements ActionParserVisitor {

	private BuildByPermitTileDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	
	public BuildByPermitTileParser(BuildByPermitTileDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}

	
	@Override
	public ActionDTO setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify
				("Ok! you have chosen to build an emporium with a permit tile. Now I need some other infos, like:"));
		
		this.game.notifyObserver(new ActionNotify
				("the name of the city in which you want to build"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableCities(), this));
		this.selectedAction.setSelectedCity(parser.cityTranslator(currentParameter));
		
		this.game.notifyObserver(new ActionNotify
				("the permit tile you want to use"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptablePermitTiles(), this));
		this.selectedAction.setSelectedPermitTile(parser.permitTileTranslator(currentParameter));
		
		return this.selectedAction;
	}

}
