package modelDTO.actionsDTO.bonusActions;

import java.util.HashSet;
import java.util.Set;

import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ActionWithParameters;
import modelDTO.gameTableDTO.CityDTO;
import modelDTO.gameTableDTO.PermitTileDTO;
import modelDTO.parser.ActionParserVisitor;
import modelDTO.parser.PurchasedPermitTileBonusParser;
import server.model.Game;
import server.model.actions.Action;
import server.model.actions.bonusActions.PurchasedPermitTileAction;
import server.model.gameTable.City;
import server.model.gameTable.PermitTile;

public class PurchasedPermitTileActionDTO implements ActionDTO, ActionWithParameters {

	/**
	 * 
	 */
	private static final long serialVersionUID = 893810260862447362L;
	private PermitTileDTO selectedPermitTile;
	private boolean parametersSetted=false;
	
	public void setPermitTile(PermitTileDTO selectedPermitTile) {
		this.selectedPermitTile=selectedPermitTile;
	}

	@Override
	public boolean checkIfParametersSetted() {
		return this.parametersSetted;
	}

	@Override
	public void parametersSetted() {
		this.parametersSetted=true;
	}

	
	@Override
	public ActionParserVisitor setParser(GameDTO game) {
		return new PurchasedPermitTileBonusParser(this, game);
	}

	@Override
	public Action map(Game game) {
		
		PurchasedPermitTileAction action=new PurchasedPermitTileAction();
		
		for (PermitTile permitTile : game.getCurrentPlayer().getPlayersPermitTilesTurnedUp())
			if(permitTile.getBonus().equals(this.selectedPermitTile.getBonuses())&&
					checkBuildableCities(permitTile.getBuildableCities()))
				action.setSelectedPermitTile(permitTile);
		
		return action;
	}
	
	private boolean checkBuildableCities(Set<City> realBuildableCities){
		Set<String> realBuildableCitiesString =new HashSet<>();
		Set<String> buildableCitiesDTOString = new HashSet<>();
		for(City city : realBuildableCities)
			realBuildableCitiesString.add(city.getName());
		for(CityDTO cityDTO : this.selectedPermitTile.getBuildablecities())
			buildableCitiesDTOString.add(cityDTO.getName());
		return realBuildableCitiesString.equals(buildableCitiesDTOString);
	}
}
