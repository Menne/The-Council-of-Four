package modelDTO.parser;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ElectCouncillorByAssistantDTO;

public class ElectCouncillorByAssistantParser implements ActionParserVisitor {

	private ElectCouncillorByAssistantDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public ElectCouncillorByAssistantParser(ElectCouncillorByAssistantDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}

	@Override
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	

	@Override
	public ActionDTO setParameters(Parser parser) {
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
