package model.actions;

import model.Game;
import model.bonus.Bonus;
import model.gameTable.PermitTile;

/**
 * It's the class that models the action associated to the choice of the PurchasedPermitTile.
 * @author Emanuele
 *
 */

public class PurchasedPermitTileAction implements Action {

	protected final Game game;
	private PermitTile selectedPermitTile;
	
	/**
	 * Constructor of the PurchasedPermitTileAction
	 * @param game is the current game
	 * @param selectedPermitTile is the permit tile selected by the current player
	 */
	public PurchasedPermitTileAction(Game game, PermitTile selectedPermitTile){
		this.game=game;
		this.selectedPermitTile=selectedPermitTile;
	}
	
	/**
	 * for each bonus present in the permit tile, assigns it to the current player
	 */
	public boolean executeAction() {
		for (Bonus bonusToAssign : this.selectedPermitTile.getBonus())
			bonusToAssign.assignBonus(this.game);
		return true;
	}
	
}
