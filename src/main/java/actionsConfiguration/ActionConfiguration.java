package actionsConfiguration;

import java.util.List;

import controller.AskParameterPack;
import model.Game;

public abstract class ActionConfiguration {
	
	protected Game game;
	
	public ActionConfiguration(Game game){
		this.game=game;
	}
	
	public abstract AskParameterPack createAskParameterPack();
	
	public abstract void setParameters(List<String> stringParameter);
	
}
