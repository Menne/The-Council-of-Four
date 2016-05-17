package model.parser;

import model.Game;
import model.actions.Action;
import model.actions.ChangePermitTiles;
import view.ActionNotify;
import view.ParametersNotify;

public class ChangePermitTilesParser implements ActionParserVisitor {

	private ChangePermitTiles selectedAction;
	private String currentParameter;
	private Game game;
	
	public ChangePermitTilesParser(ChangePermitTiles selectedAction, Game game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}
	
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	
	@Override
	public Action setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify
				("Ok! you have chosen to change the permit tiles of a region. Now I need some other infos, like:"));
		
		this.game.notifyObserver(new ActionNotify
				("the name of the region in which you want to pick"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableRegions(), this));
		this.selectedAction.setSelectedRegion(parser.regionTranslator(currentParameter));
		
		return this.selectedAction;
	}


}
