package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.BuildByPermitTile;

public class BuildWithPermitTileParser implements ActionParserVisitor {

	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		acceptableStrings.add(parser.acceptableCities());
		acceptableStrings.add(parser.acceptablePermitTiles());
		return acceptableStrings;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		BuildByPermitTile buildByPermitTile=new BuildByPermitTile(parser.game);
		buildByPermitTile.setSelectedCity(parser.cityTranslator
				(stringParameters.remove(0)));
		buildByPermitTile.setSelectedPermitTile(parser.permitTileTranslator
				(stringParameters.remove(0)));
		return buildByPermitTile;
	}

}
