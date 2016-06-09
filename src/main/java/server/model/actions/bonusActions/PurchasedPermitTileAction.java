package server.model.actions.bonusActions;

import modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonus.Bonus;
import server.model.gameTable.PermitTile;

/**
 * It's the class that models the action associated to the choice of the PurchasedPermitTile.
 * @author Emanuele
 *
 */

public class PurchasedPermitTileAction implements Action {

	private PermitTile selectedPermitTile;

	public void setSelectedPermitTile(PermitTile selectedPermitTile) {
		this.selectedPermitTile=selectedPermitTile;
	}
	
	
	@Override
	public boolean executeAction(Game game) {
		for (Bonus bonusToAssign : this.selectedPermitTile.getBonus())
			bonusToAssign.assignBonus(game);
		
		game.setState(game.getState().moveToNextTransition(game));
		return true;
	}


	@Override
	public ActionDTO map() {
		throw new IllegalStateException("PurchasePermitTileActions doesn't require mapping");
	}
	
}
