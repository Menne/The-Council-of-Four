package model.parser;

import java.util.ArrayList;
import java.util.List;

public class ChangePermitTilesParser implements ActionParser {

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(parser.acceptableRegions());
		return acceptableStrings;
	}

}
