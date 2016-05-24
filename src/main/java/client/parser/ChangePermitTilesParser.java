package client.parser;

import client.ModelDTO.GameDTO;
import client.actionDTO.ActionDTO;
import client.actionDTO.ChangePermitTilesDTO;
import client.clientView.notifies.ActionNotify;
import client.clientView.notifies.ParametersNotify;

public class ChangePermitTilesParser implements ActionParserVisitor {

	private ChangePermitTilesDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public ChangePermitTilesParser(ChangePermitTilesDTO selectedAction, GameDTO game) {
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
				("Ok! you have chosen to change the permit tiles of a region. Now I need some other infos, like:"));
		
		this.game.notifyObserver(new ActionNotify
				("the name of the region in which you want to pick"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableRegions(), this));
		this.selectedAction.setSelectedRegion(parser.regionTranslator(currentParameter));
		
		return this.selectedAction;
	}


}
