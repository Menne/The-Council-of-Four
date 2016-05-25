package client;

import java.io.Serializable;

import client.ModelDTO.GameDTO;

public interface ClientNotify extends Serializable{

	public void act(GameDTO gameDTOtoupdate);
}
