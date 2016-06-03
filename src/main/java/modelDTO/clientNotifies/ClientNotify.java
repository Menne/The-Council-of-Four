package modelDTO.clientNotifies;

import java.io.Serializable;

import client.controller.ControllerNotify;
import modelDTO.GameDTO;

@FunctionalInterface
public interface ClientNotify extends Serializable, ControllerNotify{

	public void act(GameDTO gameDTOtoupdate);
}
