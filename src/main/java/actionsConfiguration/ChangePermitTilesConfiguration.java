package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.ChangePermitTiles;
import controller.AskParameterPack;
import gameStuff.RegionBoard;
import model.Game;

/**
 * This class defines the possible strings corresponding to the parameters 
 * necessary for the action change permit tiles and gives them to the CLI. 
 * In a second moment, it receives the input from the CLI and translates it 
 * into the effective parameters required from the action change permit tiles
 * @author Emanuele
 *
 */

public class ChangePermitTilesConfiguration extends ActionConfiguration{
	
	private ChangePermitTiles action;
	
	/**
	 * Constructor of ChangePermitTilesConfiguration
	 * @param game is the current game
	 * @param action is the action which requires to be configured with parameters given by the user
	 */
	public ChangePermitTilesConfiguration(Game game, ChangePermitTiles action){
		super(game);
		this.action=action;
	}

	/**
	 * This method packs in a AskParameterPack a list of strings from which the user can choose when he inserts
	 * the parameters of the action, according to the current game state.
	 * @return a pack to be sent to CLI, which contains the instructions 
	 * the user can follow to insert the parameters, and the list of possible strings for the selected action
	 */
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

	/**
	 * This method sets to the selected action the parameters given by the user, 
	 * after a translation from the input, which is a list of strings
	 */
	@Override
	public void setParameters(List<String> stringParameters) {
		this.action.setSelectedRegion(regionTranslator
				(stringParameters.remove(0)));
	}
	
	/**
	 * translates the string given from the user into the corresponding region board
	 * @param regionToTranslate is the string corresponding to the name of the selected region board
	 * @return the region board obtained from the string
	 */
	private RegionBoard regionTranslator(String regionToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(regionToTranslate.equals(region.getName()))
				return region;
		throw new IllegalArgumentException("regionToTranslate is not a region name");
	}

}
