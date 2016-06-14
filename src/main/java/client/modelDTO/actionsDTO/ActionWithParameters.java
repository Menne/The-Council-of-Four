package client.modelDTO.actionsDTO;

import client.modelDTO.GameDTO;
import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;
import client.view.ClientView;

public interface ActionWithParameters extends ActionDTO {

	public ActionParserVisitor setParser(ClientView view, GameDTO game);

	public boolean checkIfParametersSetted();
	
	public void parametersSetted();

}
