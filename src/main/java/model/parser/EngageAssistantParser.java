package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.EngageAssistant;

public class EngageAssistantParser implements ActionParserVisitor{

	private EngageAssistant selectedAction;
	
	public EngageAssistantParser(EngageAssistant selectedAction) {
		this.selectedAction=selectedAction;
	}
	
	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> info=new ArrayList<List<String>>();
		List<String> message=new ArrayList<String>();
		message.add("Assistant engaged!");
		info.add(message);
		return info;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		return selectedAction;
	}

}
