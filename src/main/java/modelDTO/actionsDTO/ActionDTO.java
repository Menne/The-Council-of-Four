package modelDTO.actionsDTO;

import java.io.Serializable;

import server.model.Game;
import server.model.actions.Action;

	public interface ActionDTO extends Serializable{
		
		public Action map(Game game); 

}
