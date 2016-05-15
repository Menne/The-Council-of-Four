package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.ElectCouncillor;

public class ElectCouncillorParser implements ActionParserVisitor {

	private ElectCouncillor selectedAction;
			
	public ElectCouncillorParser(ElectCouncillor selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		List<String> message=new ArrayList<String>();
		message.add("Ok, you have choosed to elect a councillor. Now I need some more infos, like:");
		acceptableStrings.add(message);
		acceptableStrings.add(parser.acceptableCouncillors());
		acceptableStrings.add(parser.acceptableCouncilBalcony());
		return acceptableStrings;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		selectedAction.setNewCouncillor(parser.councillorTranslator
				(stringParameters.remove(0)));
		selectedAction.setCouncilBalcony(parser.councilBalconyTranslator
				(stringParameters.remove(0)));
		return selectedAction;
		}

}
