package model.parser;

import java.util.ArrayList;
import java.util.List;

import model.actions.Action;
import model.actions.BuildByKing;
import model.gameTable.CouncilBalcony;
import model.gameTable.PoliticsCard;

public class BuildByKingParser implements ActionParserVisitor {

	private BuildByKing selectedAction;
	
	public BuildByKingParser(BuildByKing selectedAction) {
		this.selectedAction=selectedAction;
	}
	
	@Override
	public List<List<String>> acceptableParameters(Parser parser) {
		List<List<String>> acceptableStrings=new ArrayList<List<String>>();
		List<String> message=new ArrayList<String>();
		message.add("Ok, you have choosed to build an emporium with the help of the king. Now I need some more infos, like:");
		acceptableStrings.add(message);
		acceptableStrings.add(parser.acceptableCities());
		acceptableStrings.add(parser.acceptableFirstPoliticsCard());
		for (int i=1; i<CouncilBalcony.getNumberofcouncillors(); i++)
			acceptableStrings.add(parser.acceptablePoliticsCards());
		return acceptableStrings;
	}

	@Override
	public Action parametersParser(List<String> stringParameters, Parser parser) {
		selectedAction.setSelectedCity(parser.cityTranslator
				(stringParameters.remove(0)));
		List<PoliticsCard> cardsTranslated = new ArrayList<PoliticsCard>();
		for (String parameter : stringParameters) {
			cardsTranslated.add(parser.politicsCardTranslator(stringParameters.remove(0)));
			if (!parameter.equals("stop"))
				break;
		}
		selectedAction.setCardsToDescard(cardsTranslated);
		return selectedAction;
	}

}
