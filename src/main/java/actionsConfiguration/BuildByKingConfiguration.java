package actionsConfiguration;

import java.util.List;

import actions.BuildByKing;
import actions.NeedParameters;
import controller.AskParameterPack;
import model.Game;

public class BuildByKingConfiguration extends ActionConfiguration{
	
	private BuildByKing action;
	
	public BuildByKingConfiguration(Game game, BuildByKing action){
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
