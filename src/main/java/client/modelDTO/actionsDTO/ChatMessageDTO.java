package client.modelDTO.actionsDTO;

import server.model.actions.Action;
import server.model.actions.ChatMessage;
import server.view.actionMapperVisitor.ActionMapperVisitor;

/**
 * This object contains the chat message sent from a player to others
 * @author cg31
 *
 */
public class ChatMessageDTO implements ActionDTO {

	private static final long serialVersionUID = -5494599026163859037L;
	private final String message;

	/**
	 * constructor of ChatMessageDTO
	 * @param message is the chat message the player sent
	 */
	public ChatMessageDTO(String message) {
		this.message=message;
	}
	
	public String getMessage() {
		return this.message;
	}

	@Override
	public Action startMapper(ActionMapperVisitor mapper) {
		return new ChatMessage(this.message);
	}

}
