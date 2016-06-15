package client.modelDTO.actionsDTO.actionsParametersSetters;

import java.util.ArrayList;
import java.util.List;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.view.ClientView;

public class PurchasedPermitTileBonusParser implements ActionParserVisitor {

	private PurchasedPermitTileActionDTO selectedAction;
	private ClientView view;
	private GameDTO game;

	public PurchasedPermitTileBonusParser(PurchasedPermitTileActionDTO selectedAction, ClientView view, GameDTO game) {
		this.selectedAction=selectedAction;
		this.view=view;
		this.game=game;
	}


	@Override
	public ActionDTO setParameters() {
		this.view.displayMessage("Permit tile bonus earned! You have the possibility to choose from your permit tiles,"
						+ "covered or not, and get the bonuses associated to that");
		
		List<PermitTileDTO> availablePermitTiles=new ArrayList<>();
		availablePermitTiles.addAll(this.game.getClientPlayer().getAvailablePermitTiles());
		availablePermitTiles.addAll(this.game.getClientPlayer().getCoveredPermitTiles());
		
		if (!availablePermitTiles.isEmpty()) {
			
			this.selectedAction.setPermitTile(this.view.askForPermitTile(availablePermitTiles));
			
			this.selectedAction.parametersSetted();
		}
		
		else 
			this.view.displayMessage("But it seems that you haven't any permit tiles in your hand!");
		
		return this.selectedAction;
		
	}

}
