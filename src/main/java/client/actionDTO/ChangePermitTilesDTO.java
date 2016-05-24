package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.ModelDTO.RegionDTO;
import client.parser.ActionParserVisitor;
import client.parser.ChangePermitTilesParser;
import model.Game;
import model.actions.Action;

public class ChangePermitTilesDTO implements ActionDTO {

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
		// TODO Auto-generated method stub
		return null;
	}
	
	
}
