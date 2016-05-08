package actions;

import controller.AskParameterPack;
import gameStuff.RegionBoard;
import model.Game;

/**
 * It's the quick action "change permit tiles" it operates on the 
 * protected attribute game through the method executeAction.
 * The controller will pass to the constructor the region where to change the two uncovered permit tiles  
 * @author Luca
 *
 */
public class ChangePermitTiles extends QuickAction implements NeedParameters{
	
	private RegionBoard selectedRegion;
	private static final int necessaryAssistants=1;
	
	public  ChangePermitTiles(Game game){
		super(game);
	}
	
	
	public void setSelectedRegion(RegionBoard selectedRegion) {
		this.selectedRegion = selectedRegion;
	}
	
	
	private boolean checkAssistant(){
		return this.game.getCurrentPlayer().getAssistants()>=necessaryAssistants;
	}
	
	
	/**
	 * Changes the two uncovered permit tiles of the selected region
	 * and decrements the necessary assistant to the current player.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() throws NullPointerException{
		
		if(this.selectedRegion==null)
			throw new NullPointerException("Paramters not setted");
		if(!this.checkAssistant())
			return false;
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(region.equals(selectedRegion))
				region.substitutePermitTiles();
		this.game.getCurrentPlayer().decrementAssistants(necessaryAssistants);
		this.decrementAction();
		return true;
	}


				
}