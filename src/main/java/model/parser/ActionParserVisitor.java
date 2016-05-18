package model.parser;

import model.actions.Action;

public abstract interface ActionParserVisitor {
	
	public abstract void setCurrentParameter(String input);

	public abstract Action setParameters(Parser parser);

}
