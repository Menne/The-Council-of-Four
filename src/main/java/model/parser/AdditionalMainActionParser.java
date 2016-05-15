package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.AdditionalMainAction;

public class AdditionalMainActionParser implements ActionParserVisitor{

	private AdditionalMainAction selectedAction;
	
	public AdditionalMainActionParser(AdditionalMainAction selectedAction) {
		this.selectedAction=selectedAction;
	}
	
	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> info=new ArrayList<List<String>>();
		List<String> message=new ArrayList<String>();
		message.add("Now you have an additional main action!");
		info.add(message);
		return info;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		return selectedAction;
	}

}
