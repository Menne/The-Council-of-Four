package modelDTO.parser;

import client.view.ClientView;
import client.view.notifies.AcceptAnOfferNotify;
import client.view.notifies.ActionNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;

public class AcceptAnOfferParser implements ActionParserVisitor {

	private AcceptAnOfferDTO selectedAction;
	private Object currentParameter;
	private ClientView view;
	private GameDTO game;
	
	public AcceptAnOfferParser(AcceptAnOfferDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}
	
	@Override
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	@Override
	public ActionDTO setParameters() {
		
		if (!this.game.getMarket().getOffersList().isEmpty()) {
		
			this.view.displayMessage("Do you want to buy one of these objects?");
			this.selectedAction.setOffer(this.view.askForAcceptingAnOffer
					(this.game.getMarket().getOffersList()));
			
			this.selectedAction.parametersSetted();
			
		}
		else 
			this.view.displayMessage("There is nothing to buy from other players");
		
		return selectedAction;
	}

}
