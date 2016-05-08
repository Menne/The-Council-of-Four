package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.ChangePermitTiles;
import controller.AskParameterPack;
import gameStuff.RegionBoard;
import model.Game;

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
		int numberOfRegion=Integer.parseInt(regionToTranslate);
		RegionBoard regionTranslated=this.game.getGameTable().getRegionBoards().get(numberOfRegion);
		return regionTranslated;
	}

}
