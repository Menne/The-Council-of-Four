package modelDTO.parser;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.BuildByKingDTO;

public class BuildByKingParser implements ActionParserVisitor {

	private BuildByKingDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public BuildByKingParser(BuildByKingDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.game=game;
	}

	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	
	
	@Override
	public ActionDTO setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify
				("Ok! you have chosen to build an emporium with the help of the king. Now I need some other infos, like:"));
		
		this.game.notifyObserver(new ActionNotify
				("the name of the city in which you want to build"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableCities(), this));
		this.selectedAction.setSelectedCity(parser.cityTranslator(currentParameter));
		
		this.game.notifyObserver(new ActionNotify
				("the colour of the cards you want to descard"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptablePoliticsCards(), this));
		this.selectedAction.setCardsToDescard(parser.politicsCardsTranslator(currentParameter));
	
		return this.selectedAction;
	}

}
