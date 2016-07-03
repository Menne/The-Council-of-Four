package client.modelDTO.actionsDTO;

import server.model.actions.Action;
import server.model.mappers.ActionMapperVisitor;

/**
 * This object contains the number of the map selected by the user
 * @author cg31
 *
 */
public class ChooseMapDTO implements ActionDTO {
	
	private static final long serialVersionUID = 7909531564244090785L;
	private final int mapNumber;
	
	/**
	 * Constructor of ChooseMapDTO
	 * @param mapNumber is the map number
	 */
	public ChooseMapDTO(int mapNumber) {
		this.mapNumber=mapNumber;
	}
	
	public int getMapNumber() {
		return this.mapNumber;
	}



	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalStateException("No mapper for this action");
	}

}
