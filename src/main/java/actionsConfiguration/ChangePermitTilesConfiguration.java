package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.ChangePermitTiles;
import model.gameTable.RegionBoard;
import packdaeliminare.AskParameterPack;

public class ChangePermitTilesConfiguration extends ActionConfiguration{
	
	private ChangePermitTiles action;
	
	public ChangePermitTilesConfiguration(Game game, ChangePermitTiles action){
		super(game);
		this.action=action;
	}

	@Override
	public AskParameterPack createAskParameterPack() {
		
		List<String> parametersName=new ArrayList<String>();
		parametersName.add("Region where you want to change the permit tiles");
		
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		
		List<String> regionNames=new ArrayList<String>();
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			regionNames.add(region.getName());
		acceptableStrings.add(regionNames);
		
		return new AskParameterPack(parametersName, acceptableStrings);
	}

	@Override
	public void setParameters(List<String> stringParameters) {
		this.action.setSelectedRegion(regionTranslator
				(stringParameters.remove(0)));
	}
	
	private RegionBoard regionTranslator(String regionToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a region name");
	}

}
