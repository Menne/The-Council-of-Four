package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.PickPoliticsCard;

public class PickPoliticsCardParser implements ActionParserVisitor{

	private PickPoliticsCard selectedAction;
	
	public PickPoliticsCardParser(PickPoliticsCard selectedAction) {
		this.selectedAction=selectedAction;
	}
	
	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> info=new ArrayList<List<String>>();
		List<String> message=new ArrayList<String>();
		message.add("card picked");
		info.add(message);
		return info;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		return selectedAction;
	}

}
