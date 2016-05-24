package client.actionDTO;

import client.ModelDTO.CityDTO;
import model.gameTable.PermitTile;

public class BuildByPermitTileDTO implements ActionDTO{
	
	private  PermitTile selectedPermitTile;
	private  CityDTO selectedCity;

	public PermitTile getSelectedPermitTile() {
		return selectedPermitTile;
	}

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedPermitTile(PermitTile selectedPermitTile) {
		this.selectedPermitTile = selectedPermitTile;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}

	@Override
	public String toString() {
		return "m3: build an emporium using a permit tile";
	}
	
	
}
