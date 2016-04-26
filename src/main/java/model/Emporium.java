package model;

import players.Player;

public class Emporium {
	private final Player emporiumsPlayer;
	
	public Emporium(Player emporiumsPlayer){
		this.emporiumsPlayer=emporiumsPlayer;
	}

	public Player getEmporiumsPlayer() {
		return emporiumsPlayer;
	}
}