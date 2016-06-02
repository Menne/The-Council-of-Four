package modelDTO.parser;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;

public class AcceptAnOfferParser implements ActionParserVisitor {

	private AcceptAnOfferDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public AcceptAnOfferParser(AcceptAnOfferDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.game=game;
	}
	
	@Override
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	@Override
	public ActionDTO setParameters(Parser parser) {
		
		if (!this.game.getMarket().getOffersList().isEmpty()) {
		
			this.game.notifyObserver(new ActionNotify("Do you want to buy one of these objects?"));
			this.game.notifyObserver(new ParametersNotify(parser.acceptableOffers(), this));
			this.selectedAction.setOffer(parser.OfferTranslator(currentParameter));
			
			this.selectedAction.parametersSetted();
			
		}
		else 
			this.game.notifyObserver(new ActionNotify
					("There is nothing to buy from other players"));
		
		return selectedAction;
	}

}
