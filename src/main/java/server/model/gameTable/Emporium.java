package server.model.gameTable;
import server.model.player.Player;

/**
 * modelizes the emporium object and has only the emporium's handler(player) as attribute
 * @author andreapasquali
 *
 */
public class Emporium {
	
	private final Player emporiumsPlayer;
	
	public Emporium(Player emporiumsPlayer){
		this.emporiumsPlayer=emporiumsPlayer;
	}

	public Player getEmporiumsPlayer() {
		return emporiumsPlayer;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((emporiumsPlayer == null) ? 0 : emporiumsPlayer.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Emporium other = (Emporium) obj;
		if (emporiumsPlayer == null) {
			if (other.emporiumsPlayer != null)
				return false;
		} else if (!emporiumsPlayer.equals(other.emporiumsPlayer))
			return false;
		return true;
	}
	
	
}