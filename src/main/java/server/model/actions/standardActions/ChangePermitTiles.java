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
import server.view.notifies.ErrorNotify;
import server.view.notifies.MessageNotify;
import server.view.notifies.PlayerNotify;

/**
 * It's the quick action "change permit tiles" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the region where to change the two uncovered permit tiles  
 * @author Luca
 *
 */
public class ChangePermitTiles extends QuickAction {
	
	private RegionBoard selectedRegion;
	private static final int necessaryAssistants=1;
	
	public void setSelectedRegion(RegionBoard selectedRegion) {
		this.selectedRegion = selectedRegion;
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
			game.notifyObserver(new ErrorNotify("It seems that you haven't enough assistants. Please choose another action", 
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		for(RegionBoard region : game.getGameTable().getRegionBoards())
			if(region.equals(selectedRegion))
				region.substitutePermitTiles();
		game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		
		game.notifyObserver(new PlayerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		
		this.notifyPlayers(game);
		this.nextState(game);
		
		return true;
	}

	
	private void notifyPlayers(Game game) {
		game.notifyObserver(new MessageNotify("Action completed succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageNotify(game.getCurrentPlayer().getName()
				+ " changed the permit tiles in " + this.selectedRegion.getName() + " region", otherPlayers));
	}
	
	@Override
	public ActionDTO map() {
		return new ChangePermitTilesDTO();
	}		

}