package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a AcquirePermitTileDTO
 * @author cg31
 *
 */
public class AcquirePermitTileParser implements ActionParserVisitor {
	
	private AcquirePermitTileDTO selectedAction;
	
	/**
	 * Constructor of AcquirePermitTileDTO
	 * @param selectedAction is the action selected by the user
	 */
	public AcquirePermitTileParser(AcquirePermitTileDTO selectedAction) {
		this.selectedAction=selectedAction;
	}
	
	
	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to acquire a permit tile.");
			
		if (!game.getClientPlayer().getHand().isEmpty()) {
			
			view.displayMessage("Select the region in which you want to pick the permit tile");
			this.selectedAction.setChosenRegion(view.askForRegionBoard());
	
			view.displayMessage("Select the permit tile you want to pick");
			this.selectedAction.setNumberOfPermitTile(view.askForNumberOfPermitTile
					(this.selectedAction.getChoosenRegion()));
			
			view.displayMessage("Select the politics cards you want to descard");
			this.selectedAction.setCardsToDescard(view.askForPoliticsCards());
			
			this.selectedAction.parametersSet();
			
		}
		else 
			view.displayMessage("But it seems that you haven't any politics card in your hand! Select another action please");
	}

}
