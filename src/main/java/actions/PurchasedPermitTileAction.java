package actions;

import bonus.Bonus;
import gameStuff.PermitTile;
import model.Game;

/**
 * It's the class that models the action associated to the choice of the PurchasedPermitTile.
 * @author Emanuele
 *
 */

public class PurchasedPermitTileAction extends Action {

	private PermitTile selectedPermitTile;
	
	/**
	 * Constructor of the PurchasedPermitTileAction
	 * @param game is the current game
	 * @param selectedPermitTile is the permit tile selected by the current player
	 */
	public PurchasedPermitTileAction(Game game, PermitTile selectedPermitTile){
		super(game);
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
