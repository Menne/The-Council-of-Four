package modelDTO.clientNotifies;

import java.io.Serializable;

import modelDTO.GameDTO;
import modelDTO.playerDTO.PlayerDTO;

@FunctionalInterface
public interface ClientNotify extends Serializable{

	public void act(GameDTO gameDTOtoupdate, PlayerDTO playerDTO);
}
