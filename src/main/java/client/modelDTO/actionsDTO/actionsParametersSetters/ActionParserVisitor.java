package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.ActionDTO;
import client.view.ClientView;

@FunctionalInterface
public abstract interface ActionParserVisitor {

	public abstract ActionDTO setParameters(ClientView view, GameDTO game);

}
