package modelDTO.actionsDTO;

import client.view.ClientView;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class QuitDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4211998116455080382L;
	
	private final ClientView quittingView;
	
	
	public QuitDTO(ClientView quittingView) {
		this.quittingView=quittingView;
	}

	public ClientView getQuittingView() {
		return quittingView;
	}


	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalArgumentException("QuitDTO doesn't require mapping");
	}

}
