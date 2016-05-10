package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import actions.ElectCouncillorByAssistant;
import controller.AskParameterPack;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.RegionBoard;
import model.Game;

/**
 * This class defines the possible strings corresponding to the parameters 
 * necessary for the action elect a councillor by an assistant and gives them to the CLI. 
 * In a second moment, it receives the input from the CLI and translates it 
 * into the effective parameters required from the action elect a councillor by an assistant.
 * @author Emanuele
 *
 */

public class ElectCouncillorByAssistantConfiguration extends ActionConfiguration{
	
	private ElectCouncillorByAssistant action;
	
	/**
	 * Constructor of ElectCouncillorByAssistantConfiguration
	 * @param game is the current game
	 * @param action is the action which requires to be configured with parameters given by the user
	 */
	public ElectCouncillorByAssistantConfiguration(Game game, ElectCouncillorByAssistant action){
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
		parametersName.add("Colour of the councillor which you want to pick");
		parametersName.add("Region where there is the council balcony in which you want to substitue a councillor");
		
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		
		List<String> councillorColours=new ArrayList<String>();
		for (Councillor councillor : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if (councillorColours.contains(councillor.getColour().getColour()))
				councillorColours.add(councillor.getColour().getColour());
		acceptableStrings.add(councillorColours);
		
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
		this.action.setNewCouncillor(councillorTranslator
				(stringParameters.remove(0)));
		this.action.setCouncilBalcony(councilBalconyTranslator
				(stringParameters.remove(0)));
	}
	
	/**
	 * translates the string given from the user into the corresponding councillor
	 * @param newCouncillorToTranslate is the string corresponding to the colour of the selected councillor
	 * @return the councillor obtained from the string
	 */
	private Councillor councillorTranslator(String newCouncillorToTranslate) {
		for (Councillor newCouncillorTranslated : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if (newCouncillorTranslated.getColour().getColour().equals(newCouncillorToTranslate))
				return newCouncillorTranslated;
		return null;
	}
	
	/**
	 * translates the string given from the user into the corresponding council balcony
	 * @param councilBalconyToTranslate is the string corresponding to the name of the region where there is the selected council balcony
	 * @return the council balcony obtained from the string
	 */
	private CouncilBalcony councilBalconyTranslator(String councilBalconyToTranslate) {
		int numberOfRegion=Integer.parseInt(councilBalconyToTranslate);
		CouncilBalcony councilBalconyTranslated=
				this.game.getGameTable().getRegionBoards().get(numberOfRegion).getRegionBalcony();
		return councilBalconyTranslated;
	}

}
