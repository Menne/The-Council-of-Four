package server.model.actions.bonusActions;

import java.util.Arrays;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PickPermitTileActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.gameTable.RegionBoard;
import server.view.notifies.MessageNotify;
import server.view.notifies.PlayerNotify;

public class PickPermitTileBonusAction implements Action {

	private Integer numberOfPermitTile;
	private RegionBoard selectedRegion;

	public void setNumberOfPermitTile(Integer numberOfPermitTile) {
		this.numberOfPermitTile=numberOfPermitTile;
	}

	public void setChosenRegion(RegionBoard selectedRegion) {
		this.selectedRegion=selectedRegion;
	}

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
	
	
	private void notifyPlayers(Game game) {
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageNotify("Bonus earned successfully!", 
				Arrays.asList(game.getCurrentPlayer())));
	}

	@Override
	public ActionDTO map() {
		return new PickPermitTileActionDTO();
	}

}
