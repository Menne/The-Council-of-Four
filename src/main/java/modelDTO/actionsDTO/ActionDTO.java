package modelDTO.actionsDTO;

import java.io.Serializable;

import client.controller.ControllerNotify;
import server.model.Game;
import server.model.actions.Action;

	public interface ActionDTO extends Serializable,ControllerNotify{
		
		public Action map(Game game); 

}
