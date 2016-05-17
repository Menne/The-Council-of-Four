package model.parser;


import model.Game;
import model.actions.Action;
import model.actions.BuildByKing;
import view.ActionNotify;
import view.ParametersNotify;

public class BuildByKingParser implements ActionParserVisitor {

	private BuildByKing selectedAction;
	private String currentParameter;
	private Game game;
	
	public BuildByKingParser(BuildByKing selectedAction, Game game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}

	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	
	
	@Override
	public Action setParameters(Parser parser) {
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
