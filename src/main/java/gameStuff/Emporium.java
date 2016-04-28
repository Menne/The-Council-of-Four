package gameStuff;
import players.Player;

/**
 * 
 * @author andreapasquali
 *
 */
public class Emporium {
	
	public static Player getEmporiumsPlayer;
	private final Player emporiumsPlayer;
	
	public Emporium(Player emporiumsPlayer){
		this.emporiumsPlayer=emporiumsPlayer;
	}

	public Player getEmporiumsPlayer() {
		return emporiumsPlayer;
	}
}