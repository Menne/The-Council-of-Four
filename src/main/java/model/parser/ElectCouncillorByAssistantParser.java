package model.parser;

import model.Game;
import model.actions.Action;
import model.actions.ElectCouncillorByAssistant;
import view.ActionNotify;
import view.ParametersNotify;

public class ElectCouncillorByAssistantParser implements ActionParserVisitor {

	private ElectCouncillorByAssistant selectedAction;
	private String currentParameter;
	private Game game;
	
	public ElectCouncillorByAssistantParser(ElectCouncillorByAssistant selectedAction, Game game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}

	@Override
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	

	@Override
	public Action setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify
				("Ok! you have chosen to elect a councillor. Now I need some other infos, like:"));
		
		this.game.notifyObserver(new ActionNotify
				("the colour of the councillor you want to elect"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableCouncillors(), this));
		this.selectedAction.setNewCouncillor(parser.councillorTranslator(currentParameter));
		
		this.game.notifyObserver(new ActionNotify
				("the name of the region in which you want to change the councillor"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableCouncilBalcony(), this));
		this.selectedAction.setCouncilBalcony(parser.councilBalconyTranslator(currentParameter));
		
		return this.selectedAction;
	}

}
