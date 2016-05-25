package server.view.serverSocketViewNotifies;

import server.view.clientNotifies.ClientNotify;
import server.view.clientNotifies.ErrorDTONotify;

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
