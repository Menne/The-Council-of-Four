package modelDTO.actionsDTO;

import client.view.ClientView;
import modelDTO.GameDTO;
import modelDTO.parser.ActionParserVisitor;

public interface ActionWithParameters extends ActionDTO {

	public ActionParserVisitor setParser(ClientView view, GameDTO game);

	public boolean checkIfParametersSetted();
	
	public void parametersSetted();

}
