package view;

import client.ClientNotify;
import client.ErrorDTONotify;

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
