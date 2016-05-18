package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;
import view.ErrorNotify;
import view.GameNotify;

/**
 * It's the quick action "additional main action" it operates
 * on the protected attribute game through the method executeAction.
 * @author Luca
 *
 */
public class AdditionalMainAction extends QuickAction {

	private static final int necessaryAssistants=3;
	
	public AdditionalMainAction(Game game){
		super(game);
	}
	
	private boolean checkAssistants(){
		if(this.game.getCurrentPlayer().getNumberOfAssistants()>=necessaryAssistants)
			return true;
		else
			return false;
	}
	
	/**
	 * Decrements the necessaryAssistants to the current player and give him
	 * an additional main action
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	public boolean executeAction() {
		if(!this.checkAssistants()){
			this.game.notifyObserver(new ErrorNotify("You can't do this action"));
			return false;
		}	
		
		this.game.setState(this.game.getState().additionalMainActionTransition());
		this.game.notifyObserver(new GameNotify(this.game));
		return true;
	}
	
	@Override
	public String toString() {
		return "q4: get an additionalo main action";
	}

	@Override
	public ActionParserVisitor setParser() {
		return null;
	}

}