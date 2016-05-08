package actionsConfiguration;

import java.util.List;

import actions.ElectCouncillor;
import actions.NeedParameters;
import controller.AskParameterPack;
import model.Game;

public class ElectCouncillorConfiguration extends ActionConfiguration{
	
	private ElectCouncillor action;
	
	public ElectCouncillorConfiguration(Game game, ElectCouncillor action){
		super(game);
		this.action=action;
	}

	@Override
	public AskParameterPack createAskParameterPack() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void setParameters(List<String> stringParameter) {
		// TODO Auto-generated method stub
		
	}

}
