package client.actionDTO;

import client.ModelDTO.GameDTO;
import client.parser.ActionParserVisitor;

public interface ActionDTO {

	ActionParserVisitor setParser(GameDTO game);

}
