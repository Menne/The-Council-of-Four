package client.actionDTO;

import client.ModelDTO.RegionDTO;

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
	
	
}
