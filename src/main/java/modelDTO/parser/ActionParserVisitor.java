package modelDTO.parser;

import modelDTO.actionsDTO.ActionDTO;

public abstract interface ActionParserVisitor {

	public abstract ActionDTO setParameters();

}
