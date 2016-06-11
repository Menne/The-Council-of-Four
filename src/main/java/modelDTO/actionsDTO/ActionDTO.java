package modelDTO.actionsDTO;

import java.io.Serializable;

import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public interface ActionDTO extends Serializable{
	
	public Action startMapper(ActionMapperVisitor mapper);

}
