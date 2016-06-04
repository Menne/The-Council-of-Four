package modelDTO.clientNotifies;

import modelDTO.GameDTO;

public class MessageDTONotify implements ClientNotify{

	/**
	 * 
	 */
	private static final long serialVersionUID = -51775887051624563L;
	private String message;

	public MessageDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void updateModel(GameDTO gameDTOtoupdate) {
		System.out.println(this.message);
		
	}

	
}
