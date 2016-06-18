package client.modelDTO.actionsDTO;

import client.modelDTO.actionsDTO.actionsParametersSetters.ActionParserVisitor;

public interface ActionWithParameters extends ActionDTO {

	public ActionParserVisitor setParser();

	public boolean checkIfParametersSetted();
	
	public void parametersSetted();

}
