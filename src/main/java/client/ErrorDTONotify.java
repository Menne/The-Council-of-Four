package client;

import client.ModelDTO.GameDTO;

public class ErrorDTONotify implements ClientNotify{

	private String message;

	public ErrorDTONotify(String message) {
		this.message=message;
	}

	@Override
	public void act(GameDTO gameDTOtoupdate) {
		System.out.println(this.message);
		
	}

	
}
