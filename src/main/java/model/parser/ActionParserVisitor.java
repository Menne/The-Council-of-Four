package model.parser;

import java.util.List;

import model.actions.Action;

public abstract interface ActionParserVisitor {
	
	public abstract List<List<String>> acceptableParameters(Parser parser);
	
	public abstract Action parametersParser(List<String> stringParameters, Parser parser);

}
