package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import server.model.Game;
import server.model.actions.QuickAction;
import server.model.gameTable.RegionBoard;
import server.model.player.Player;
import server.serverNotifies.ErrorServerNotify;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * It's the quick action "change permit tiles".
 * @author cg31
 *
 */
public class ChangePermitTiles implements QuickAction {
	
	private RegionBoard selectedRegion;
	private static final int necessaryAssistants=1;
	
	public void setSelectedRegion(RegionBoard selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	
	
	public RegionBoard getSelectedRegion() {
		return selectedRegion;
	}



	private boolean checkAssistant(Game game){
		return game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants;
	}
	
	
	/**
	 * Changes the two uncovered permit tiles of the selected region
	 * and decrements the necessary assistant to the current player.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException{
		
		if(this.selectedRegion==null)
			throw new NullPointerException("Paramters not setted");
		
		if(!this.checkAssistant(game)){
			game.notifyObserver(new ErrorServerNotify("It seems that you haven't enough assistants. Please choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		for(RegionBoard region : game.getGameTable().getRegionBoards())
			if(region.equals(selectedRegion))
				region.substitutePermitTiles();
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		this.updateClients(game);
		this.nextState(game);
		
		return true;
	}

	
	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableServerNotify(game, new ArrayList<Player>(game.getAllPlayers()), false));
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageServerNotify("Permit tiles changed successfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getAllPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ " changed the permit tiles in " + this.selectedRegion.getName() + " region", otherPlayers));
	}
	
	@Override
	public ActionDTO map() {
		return new ChangePermitTilesDTO();
	}		

}