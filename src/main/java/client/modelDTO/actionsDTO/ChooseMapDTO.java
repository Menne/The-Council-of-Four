package client.modelDTO.actionsDTO;

import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class ChooseMapDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7909531564244090785L;
	private final int mapNumber;
	
	public ChooseMapDTO(int mapNumber) {
		this.mapNumber=mapNumber;
	}
	
	

	public int getMapNumber() {
		return mapNumber;
	}



	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalStateException("No mapper for this action");
	}

}
