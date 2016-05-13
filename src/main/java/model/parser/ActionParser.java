package model.parser;

import java.util.List;

public abstract interface ActionParser {
	
	public abstract List<List<String>> acceptableParameters(Parser parser);

}
