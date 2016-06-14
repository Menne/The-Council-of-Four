package client.modelDTO.clientNotifies;

import java.io.Serializable;

import client.modelDTO.GameDTO;

@FunctionalInterface
public interface ClientNotify extends Serializable{

	public void updateModel(GameDTO gameDTOtoupdate);
}
