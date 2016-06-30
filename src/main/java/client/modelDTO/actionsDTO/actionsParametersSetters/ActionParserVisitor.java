package client.modelDTO.actionsDTO.actionsParametersSetters;

import client.modelDTO.GameDTO;
import client.view.ClientView;

@FunctionalInterface
public abstract interface ActionParserVisitor {

	public abstract void setParameters(ClientView view, GameDTO game);

}
