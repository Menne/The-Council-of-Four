package model.parser;

import model.Game;
import model.actions.Action;
import model.actions.BuildByPermitTile;
import view.ActionNotify;
import view.ParametersNotify;

public class BuildByPermitTileParser implements ActionParserVisitor {

	private BuildByPermitTile selectedAction;
	private String currentParameter;
	private Game game;
	
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	
	public BuildByPermitTileParser(BuildByPermitTile selectedAction, Game game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}

	
	@Override
	public Action setParameters(Parser parser) {
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
