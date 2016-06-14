package modelDTO.parser;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;

public class AcceptAnOfferParser implements ActionParserVisitor {

	private AcceptAnOfferDTO selectedAction;
	private ClientView view;
	private GameDTO game;
	
	public AcceptAnOfferParser(AcceptAnOfferDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
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
