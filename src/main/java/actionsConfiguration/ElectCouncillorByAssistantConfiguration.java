package actionsConfiguration;

import java.util.List;

import actions.ElectCouncillorByAssistant;
import actions.NeedParameters;
import controller.AskParameterPack;
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
	public void setParameters(List<String> stringParameter) {
		// TODO Auto-generated method stub
		
	}

}
