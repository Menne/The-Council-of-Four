package modelDTO.parser;

import modelDTO.actionsDTO.ActionDTO;

public abstract interface ActionParserVisitor {
	
	public abstract void setCurrentParameter(String input);

	public abstract ActionDTO setParameters(Parser parser);

}
