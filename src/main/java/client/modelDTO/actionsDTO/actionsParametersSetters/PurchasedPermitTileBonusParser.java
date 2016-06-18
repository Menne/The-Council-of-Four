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

	public PurchasedPermitTileBonusParser(PurchasedPermitTileActionDTO selectedAction) {
		this.selectedAction=selectedAction;
	}


	@Override
	public ActionDTO setParameters(ClientView view, GameDTO game) {
		view.displayMessage("Permit tile bonus earned! You have the possibility to choose from your permit tiles,"
						+ "covered or not, and get the bonuses associated to that");
		
		List<PermitTileDTO> availablePermitTiles=new ArrayList<>();
		availablePermitTiles.addAll(game.getClientPlayer().getAvailablePermitTiles());
		availablePermitTiles.addAll(game.getClientPlayer().getCoveredPermitTiles());
		
		if (!availablePermitTiles.isEmpty()) {
			
			this.selectedAction.setPermitTile(view.askForPermitTile(availablePermitTiles));
			
			this.selectedAction.parametersSetted();
		}
		
		else 
			view.displayMessage("But it seems that you haven't any permit tiles in your hand!");
		
		return this.selectedAction;
		
	}

}
