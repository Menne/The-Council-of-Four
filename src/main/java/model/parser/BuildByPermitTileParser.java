package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.BuildByPermitTile;

public class BuildByPermitTileParser implements ActionParserVisitor {

	private BuildByPermitTile selectedAction;
			
	public BuildByPermitTileParser(BuildByPermitTile selectedAction) {
		this.selectedAction=selectedAction;
	}

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		List<String> message=new ArrayList<String>();
		message.add("Ok, you have choosed to build an emporium using a permit tile. Now I need some more infos, like:");
		acceptableStrings.add(message);
		acceptableStrings.add(parser.acceptableCities());
		acceptableStrings.add(parser.acceptablePermitTiles());
		return acceptableStrings;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		selectedAction.setSelectedCity(parser.cityTranslator
				(stringParameters.remove(0)));
		selectedAction.setSelectedPermitTile(parser.permitTileTranslator
				(stringParameters.remove(0)));
		return selectedAction;
	}

}
