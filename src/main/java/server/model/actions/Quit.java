package server.model.actions;

import client.modelDTO.actionsDTO.ActionDTO;
import server.model.Game;
import server.model.player.Player;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.MessageServerNotify;

/**
 * Action executed when a player wants to leave the game
 * @author cg31
 *
 */
public class Quit implements Action {

	private final Player quittingPlayer;
	
	/**
	 * Constructor of Quit action
	 * @param quittingPlayer is the player who has decided to leave the game
	 */
	public Quit(Player quittingPlayer) {
		this.quittingPlayer=quittingPlayer;
	}

	/**
	 * Disconnects the player who decided to leave
	 */
	@Override
	public boolean executeAction(Game game) {
		if(quittingPlayer.equals(game.getCurrentPlayer())){
			game.setState(game.getState().moveToNextTransition(game));
			if(quittingPlayer.equals(game.lastPlayer())){
				game.getPlayers().remove(quittingPlayer);
				game.getPlayers().add(0, game.getPlayers().remove(game.getPlayers().size()-1));
			}
			else
				game.getPlayers().remove(quittingPlayer);
			game.getMarket().getBuyingPlayerList().remove(quittingPlayer);
			game.getMarket().getSellingPlayerList().remove(quittingPlayer);
			game.getQuittedPlayers().add(quittingPlayer);
			if(game.getPlayers().size()==1){
				game.getQuittedPlayers().add(game.getPlayers().remove(0));
				game.endGame();
			}
			else
				game.getState().updateAvailableActions(game);
		}
		else{
			game.getPlayers().remove(quittingPlayer);
			game.getMarket().getBuyingPlayerList().remove(quittingPlayer);
			game.getMarket().getSellingPlayerList().remove(quittingPlayer);
			game.getQuittedPlayers().add(quittingPlayer);
			if(game.getPlayers().size()==1){
				game.getQuittedPlayers().add(game.getPlayers().remove(0));
				game.endGame();
			}
			else
				game.notifyObserver(new GameTableServerNotify(game, game.getAllPlayers(),false));
		}
		this.updateClients(game);
		return true;
	}

	@Override
	public ActionDTO map(){
		throw new IllegalStateException("no mapping for action quit");
	}

	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new MessageServerNotify(game.getQuittedPlayers().get
				(game.getQuittedPlayers().size()-1).getName() + " has left the game", game.getPlayers()));
	}

}
