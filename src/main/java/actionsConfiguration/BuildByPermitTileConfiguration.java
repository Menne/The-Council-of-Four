package actionsConfiguration;

import java.util.List;

import actions.BuildByPermitTile;
import controller.AskParameterPack;
import gameStuff.City;
import gameStuff.PermitTile;
import gameStuff.RegionBoard;
import model.Game;

public class BuildByPermitTileConfiguration extends ActionConfiguration{
	
	private BuildByPermitTile action;
	
	
	public BuildByPermitTileConfiguration(Game game, BuildByPermitTile action){
		super(game);
		this.action=action;
	}

	@Override
	public AskParameterPack createAskParameterPack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameters(List<String> stringParameters) {
		this.action.setSelectedCity(cityTranslator
				(stringParameters.remove(0)));
		this.action.setSelectedPermitTile(permitTileTranslator
				(stringParameters.remove(0)));
		
	}
	
	private City cityTranslator(String cityToTranslate) {
		for (RegionBoard regionBoard : this.game.getGameTable().getRegionBoards())
			for (City cityTranslated : regionBoard.getRegionCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}
	
	private PermitTile permitTileTranslator(String permitTileToTranslate) {
		int numberOfPermitTile=Integer.parseInt(permitTileToTranslate);
		PermitTile permitTileTranslated=this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(numberOfPermitTile);
		return permitTileTranslated;
	}

}
