package client.parser;

import client.actionDTO.ActionDTO;

public abstract interface ActionParserVisitor {
	
	public abstract void setCurrentParameter(String input);

	public abstract ActionDTO setParameters(Parser parser);

}
