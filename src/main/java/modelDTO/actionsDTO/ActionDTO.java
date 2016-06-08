package modelDTO.actionsDTO;

import java.io.Serializable;

import server.model.actions.Action;
import server.view.mapperVisitor.ActionDTOMapper;

public interface ActionDTO extends Serializable{
	
	public Action startVisitor(ActionDTOMapper mapper);

}
