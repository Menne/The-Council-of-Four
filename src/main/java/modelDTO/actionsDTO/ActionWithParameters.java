package modelDTO.actionsDTO;

import modelDTO.GameDTO;
import modelDTO.parser.ActionParserVisitor;

public interface ActionWithParameters extends ActionDTO {
	
	public ActionParserVisitor setParser(GameDTO game);

}
