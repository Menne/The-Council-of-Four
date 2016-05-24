package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;

public interface ActionDTO {

	public ActionParserVisitor setParser(GameDTO game);

}
