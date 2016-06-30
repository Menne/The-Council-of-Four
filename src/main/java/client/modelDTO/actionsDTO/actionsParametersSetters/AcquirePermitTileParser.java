package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.view.ClientView;

public class AcquirePermitTileParser implements ActionParserVisitor {
	
	private AcquirePermitTileDTO selectedAction;
	
	public AcquirePermitTileParser(AcquirePermitTileDTO selectedAction) {
		this.selectedAction=selectedAction;
	}
	
	
	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok! you have chosen to acquire a permit tile.");
			
		if (!game.getClientPlayer().getHand().isEmpty()) {
			
			view.displayMessage("Now I need some other infos, like:");
			
			view.displayMessage("the name of the region in which you want to pick");
			this.selectedAction.setChosenRegion(view.askForRegionBoard());
	
			view.displayMessage("the number of permit tile you want to pick");
			this.selectedAction.setNumberOfPermitTile(view.askForNumberOfPermitTile
					(this.selectedAction.getChoosenRegion()));
			
			view.displayMessage("the colours of the cards in your hand you want to descard");
			this.selectedAction.setCardsToDescard(view.askForPoliticsCards());
			
			this.selectedAction.parametersSetted();
			
		}
		else 
			view.displayMessage("but it seems that you haven't any politics card in your hand! Select another action please");
	}

}
