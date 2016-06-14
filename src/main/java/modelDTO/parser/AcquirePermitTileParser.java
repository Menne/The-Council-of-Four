package modelDTO.parser;

import java.util.Arrays;
import java.util.List;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;

public class AcquirePermitTileParser implements ActionParserVisitor {
	
	private AcquirePermitTileDTO selectedAction;
	private ClientView view;
	private GameDTO game;
	
	public AcquirePermitTileParser(AcquirePermitTileDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}
	
	
	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Ok! you have chosen to acquire a permit tile.");
			
		if (!this.game.getClientPlayer().getHand().isEmpty()) {
			
			this.view.displayMessage("Now I need some other infos, like:");
			
			this.view.displayMessage("the name of the region in which you want to pick");
			this.selectedAction.setChosenRegion(this.view.askForRegionBoard
					(this.game.getClientGameTable().getClientRegions()));
	
			this.view.displayMessage("the number of permit tile you want to pick");
			List<Integer> acceptableNumbersOfPermitTiles=Arrays.asList(0,1);
			this.selectedAction.setNumberOfPermitTile(this.view.askForNumberOfPermitTile
					(acceptableNumbersOfPermitTiles));
			
			this.view.displayMessage("the colours of the cards in your hand you want to descard");
			this.selectedAction.setCardsToDescard(this.view.askForPoliticsCards
					(this.game.getClientPlayer().getHand()));
			
			this.selectedAction.parametersSetted();
			
		}
		else 
			this.view.displayMessage("but it seems that you haven't any politics card in your hand! Select another action please");
			
		return this.selectedAction;
	}

}
