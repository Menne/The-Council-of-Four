package client.actionDTO;

import client.ModelDTO.CityDTO;
import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import client.parser.BuildByPermitTileParser;
import model.gameTable.PermitTile;

public class BuildByPermitTileDTO implements ActionDTO{
	
	private final PermitTile selectedPermitTile;
	private final CityDTO selectedCity;
	
	public BuildByPermitTileDTO(PermitTile selectedPermitTile, CityDTO selectedCity){
		this.selectedCity=selectedCity;
		this.selectedPermitTile=selectedPermitTile;
	}

	public PermitTile getSelectedPermitTile() {
		return selectedPermitTile;
	}

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	@Override
	public String toString() {
		return "m3: build an emporium using a permit tile";
	}

	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new BuildByPermitTileParser(this, game);
	}
	
	
}
