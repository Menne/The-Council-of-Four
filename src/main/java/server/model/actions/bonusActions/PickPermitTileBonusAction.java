package server.model.actions.bonusActions;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.actions.Action;
import server.model.gameTable.RegionBoard;

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
		
		game.setState(game.getState().moveToNextTransition(game));
		return true;
	}

	@Override
	public ActionDTO map() {
		throw new IllegalStateException("PickPermitTileAction doesn't require mapping");
	}

}
