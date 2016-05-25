package client.actionDTO;

import java.io.Serializable;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;

	public interface ActionDTO extends Serializable{

		public ActionParserVisitor setParser(GameDTO game);
		
		public Action map(Game game); 

}
