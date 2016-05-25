package modelDTO.actionsDTO;

import java.io.Serializable;

import modelDTO.GameDTO;
import modelDTO.parser.ActionParserVisitor;
import server.model.Game;
import server.model.actions.Action;

	public interface ActionDTO extends Serializable{

		public ActionParserVisitor setParser(GameDTO game);
		
		public Action map(Game game); 

}
