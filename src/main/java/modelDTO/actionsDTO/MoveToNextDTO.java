package modelDTO.actionsDTO;

import server.model.actions.Action;
import server.model.actions.MoveToNext;
import server.view.mapperVisitor.ActionDTOMapper;

public class MoveToNextDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6168986616607464860L;


	@Override
	public String toString() {
		return "sk: skip this passage";
	}

	@Override
	public Action startVisitor(ActionDTOMapper mapper) {
		return new MoveToNext();
	}
	

}
