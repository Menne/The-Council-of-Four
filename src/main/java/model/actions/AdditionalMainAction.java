package model.actions;

import model.Game;
import model.parser.ActionParserVisitor;
import view.ErrorNotify;

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
		if(this.game.getCurrentPlayer().getAssistants()>=necessaryAssistants)
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
			this.game.notifyObserver(new ErrorNotify());
			return false;
		}			
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