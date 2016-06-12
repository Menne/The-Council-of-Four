package modelDTO.actionsDTO;

import client.view.Connection;
import server.model.actions.Action;
import server.view.actionMapperVisitor.ActionMapperVisitor;

public class QuitDTO implements ActionDTO {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4211998116455080382L;
	
	private final Connection quittingConnection;
	
	
	public QuitDTO(Connection connection) {
		this.quittingConnection=connection;
	}



	public Connection getQuittingConnection() {
		return quittingConnection;
	}



	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		throw new IllegalArgumentException("QuitDTO doesn't require mapping");
	}

}
