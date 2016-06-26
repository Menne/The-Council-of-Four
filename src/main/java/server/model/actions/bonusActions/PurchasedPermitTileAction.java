package server.model.actions.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
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
		if(this.selectedPermitTile==null)
			throw new NullPointerException("you have to select a permit tile to do this action");
		for (Bonus bonusToAssign : this.selectedPermitTile.getBonuses())
			bonusToAssign.assignBonus(game);
		
		game.setState(game.getState().moveToNextTransition(game));
		return true;
	}


	@Override
	public ActionDTO map() {
		throw new IllegalStateException("PurchasePermitTileAction doesn't require mapping");
	}
	
}
