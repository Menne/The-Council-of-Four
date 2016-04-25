package model;

import java.util.List;
import java.util.Set;

import gameStuff.ColourBonusTile;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.CouncillorsRiserve;
import gameStuff.KingRewardTile;
import gameStuff.NobilityTrack;
import gameStuff.PoliticsDeck;
import gameStuff.RegionBoard;
import observerPattern.Observable;
import players.Player;

public class Model extends Observable {

	private final List<Player> players;
	private Player currentPlayer;
	private GameState gameState;
	private final CouncillorsRiserve councillorsReserve;
	private final CouncilBalcony councilOfKing;
	private final RegionBoard[] map;
	private final NobilityTrack nobilityTrack;
	private final PoliticsDeck politicsDeck;
	private final PoliticsDeck politicsDiscard;
	private final Set<KingRewardTile> kingRewardTiles;
	private final Set<ColourBonusTile> colourBonusTiles;
	
	
	/**
	 * 
	 * @param table
	 * @param players
	 */
	public Model(List<Player> players, CouncillorsRiserve councillorsRiserve,
			CouncilBalcony councilOfKing, RegionBoard[] map, NobilityTrack nobilityTrack,
			PoliticsDeck politicsDeck, PoliticsDeck politicsDiscard,
			Set<KingRewardTile> kingRewardTiles, Set<ColourBonusTile> colourBonusTiles){
		
		this.players=players;
		this.currentPlayer=players.get(0);
		this.gameState=GameState.RUNNING;
		this.councillorsReserve=councillorsRiserve;
		this.councilOfKing=councilOfKing;
		this.map=map;
		this.nobilityTrack=nobilityTrack;
		this.politicsDeck=politicsDeck;
		this.politicsDiscard=politicsDiscard;
		this.kingRewardTiles=kingRewardTiles;
		this.colourBonusTiles=colourBonusTiles;	
	}

	public List<Player> getPlayers() {
		return players;
	}

	public Player getCurrentPlayer() {
		return currentPlayer;
	}

	public GameState getGameState() {
		return gameState;
	}

	public CouncillorsRiserve getCouncillorsReserve() {
		return councillorsReserve;
	}

	public CouncilBalcony getCouncilOfKing() {
		return councilOfKing;
	}

	public RegionBoard[] getMap() {
		return map;
	}

	public NobilityTrack getNobilityTrack() {
		return nobilityTrack;
	}

	public PoliticsDeck getPoliticsDeck() {
		return politicsDeck;
	}

	public PoliticsDeck getPoliticsDiscard() {
		return politicsDiscard;
	}

	public Set<KingRewardTile> getKingRewardTiles() {
		return kingRewardTiles;
	}

	public Set<ColourBonusTile> getColourBonusTiles() {
		return colourBonusTiles;
	}

	/**
	 * Set the gameState with the parameter
	 * Notify the Observers. 
	 * @param gameState Is the new gameState
	 */
	public void setGameState(GameState gameState) {
		this.gameState = gameState;
		this.notifyObservers(this.gameState);
	}
	
	/**
	 * Set the currentPlayer with the next Player.
	 * Notify the change to the Observers.
	 */
	public void nextPlayer(){
		this.currentPlayer=this.players.get(this.players.indexOf(currentPlayer)+1);
		this.notifyObservers(this.currentPlayer);
	}
	
	/**
	 * Substitute a new councillor from the council reserve to the council of the king.
	 * Notify the changes to the observers.
	 * @param newCouncillor Is the new councillor
	 * @return FALSE if the new councillor is not present in the councillors reserve
	 * TRUE otherwise
	 */
	public boolean substituteKingCouncillor(Councillor newCouncillor){
		Councillor temp;
		if(this.councillorsReserve.getCouncillors().contains(newCouncillor)){
			temp= this.councilOfKing.substituteCouncillor(newCouncillor);
			this.notifyObservers(this.councilOfKing);
			this.councillorsReserve.getCouncillors().remove(newCouncillor);
			this.councillorsReserve.getCouncillors().add(temp);
			this.notifyObservers(this.councillorsReserve);
			return true;
		}
		else
			return false;	
	}
}
