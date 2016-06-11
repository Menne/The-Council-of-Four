package modelDTO.actionsDTO;

import server.model.actions.Action;
import server.model.actions.PickPoliticsCard;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class PickPoliticsCardDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7937576386571037161L;

	
	@Override
	public String toString() {
		return "pc: pick a politics card";
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return new PickPoliticsCard();
	}

}
