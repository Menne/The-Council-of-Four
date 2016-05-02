package actions;

import gameStuff.RegionBoard;
import model.Game;

/**
 * It's the quick action "change permit tiles" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the region where to change the two uncovered permit tiles  
 * @author Luca
 *
 */
public class ChangePermitTiles extends QuickAction {
	
	private final RegionBoard selectedRegion;
	private static final int necessaryAssistants=1;
	
	public  ChangePermitTiles(Game game, RegionBoard selectedRegion){
		super(game);
		this.selectedRegion=selectedRegion;
	}
	
	private boolean checkAssistant(){
		return this.game.getCurrentPlayer().getAssistants()>=necessaryAssistants;
	}
	
	/**
	 * Changes the two uncovered permit tiles of the selected region
	 * and decrements the necessary assistant to the current player.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() {
		if(!this.checkAssistant())
			return false;
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(region.equals(selectedRegion))
				region.substituteBothPermitTiles();
		this.game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		return true;
	}
				
}