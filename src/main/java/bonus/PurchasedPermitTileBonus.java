package bonus;

import gameStuff.PermitTile;
import model.Game;

/**
 * Obtain the bonuses of a previously purchased permit tile, even the covered ones
 * @author Emanuele
 *
 */

public class PurchasedPermitTileBonus implements Bonus {
	
	
	private PermitTile chosenPermitTile;

	/**
	 * Constructor of PurchasedPermitTileBonus
	 * @param chosenPermitTile is the permit tile the player has chosen
	 */
	public PurchasedPermitTileBonus(PermitTile chosenPermitTile) {
		this.chosenPermitTile=chosenPermitTile;
	}

	/**
	 * For each bonus in the chosen permit tile
	 * @param currentPlayer is the player who plays the turn
	 */
	public void assignBonus(Game game) {
		for (Bonus bonusToAssign : chosenPermitTile.getBonus())
			bonusToAssign.assignBonus(game);
	}

}