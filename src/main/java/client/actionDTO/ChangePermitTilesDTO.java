package client.actionDTO;

import client.ModelDTO.RegionDTO;

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
	
	
}
