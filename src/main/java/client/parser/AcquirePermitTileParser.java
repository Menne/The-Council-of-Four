package client.parser;

import client.ModelDTO.GameDTO;
import client.actionDTO.AcquirePermitTileDTO;
import client.actionDTO.ActionDTO;
import client.clientView.notifies.ActionNotify;
import client.clientView.notifies.ParametersNotify;

public class AcquirePermitTileParser implements ActionParserVisitor {
	
	private AcquirePermitTileDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public AcquirePermitTileParser(AcquirePermitTileDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}

	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	
	
	@Override
	public ActionDTO setParameters(Parser parser) {
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
	//	this.game.notifyObserver(new ParametersNotify(parser.acceptablePoliticsCards(), this));
	//	this.selectedAction.setCardsToDescard(parser.politicsCardsTranslator(currentParameter));
		
		return this.selectedAction;
	}

}
