package server.view.notifies;

import modelDTO.clientNotifies.ClientNotify;
import modelDTO.clientNotifies.ErrorDTONotify;

public class ErrorNotify implements ViewNotify {

	private String message;

	public ErrorNotify(String message) {
		this.message=message;
	}

	@Override
	public ClientNotify toClientNotify() {
		return new ErrorDTONotify(this.message);
	}

}
