package modelDTO.parser;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import client.view.notifies.PoliticsCardsNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.AcquirePermitTileDTO;
import modelDTO.actionsDTO.ActionDTO;

public class AcquirePermitTileParser implements ActionParserVisitor {
	
	private AcquirePermitTileDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public AcquirePermitTileParser(AcquirePermitTileDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
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
		this.game.notifyObserver(new PoliticsCardsNotify(parser.acceptablePoliticsCards(), this));
		this.selectedAction.setCardsToDescard(parser.politicsCardsTranslator(currentParameter));
		
		return this.selectedAction;
	}

}
