package client.actionDTO;

import java.io.Serializable;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import server.model.Game;
import server.model.actions.Action;

	public interface ActionDTO extends Serializable{

		public ActionParserVisitor setParser(GameDTO game);
		
		public Action map(Game game); 

}
