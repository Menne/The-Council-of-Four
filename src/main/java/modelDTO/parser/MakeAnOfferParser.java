package modelDTO.parser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.view.notifies.ActionNotify;
import client.view.notifies.MakeAnOfferNotify;
import client.view.notifies.OfferPriceNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import modelDTO.gameTableDTO.CardColourDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.marketDTO.OfferDTO;
import modelDTO.playerDTO.AssistantDTO;

public class MakeAnOfferParser implements ActionParserVisitor {

	private MakeAnOfferDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	private boolean otherSelling;
	
	public MakeAnOfferParser(MakeAnOfferDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.game=game;
		this.otherSelling=true;
	}

	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	
	public void setOtherSelling(boolean otherSelling) {
		this.otherSelling=otherSelling;
	}

	@Override
	public ActionDTO setParameters(Parser parser) throws IllegalArgumentException {
		this.game.notifyObserver(new ActionNotify("Ok, you decided to sell something to the other players"));
			
		List<String> acceptableParameters=new ArrayList<String>();
		List<String> index1=new ArrayList<String>();
		List<String> index2=new ArrayList<String>();
		List<String> index3=new ArrayList<String>();
		
		for (int i=0; i<parser.acceptablePoliticsCards().size(); i++) {
			acceptableParameters.add(parser.acceptablePoliticsCards().get(i).toString());
			index1.add(""+i);
		}
		int sizeCounter=acceptableParameters.size();
		for (int i=sizeCounter; i<sizeCounter+parser.acceptablePermitTiles().size(); i++) {
			acceptableParameters.add(parser.acceptablePermitTiles().get(i).toString());
			index2.add(""+i);
		}
		sizeCounter=acceptableParameters.size();
		for (int i=sizeCounter; i<sizeCounter+this.game.getClientPlayer().getAssistants(); i++) {
			acceptableParameters.add("Assistant");
			index3.add(""+i);
		}
		
		while (this.otherSelling && !acceptableParameters.isEmpty()) {
				
				OfferDTO offerDTO=new OfferDTO();
				offerDTO.setOfferingPlayer(this.game.getClientPlayer().getName());
					
				this.game.notifyObserver(new ActionNotify("Which element do you want to offer?"));
				this.game.notifyObserver(new MakeAnOfferNotify(acceptableParameters, this));
				
				
				if (index1.contains(currentParameter)) {
					index1.remove(Integer.parseInt(currentParameter)-1);
					offerDTO.setOfferedObjectDTO(this.game.getClientPlayer().getHand().get(Integer.parseInt(currentParameter)-1));
					if (!(offerDTO.getOfferedObjectDTO() instanceof CardColourDTO))
						throw new IllegalArgumentException("The offering object must be a CardColourDTO");
				}
				if (index2.contains(currentParameter)) {
					index2.remove(Integer.parseInt(currentParameter)-1);
					offerDTO.setOfferedObjectDTO(parser.permitTileTranslator(acceptableParameters.get(Integer.parseInt(currentParameter)-1)));
					if (!(offerDTO.getOfferedObjectDTO() instanceof PermitTileDTO))
						throw new IllegalArgumentException("The offering object must be a PermitTileDTO");
				}
				if (index3.contains(currentParameter)) {
					index3.remove(Integer.parseInt(currentParameter)-1);
					offerDTO.setOfferedObjectDTO(new AssistantDTO());
					if (!(offerDTO.getOfferedObjectDTO() instanceof AssistantDTO))
						throw new IllegalArgumentException("The offering object must be an AssistantDTO");
				}
				acceptableParameters.remove(Integer.parseInt(currentParameter)-1);
				
				List<String> acceptablePrice=new ArrayList<String>();
				for (int i=0; i<=100; i++)
					acceptablePrice.add(""+i);
				this.game.notifyObserver(new OfferPriceNotify(acceptablePrice, this));
				offerDTO.setPrice(Integer.parseInt(currentParameter));
				
				this.selectedAction.addOfferToList(offerDTO);
				
				List<String> askOtherSelling=Arrays.asList("yes","no");
				this.game.notifyObserver(new ActionNotify("Do you want to sell more?"));
				this.game.notifyObserver(new ParametersNotify(askOtherSelling, this));
				System.out.println(currentParameter);
				if (this.currentParameter.equals(askOtherSelling.get(1)))
					this.otherSelling=false;
			
				this.selectedAction.parametersSetted();

		}

		if (this.otherSelling && parser.acceptablePoliticsCards().isEmpty() && parser.acceptablePermitTiles().isEmpty()
				&& this.game.getClientPlayer().getAssistants()==0)
			this.game.notifyObserver(new ActionNotify
					("but it seems that you haven't anything to offer!"));
		
		return selectedAction;
	}
	
}
