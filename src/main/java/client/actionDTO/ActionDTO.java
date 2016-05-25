package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;
import model.Game;
import model.actions.Action;

	public interface ActionDTO {

		public ActionParserVisitor setParser(GameDTO game);
		
		public Action map(Game game); 

}
