package modelDTO.parser;

import java.util.ArrayList;
import java.util.List;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.MakeAnOfferDTO;
import modelDTO.marketDTO.MarketableDTO;
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
	public ActionDTO setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify("Ok, you decided to sell something to the other players"));
		
		if (!(parser.acceptablePoliticsCards().isEmpty() || parser.acceptablePermitTiles().isEmpty()
				|| this.game.getCurrentPlayer().getAssistants()==0)) {
			
			this.game.notifyObserver(new ActionNotify("Which element do you want to offer?"));
			
			List<String> acceptableParameters=new ArrayList<String>();
			List<String> index1=new ArrayList<String>();
			List<String> index2=new ArrayList<String>();
			List<String> index3=new ArrayList<String>();
			
			for (int i=0; i<parser.acceptablePoliticsCards().size(); i++) {
				acceptableParameters.add(i + ": " + parser.acceptablePoliticsCards().get(i).toString());
				index1.add(""+i);
			}
			for (int i=acceptableParameters.size(); i<parser.acceptablePermitTiles().size(); i++) {
				acceptableParameters.add(i + ": " + parser.acceptablePermitTiles().get(i).toString());
				index2.add(""+i);
			}
			for (int i=acceptableParameters.size(); i<this.game.getCurrentPlayer().getAssistants(); i++) {
				acceptableParameters.add(i + ": Assistant");
				index3.add(""+i);
			}
			
			this.game.notifyObserver(new ParametersNotify(acceptableParameters, this));

			if (index1.contains(currentParameter))
				this.selectedAction.setOfferingObject((MarketableDTO) parser.politicsCardsTranslator(currentParameter));
			if (index1.contains(currentParameter))
				this.selectedAction.setOfferingObject(parser.permitTileTranslator(currentParameter));
			if (index1.contains(currentParameter))
				this.selectedAction.setOfferingObject(new AssistantDTO());
			
			this.selectedAction.setPrice(Integer.parseInt(currentParameter));
			
			return selectedAction;
		
		}
		
		else 
			this.game.notifyObserver(new ActionNotify
					("but it seems that you haven't anything to offer!"));
		
		return selectedAction;
	}

}
