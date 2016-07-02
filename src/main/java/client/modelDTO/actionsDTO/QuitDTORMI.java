package client.modelDTO.actionsDTO;

import client.connections.ClientRMIViewRemote;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This object is sent to the server when a RMI client makes the decision to leave the game 
 * @author cg31
 *
 */
public class QuitDTORMI implements ActionDTO {

	private static final long serialVersionUID = 2925523002357555784L;
	private final ClientRMIViewRemote quittingView;
	
	/**
	 * Constructor of QuitDTORMI
	 * @param quittingView is the view of the client who is leaving the game
	 */
	public QuitDTORMI(ClientRMIViewRemote quittingView) {
		this.quittingView=quittingView;
	}

	public ClientRMIViewRemote getQuittingView() {
		return quittingView;
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalArgumentException("QuitDTO doesn't require mapping");
	}
}
