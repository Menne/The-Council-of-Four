package client.actionDTO;

import client.ModelDTO.CityDTO;
import client.ModelDTO.GameDTO;
import client.ModelDTO.PermitTileDTO;
import client.parser.ActionParserVisitor;
import client.parser.BuildByPermitTileParser;
import model.Game;
import model.actions.Action;
import model.actions.standardAction.BuildByPermitTile;
import model.gameTable.City;
import model.gameTable.PermitTile;

public class BuildByPermitTileDTO implements ActionDTO{
	
	private  PermitTileDTO selectedPermitTile;
	private  CityDTO selectedCity;

	public PermitTileDTO getSelectedPermitTile() {
		return selectedPermitTile;
	}

	public CityDTO getSelectedCity() {
		return selectedCity;
	}

	public void setSelectedPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile = selectedPermitTile;
	}

	public void setSelectedCity(CityDTO selectedCity) {
		this.selectedCity = selectedCity;
	}
	
	@Override
	public Action map(Game game) {
		BuildByPermitTile action =new BuildByPermitTile();
		
		for(City city : game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getName().equals(this.selectedCity.getName()))
				action.setSelectedCity(city);
		
		for(PermitTile permitTile : game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
			if(permitTile.getBonus().equals(this.selectedPermitTile.getBonuses()));
			//TODO
		return action;
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
