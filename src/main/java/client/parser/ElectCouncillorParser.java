package client.parser;

import client.ModelDTO.GameDTO;
import client.actionDTO.ActionDTO;
import client.actionDTO.ElectCouncillorDTO;
import client.clientView.notifies.ActionNotify;
import client.clientView.notifies.ParametersNotify;

public class ElectCouncillorParser implements ActionParserVisitor {

	private ElectCouncillorDTO selectedAction;
	private String currentParameter;
	private GameDTO game;
	
	public ElectCouncillorParser(ElectCouncillorDTO selectedAction, GameDTO game) {
		this.selectedAction=selectedAction;
		this.currentParameter=null;
		this.game=game;
	}
	
	public void setCurrentParameter(String currentParameter) {
		this.currentParameter=currentParameter;
	}
	
	
	@Override
	public ActionDTO setParameters(Parser parser) {
		this.game.notifyObserver(new ActionNotify
				("Ok! you have chosen to send an assistant to elect a councillor. Now I need some other infos, like:"));
		
		this.game.notifyObserver(new ActionNotify
				("the colour of the councillor you want to elect"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableCouncillors(), this));
	//	this.selectedAction.setNewCouncillor(parser.councillorTranslator(currentParameter));
		
		this.game.notifyObserver(new ActionNotify
				("the name of the region in which you want to change the councillor"));
		this.game.notifyObserver(new ParametersNotify(parser.acceptableCouncilBalcony(), this));
	//	this.selectedAction.setCouncilBalcony(parser.councilBalconyTranslator(currentParameter));
		
		return this.selectedAction;
	}

}
