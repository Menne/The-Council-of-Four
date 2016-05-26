package modelDTO.parser;

import client.view.notifies.ActionNotify;
import client.view.notifies.ParametersNotify;
import modelDTO.GameDTO;
import modelDTO.actionsDTO.ActionDTO;
import modelDTO.actionsDTO.ElectCouncillorDTO;

public class ElectCouncillorParser implements ActionParserVisitor {

	private ElectCouncillorDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public ElectCouncillorParser(ElectCouncillorDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.game=game;
	}
	
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
