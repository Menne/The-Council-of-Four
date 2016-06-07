package modelDTO.actionsDTO;

import client.view.ClientView;
import server.model.actions.Action;
import server.view.mapperVisitor.ActionDTOMapper;

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
	public Action startVisitor(ActionDTOMapper mapper) {
		return null;
	}

}
