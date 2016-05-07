package controller;

import java.util.List;

public class AskParameterPack {

	private final List<String> parameters;
	private final List<List<String>> acceptableParameters;
	
	public AskParameterPack(List<String> parameters, 
			List<List<String>> acceptableParameters){
		
		this.parameters=parameters;
		this.acceptableParameters=acceptableParameters;
	}

	public List<String> getParameters() {
		return parameters;
	}

	public List<List<String>> getAcceptableParameters() {
		return acceptableParameters;
	}
	
}
