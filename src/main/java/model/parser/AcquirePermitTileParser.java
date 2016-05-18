package model.parser;

import model.Game;
import model.actions.AcquirePermitTile;
import model.actions.Action;
import view.ActionNotify;
import view.ParametersNotify;

public class AcquirePermitTileParser implements ActionParserVisitor {
	
	private AcquirePermitTile selectedAction;
	private String currentParameter;
	private Game game;
	
	public AcquirePermitTileParser(AcquirePermitTile selectedAction, Game game) {
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
				("Ok! you have chosen to acquire a permit tile. Now I need some other infos, like:"));
		
		this.game.notifyObserver(new ActionNotify
				("the name of the region in which you want to pick"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableRegions(), this));
		this.selectedAction.setChosenRegion(parser.regionTranslator(currentParameter));
		
		this.game.notifyObserver(new ActionNotify
				("the number of permit tile you want to pick"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableNumberOfPermitTile(), this));
		this.selectedAction.setNumberOfPermitTile(parser.numberOfPermitTileTranslator(currentParameter));
		
		this.game.notifyObserver(new ActionNotify
				("the colours of the cards in your hand you want to descard"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptablePoliticsCards(), this));
		this.selectedAction.setCardsToDescard(parser.politicsCardsTranslator(currentParameter));
		
		return this.selectedAction;
	}

}
