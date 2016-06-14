package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.actionsDTO.ActionDTO;

public abstract interface ActionParserVisitor {

	public abstract ActionDTO setParameters();

}
