package actionsConfiguration;

import java.util.ArrayList;
import java.util.List;

import model.Game;
import model.actions.ElectCouncillorByAssistant;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.RegionBoard;
import packdaeliminare.AskParameterPack;

public class ElectCouncillorByAssistantConfiguration extends ActionConfiguration{
	
	private ElectCouncillorByAssistant action;
	
	public ElectCouncillorByAssistantConfiguration(Game game, ElectCouncillorByAssistant action){
		super(game);
		this.action=action;
	}

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

	@Override
	public void setParameters(List<String> stringParameters) {
		this.action.setNewCouncillor(councillorTranslator
				(stringParameters.remove(0)));
		this.action.setCouncilBalcony(councilBalconyTranslator
				(stringParameters.remove(0)));
	}
	
	private Councillor councillorTranslator(String newCouncillorToTranslate) {
		for (Councillor newCouncillorTranslated : this.game.getGameTable().getCouncilReserve().getCouncillors())
			if (newCouncillorTranslated.getColour().equals(newCouncillorToTranslate))
				return newCouncillorTranslated;
		return null;
	}
	
	private CouncilBalcony councilBalconyTranslator(String councilBalconyToTranslate) {
		int numberOfRegion=Integer.parseInt(councilBalconyToTranslate);
		CouncilBalcony councilBalconyTranslated=
				this.game.getGameTable().getRegionBoards().get(numberOfRegion).getRegionBalcony();
		return councilBalconyTranslated;
	}

}
