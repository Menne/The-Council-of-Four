package model.actions;

import model.parser.ActionParserVisitor;

public class NullAction implements Action {
	

	@Override
	public boolean executeAction() {
		return true;
	}

	@Override
	public String toString() {
		return "exit: if you want to finish the turn";
	}

	@Override
	public ActionParserVisitor setParser() {
		// TODO Auto-generated method stub
		return null;
	}
	
	

}
