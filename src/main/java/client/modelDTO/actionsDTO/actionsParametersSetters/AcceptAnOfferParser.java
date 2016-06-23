package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.view.ClientView;

public class AcceptAnOfferParser implements ActionParserVisitor {

	private AcceptAnOfferDTO selectedAction;
	
	public AcceptAnOfferParser(AcceptAnOfferDTO selectedAction) {
		this.selectedAction=selectedAction;
	}
	

	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		
		if (!game.getMarket().getOffersList().isEmpty()) {
		
			view.displayMessage("Do you want to buy one of these objects?");
			this.selectedAction.setOffer(view.askForAcceptingAnOffer());
			
			this.selectedAction.parametersSetted();
			
		}
		else 
			view.displayMessage("There is nothing to buy from other players");
		
		return selectedAction;
	}

}
