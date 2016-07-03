package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.player.Player;
import server.serverNotifies.GameTableServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * It's the main action "elect councillor".
 * @author cg31
 *
 */
public class ElectCouncillor implements MainAction {

	private Councillor newCouncillor;
	private CouncilBalcony councilBalcony;
	private static final int givenCoins=4;
	
	public void setNewCouncillor(Councillor newCouncillor) {
		this.newCouncillor = newCouncillor;
	}

	public void setCouncilBalcony(CouncilBalcony councilBalcony) {
		this.councilBalcony = councilBalcony;
	}

	
	public Councillor getNewCouncillor() {
		return newCouncillor;
	}

	public CouncilBalcony getCouncilBalcony() {
		return councilBalcony;
	}

	/**
	 * Substitutes a given councillor in one of the balconies of the game,
	 * adds the old substituted councillor in the reserve,
	 * gives to the current player 4 coins.
	 * @return TRUE if the action ends well; FALSE otherwise.
	 */
	@Override
	public boolean executeAction(Game game) throws NullPointerException{
		if (this.newCouncillor==null || this.councilBalcony==null)
			throw new NullPointerException("Parameters not setted");
		
		Councillor oldCouncillor=this.councilBalcony.substituteCouncillor(this.newCouncillor);
		game.getGameTable().getCouncilReserve().getCouncillors().add(oldCouncillor);
		for (Councillor councillor : game.getGameTable().getCouncilReserve().getCouncillors())
			if (councillor.getColour().getColour().equals(this.newCouncillor.getColour().getColour())) {
				game.getGameTable().getCouncilReserve().removeCouncillor(councillor);
				break;
			}
		game.getCurrentPlayer().incrementCoins(givenCoins);
		
		this.updateClients(game);
		this.nextState(game);
		
		return true;
	}
	
	@Override
	public void updateClients(Game game) {
		game.notifyObserver(new GameTableServerNotify(game, new ArrayList<Player>(game.getAllPlayers()), false));
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageServerNotify("councillor elected succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ " elected a " + this.newCouncillor.getColour() +" councillor in the " + this.councilBalcony.toString()
				+ " balcony", otherPlayers));
	}

	
	@Override
	public ActionDTO map() {
		return new ElectCouncillorDTO();
	}

}