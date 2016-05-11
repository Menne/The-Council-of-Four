package actionsConfiguration;

import java.util.List;

import controller.AskParameterPack;
import model.Game;

/**
 * This class defines the possible strings corresponding to the parameters 
 * necessary for a generic action and gives them to the CLI. 
 * In a second moment, it receives the input from the CLI and translates it 
 * into the effective parameters required from a generic action
 * @author Emanuele
 *
 */

public abstract class ActionConfiguration {
	
	protected Game game;
	
	public ActionConfiguration(Game game){
		this.game=game;
	}
	
	public abstract AskParameterPack createAskParameterPack();
	
	public abstract void setParameters(List<String> stringParameter);
	
}
