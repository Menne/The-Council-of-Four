package modelDTO.parser;

import java.util.ArrayList;
import java.util.List;

import client.view.notifies.ActionNotify;
import client.view.notifies.MakeAnOfferNotify;
import client.view.notifies.OfferPriceNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.playerDTO.AssistantDTO;

public class MakeAnOfferParser implements ActionParserVisitor {

	private MakeAnOfferDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public MakeAnOfferParser(MakeAnOfferDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.game=game;
	}

	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}

	@Override
	public ActionDTO setParameters(Parser parser) throws IllegalArgumentException {
		this.game.notifyObserver(new ActionNotify("Ok, you decided to sell something to the other players"));
		
		if (!(parser.acceptablePoliticsCards().isEmpty() && parser.acceptablePermitTiles().isEmpty()
				&& this.game.getClientPlayer().getAssistants()==0)) {
			
			this.game.notifyObserver(new ActionNotify("Which element do you want to offer?"));
			
			List<String> acceptableParameters=new ArrayList<String>();
			List<String> index1=new ArrayList<String>();
			List<String> index2=new ArrayList<String>();
			List<String> index3=new ArrayList<String>();
			
			for (int i=0; i<parser.acceptablePoliticsCards().size(); i++) {
				acceptableParameters.add(i + ": " + parser.acceptablePoliticsCards().get(i).toString());
				index1.add(""+i);
			}
			int sizeCounter=acceptableParameters.size();
			for (int i=sizeCounter; i<sizeCounter+parser.acceptablePermitTiles().size(); i++) {
				acceptableParameters.add(i + ": " + parser.acceptablePermitTiles().get(i).toString());
				index2.add(""+i);
			}
			sizeCounter=acceptableParameters.size();
			for (int i=sizeCounter; i<sizeCounter+this.game.getClientPlayer().getAssistants(); i++) {
				acceptableParameters.add(i + ": Assistant");
				index3.add(""+i);
			}
			
			this.game.notifyObserver(new MakeAnOfferNotify(acceptableParameters, this));
			
			if (index1.contains(currentParameter)) {
				this.selectedAction.setOfferingObject(this.game.getClientPlayer().getHand().get(Integer.parseInt(currentParameter)));
		//		if (!(this.selectedAction.getOfferingObject() instanceof CardColourDTO));
		//			throw new IllegalArgumentException("The offering object must be a CardColourDTO");
			}
			if (index2.contains(currentParameter)) {
				this.selectedAction.setOfferingObject(parser.permitTileTranslator(acceptableParameters.get(Integer.parseInt(currentParameter))));
		//		if (!(this.selectedAction.getOfferingObject() instanceof PermitTileDTO));
		//			throw new IllegalArgumentException("The offering object must be a PermitTileDTO");
			}
			if (index3.contains(currentParameter)) {
				this.selectedAction.setOfferingObject(new AssistantDTO());
		//		if (!(this.selectedAction.getOfferingObject() instanceof AssistantDTO));
		//			throw new IllegalArgumentException("The offering object must be an AssistantDTO");
			}
			
			
			List<String> acceptablePrice=new ArrayList<String>();
			for (int i=0; i<=100; i++)
				acceptablePrice.add(""+i);
			this.game.notifyObserver(new OfferPriceNotify(acceptablePrice, this));
			this.selectedAction.setPrice(Integer.parseInt(currentParameter));
			
			this.selectedAction.parametersSetted();
			
			return selectedAction;
		
		}
		
		else 
			this.game.notifyObserver(new ActionNotify
					("but it seems that you haven't anything to offer!"));
		
		return selectedAction;
	}

}
