package server.model.actions.bonusActions;

import java.util.Arrays;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.bonuses.Bonus;
import server.model.gameTable.PermitTile;
import server.serverNotifies.PlayerServerNotify;

/**
 * This class modelizes the action associated to the bonus of a PurchasedPermitTileBonus
 * @author cg31
 *
 */
public class PurchasedPermitTileAction implements Action {

	private PermitTile selectedPermitTile;

	public void setSelectedPermitTile(PermitTile selectedPermitTile) {
		this.selectedPermitTile=selectedPermitTile;
	}
	
	
	public PermitTile getSelectedPermitTile() {
		return selectedPermitTile;
	}


	/**
	 * Assigns the bonuses of the permit tile selected to the current player
	 * @return true, if the bonus assignment went well, false otherwise
	 */
	@Override
	public boolean executeAction(Game game) {
		if (this.selectedPermitTile==null) {
			game.setState(game.getState().moveToNextTransition(game));
			return false;
		}
			
		for (Bonus bonusToAssign : this.selectedPermitTile.getBonuses())
			bonusToAssign.assignBonus(game);
		
		this.notifyPlayers(game);
		game.setState(game.getState().moveToNextTransition(game));
		game.getState().updateClients(game);
		
		return true;
	}

	
	@Override
	public void notifyPlayers(Game game) {
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
	}

	@Override
	public ActionDTO map() {
		return new PurchasedPermitTileActionDTO();
	}
	
}
