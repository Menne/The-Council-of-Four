package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import controller.AskParameterPack;
import model.Game;
import model.actions.BuildByPermitTile;
import model.gameTable.City;
import model.gameTable.Emporium;
import model.gameTable.PermitTile;
import model.gameTable.RegionBoard;

public class BuildByPermitTileConfiguration extends ActionConfiguration{
	
	private BuildByPermitTile action;
	
	
	public BuildByPermitTileConfiguration(Game game, BuildByPermitTile action){
		super(game);
		this.action=action;
	}

	@Override
	public AskParameterPack createAskParameterPack() {
		
		List<String> parametersName=new ArrayList<String>();
		parametersName.add("City where you want to build an emporium");
		parametersName.add("Permit tile you want to use to acquire");
		
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		
		List<String> cityNames=new ArrayList<String>();
		for(City city : this.game.getGameTable().getMap().getGameMap().vertexSet())
			if(city.getCityEmporiums().isEmpty())
				cityNames.add(city.getName());
			else
				for (Emporium emporium : city.getCityEmporiums())
					if (!emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
						cityNames.add(city.getName());
		acceptableStrings.add(cityNames);
	
		List<String> permitTilesNumbers=new ArrayList<String>();
		int maxNumberOfCards=this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().size();
		for(Integer i=0; i<maxNumberOfCards; i++)
			permitTilesNumbers.add(i.toString());
		acceptableStrings.add(permitTilesNumbers);
	
		return new AskParameterPack(parametersName, acceptableStrings);
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
