package controller;


public abstract class Turn {
	
	
	protected final GameLogic gameLogic;
	
	public Turn(GameLogic gameLogic) {
		this.gameLogic=gameLogic;
	}
	
	public abstract void executeTurn();


}