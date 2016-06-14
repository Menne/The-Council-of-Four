package modelDTO.actionsDTO;

import client.view.rmi.ClientRMIViewRemote;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class QuitDTORMI implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2925523002357555784L;
	
	private final ClientRMIViewRemote quittingView;
	
	public QuitDTORMI(ClientRMIViewRemote quittingView) {
		this.quittingView=quittingView;
	}

	public ClientRMIViewRemote getQuittingView() {
		return quittingView;
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalArgumentException("this mapper shouldn't be called");
	}
}
