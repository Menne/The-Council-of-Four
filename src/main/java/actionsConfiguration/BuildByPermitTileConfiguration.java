package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.BuildByPermitTile;
import controller.AskParameterPack;
import gameStuff.City;
import gameStuff.Emporium;
import gameStuff.PermitTile;
import gameStuff.RegionBoard;
import model.Game;

/**
 * This class defines the possible strings corresponding to the parameters 
 * necessary for the action build by permit tile and gives them to the CLI. 
 * In a second moment, it receives the input from the CLI and translates it 
 * into the effective parameters required from the action build by permit tile
 * @author Emanuele
 *
 */

public class BuildByPermitTileConfiguration extends ActionConfiguration{
	
	private BuildByPermitTile action;
	
	/**
	 * Constructor of BuildByPermitTileConfiguration
	 * @param game is the current game
	 * @param action is the action which requires to be configured with parameters given by the user
	 */
	public BuildByPermitTileConfiguration(Game game, BuildByPermitTile action){
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

	/**
	 * This method sets to the selected action the parameters given by the user, 
	 * after a translation from the input, which is a list of strings
	 */
	@Override
	public void setParameters(List<String> stringParameters) {
		this.action.setSelectedCity(cityTranslator
				(stringParameters.remove(0)));
		this.action.setSelectedPermitTile(permitTileTranslator
				(stringParameters.remove(0)));
		
	}
	
	/**
	 * translates the string given from the user into the corresponding city
	 * @param cityToTranslate is the string corresponding to the name of the selected city
	 * @return the city obtained from the string
	 */
	private City cityTranslator(String cityToTranslate) {
		for (RegionBoard regionBoard : this.game.getGameTable().getRegionBoards())
			for (City cityTranslated : regionBoard.getRegionCities())
				if (cityTranslated.getName().equals(cityToTranslate))
					return cityTranslated;
		return null;
	}
	
	/**
	 * translates the string given from the user into the corresponding permit tile
	 * @param permitTileToTranslate is the string corresponding to the index of the permit tile in the hand of the player
	 * @return the permit tile obtained from the string
	 */
	private PermitTile permitTileTranslator(String permitTileToTranslate) {
		int numberOfPermitTile=Integer.parseInt(permitTileToTranslate);
		PermitTile permitTileTranslated=this.game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(numberOfPermitTile);
		return permitTileTranslated;
	}

}
