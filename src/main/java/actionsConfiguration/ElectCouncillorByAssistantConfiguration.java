package actionsConfiguration;

import java.util.List;

import actions.ElectCouncillorByAssistant;
import controller.AskParameterPack;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import model.Game;

public class ElectCouncillorByAssistantConfiguration extends ActionConfiguration{
	
	private ElectCouncillorByAssistant action;
	
	public ElectCouncillorByAssistantConfiguration(Game game, ElectCouncillorByAssistant action){
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
