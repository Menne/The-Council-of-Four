package actionsConfiguration;

import java.util.List;

import actions.ChangePermitTiles;
import actions.NeedParameters;
import controller.AskParameterPack;
import model.Game;

public class ChangePermitTilesConfiguration extends ActionConfiguration{
	
	private ChangePermitTiles action;
	
	public ChangePermitTilesConfiguration(Game game, ChangePermitTiles action){
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
