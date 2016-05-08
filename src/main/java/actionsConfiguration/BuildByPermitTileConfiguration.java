package actionsConfiguration;

import java.util.List;

import actions.BuildByPermitTile;
import actions.NeedParameters;
import controller.AskParameterPack;
import model.Game;

public class BuildByPermitTileConfiguration extends ActionConfiguration{
	
	private BuildByPermitTile action;
	
	
	public BuildByPermitTileConfiguration(Game game, BuildByPermitTile action){
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
