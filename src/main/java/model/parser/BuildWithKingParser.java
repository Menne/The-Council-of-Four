package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.gameTable.CouncilBalcony;

public class BuildWithKingParser implements ActionParser {

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(parser.acceptableCities());
		acceptableStrings.add(parser.acceptableFirstPoliticsCard());
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors(); i++)
			acceptableStrings.add(parser.acceptablePoliticsCards());
		return acceptableStrings;
	}

}
