package modelDTO.parser;

import client.view.ClientView;
import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;

public class ChangePermitTilesParser implements ActionParserVisitor {

	private ChangePermitTilesDTO selectedAction;
	private Object currentParameter;
	private ClientView view;
	private GameDTO game;
	
	public ChangePermitTilesParser(ChangePermitTilesDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}
	
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	
	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Ok! you have chosen to change the permit tiles of a region. Now I need some other infos, like:");
		
		this.view.displayMessage("the name of the region in which you want to pick");
		this.selectedAction.setSelectedRegion(this.view.askForRegionBoard
				(this.game.getClientGameTable().getClientRegions()));
		
		this.selectedAction.parametersSetted();
		
		return this.selectedAction;
	}

}
