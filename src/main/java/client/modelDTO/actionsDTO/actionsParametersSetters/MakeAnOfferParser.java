package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.view.ClientView;

public class MakeAnOfferParser implements ActionParserVisitor {

	private MakeAnOfferDTO selectedAction;
	private ClientView view;
	private GameDTO game;
	private boolean otherSelling;
	
	public MakeAnOfferParser(MakeAnOfferDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
		this.otherSelling=true;
	}

	
	public void setOtherSelling(boolean otherSelling) {
		this.otherSelling=otherSelling;
	}

	@Override
	public ActionDTO setParameters() throws IllegalArgumentException {
		this.view.displayMessage("Ok, you decided to sell something to the other players");
		
		List<MarketableDTO> acceptableObjectsToOffer=new ArrayList<>();
		acceptableObjectsToOffer.addAll(this.game.getClientPlayer().getHand());
		acceptableObjectsToOffer.addAll(this.game.getClientPlayer().getAvailablePermitTiles());
		acceptableObjectsToOffer.addAll(this.game.getClientPlayer().getAssistants());
		
		while (!acceptableObjectsToOffer.isEmpty() && this.otherSelling) {
			this.view.displayMessage("Which element do you want to offer?");
			
			OfferDTO offerDTO=new OfferDTO();
			offerDTO.setOfferingPlayer(this.game.getClientPlayer().getName());
			
			offerDTO.setOfferedObjectDTO(this.view.askForMakingAnOffer(acceptableObjectsToOffer));
			acceptableObjectsToOffer.remove(offerDTO.getOfferedObjectDTO());
			
			this.view.displayMessage("What's the price of the element you selected?");
			offerDTO.setPrice(this.view.askForPrice());
			
			this.selectedAction.addOfferToList(offerDTO);
			
			this.view.displayMessage("Do you want to sell more? (yes/no)");
			this.otherSelling=this.view.askForOtherSelling();
		
			this.selectedAction.parametersSetted();
		}

		if (this.otherSelling && acceptableObjectsToOffer.isEmpty())
			this.view.displayMessage("but it seems that you haven't anything to offer!");
		
		return selectedAction;
	}
	
}
