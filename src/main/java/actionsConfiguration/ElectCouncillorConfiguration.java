package actionsConfiguration;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.Game;
import model.actions.ElectCouncillor;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.RegionBoard;
import packdaeliminare.AskParameterPack;

public class ElectCouncillorConfiguration extends ActionConfiguration{
	
	private ElectCouncillor action;
	
	public ElectCouncillorConfiguration(Game game, ElectCouncillor action){
		super(game);
		this.action=action;
	}

	@Override
	public AskParameterPack createAskParameterPack() {

		List<String> parametersName=new ArrayList<String>();
		parametersName.add("Colour of the councillor which you want to pick");
		parametersName.add("Region where there is the council balcony in which you want to substitue a councillor");
		
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(new ArrayList<String>());
		
		Set<String> councillorColours=new HashSet<String>();
		for (Councillor councillor : this.game.getGameTable().getCouncilReserve().getCouncillors())
			councillorColours.add(councillor.getColour().getColour());
		acceptableStrings.get(0).addAll(councillorColours);
		
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
			if (newCouncillorTranslated.getColour().getColour().equals(newCouncillorToTranslate))
				return newCouncillorTranslated;
		throw new IllegalArgumentException("newCouncillorToTranslate is not a colour name");
	}
	
	private CouncilBalcony councilBalconyTranslator(String councilBalconyToTranslate) {
		for(RegionBoard region : this.game.getGameTable().getRegionBoards())
			if(councilBalconyToTranslate.equals(region.getName()))
				return region.getRegionBalcony();
		throw new IllegalArgumentException("councilBalconyToTranslate is not a region name");
	}

}
