package modelDTO.actionsDTO;

import modelDTO.GameDTO;
import modelDTO.gameTableDTO.RegionDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.ChangePermitTilesParser;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.gameTable.RegionBoard;

public class ChangePermitTilesDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3441345313935469077L;
	private RegionDTO selectedRegion;


	public RegionDTO getSelectedRegion() {
		return selectedRegion;
	}

	public void setSelectedRegion(RegionDTO selectedRegion) {
		this.selectedRegion = selectedRegion;
	}

	@Override
	public String toString() {
		return "q2: change the permit tiles of a region";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ChangePermitTilesParser(this, game);
	}

	@Override
	public Action map(Game game) {
		ChangePermitTiles action=new ChangePermitTiles();
		
		for(RegionBoard region : game.getGameTable().getRegionBoards())
			if(region.getName().equals(this.selectedRegion.getName()))
				action.setSelectedRegion(region);
		return action;
	}
	
	
}
