package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.ModelDTO.RegionDTO;
import client.parser.ActionParserVisitor;
import client.parser.ChangePermitTilesParser;

public class ChangePermitTilesDTO implements ActionDTO {

	private final RegionDTO selectedRegion;

	public ChangePermitTilesDTO(RegionDTO selectedRegion){
		this.selectedRegion=selectedRegion;
	}

	public RegionDTO getSelectedRegion() {
		return selectedRegion;
	}

	@Override
	public String toString() {
		return "q2: change the permit tiles of a region";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new ChangePermitTilesParser(this, game);
	}
	
	
}
