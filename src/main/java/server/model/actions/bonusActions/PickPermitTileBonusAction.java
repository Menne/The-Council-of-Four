package server.model.actions.bonusActions;

import java.util.Arrays;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PickPermitTileActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.gameTable.RegionBoard;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * This class modelizes the action associated to the bonus of a PickPermitTileBonus
 * @author cg31
 *
 */
public class PickPermitTileBonusAction implements Action {

	private Integer numberOfPermitTile;
	private RegionBoard selectedRegion;

	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile=numberOfPermitTile;
	}

	public void setChosenRegion(RegionBoard selectedRegion) {
		this.selectedRegion=selectedRegion;
	}

	/**
	 * Assigns the bonuses of the permit tile selected
	 * @return true, if the bonus assignment went well, false otherwise
	 */
	@Override
	public boolean executeAction(Game game) {
		if (this.numberOfPermitTile==null||
				this.selectedRegion==null)
			throw new NullPointerException("Paramters not setted");
		
		game.getCurrentPlayer().addTile(this.selectedRegion.pickUncoveredPermitTile(this.numberOfPermitTile));
		this.selectedRegion.uncoverPermitTiles();
		
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
		return new PickPermitTileActionDTO();
	}

}
