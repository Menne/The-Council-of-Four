package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.marketDTO.MarketableDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.view.ClientView;

/**
 * This class provides the logic to set the needed parameters of a MakeAnOfferDTO
 * @author cg31
 *
 */
public class MakeAnOfferParser implements ActionParserVisitor {

	private MakeAnOfferDTO selectedAction;
	private boolean otherSelling;
	
	/**
	 * Constructor of MakeAnOfferDTO
	 * @param selectedAction is the action selected by the user
	 */
	public MakeAnOfferParser(MakeAnOfferDTO selectedAction) {
		this.selectedAction=selectedAction;
		this.otherSelling=true;
	}

	@Override
	public void setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Ok, you decided to sell something to the other players");
		List<MarketableDTO> acceptableObjectsToOffer=new ArrayList<>();
		acceptableObjectsToOffer.addAll(game.getClientPlayer().getHand());
		acceptableObjectsToOffer.addAll(game.getClientPlayer().getAvailablePermitTiles());
		acceptableObjectsToOffer.addAll(game.getClientPlayer().getAssistants());
		
		while (!acceptableObjectsToOffer.isEmpty() && this.otherSelling) {
			view.displayMessage("Which element do you want to offer?");
			
			OfferDTO offerDTO=new OfferDTO();
			offerDTO.setOfferingPlayer(game.getClientPlayer().getName());
			
			offerDTO.setOfferedObjectDTO(view.askForMakingAnOffer(acceptableObjectsToOffer));
			acceptableObjectsToOffer.remove(offerDTO.getOfferedObjectDTO());
			
			view.displayMessage("What's the price of the element you selected?");
			offerDTO.setPrice(view.askForPrice());
			
			this.selectedAction.addOfferToList(offerDTO);
			
			view.displayMessage("Do you want make another offer?");
			this.otherSelling=view.askForOtherSelling();
		
			this.selectedAction.parametersSetted();
		}

		if (this.otherSelling && acceptableObjectsToOffer.isEmpty())
			view.displayMessage("but it seems that you haven't anything to offer!");
	}
	
}
