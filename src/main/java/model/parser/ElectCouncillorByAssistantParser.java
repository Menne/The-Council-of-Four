package model.parser;

import java.util.ArrayList;
import java.util.List;

public class ElectCouncillorByAssistantParser implements ActionParser {

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(parser.acceptableCouncillors());
		acceptableStrings.add(parser.acceptableCouncilBalcony());
		return acceptableStrings;
	}

}
