package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import players.Player;
import server.model.Game;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.Emporium;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.stateMachine.State01;
import server.model.stateMachine.State11;

public class BuildByPermitTileTest {

	@Test
	public void testExecuteAction() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(5);
		game.getCurrentPlayer().incrementCoins(10);
		assertEquals(20, game.getCurrentPlayer().getCoins());
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		game.getCurrentPlayer().addCardToHand(rainbow1);
		game.getCurrentPlayer().addCardToHand(rainbow2);
		game.getCurrentPlayer().addCardToHand(rainbow3);
		game.getCurrentPlayer().addCardToHand(rainbow4);
		game.setState(new State11());
		PermitTile tile= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0];
		AcquirePermitTile acquire= new AcquirePermitTile();
		acquire.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		acquire.setNumberOfPermitTile(0);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.addAll(game.getCurrentPlayer().getHand());
		acquire.setCardsToDescard(discard);
		assertTrue(acquire.executeAction(game));
		assertEquals(State01.class, game.getState().getClass());
		assertTrue(tile==game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));
		Iterator<City> it= tile.getBuildableCities().iterator();
		City selectedCity= it.next();
	//	Set<Bonus> bonuses= selectedCity.getRewardToken();
		game.setState(new State11());
		BuildByPermitTile action= new BuildByPermitTile();
		action.setSelectedPermitTile(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));;
		action.setSelectedCity(selectedCity);
		assertFalse(game.getCurrentPlayer().getPlayersPermitTilesTurnedDown().contains(tile));
		assertTrue(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().contains(tile));
		assertTrue(action.executeAction(game));
		assertTrue(game.getCurrentPlayer().getPlayersPermitTilesTurnedDown().contains(tile));
		assertFalse(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().contains(tile));
		Iterator<Emporium> itEmp= selectedCity.getCityEmporiums().iterator();
		Emporium e=itEmp.next();
		assertEquals(game.getCurrentPlayer(), e.getEmporiumsPlayer());
			
	}

	
	@Test(expected=NullPointerException.class)
	public void testExceptionIfSelectedPermitTileIsNull() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(5);
		game.getCurrentPlayer().incrementCoins(10);
		assertEquals(20, game.getCurrentPlayer().getCoins());
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		game.getCurrentPlayer().addCardToHand(rainbow1);
		game.getCurrentPlayer().addCardToHand(rainbow2);
		game.getCurrentPlayer().addCardToHand(rainbow3);
		game.getCurrentPlayer().addCardToHand(rainbow4);
		game.setState(new State11());
		PermitTile tile= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0];
		AcquirePermitTile acquire= new AcquirePermitTile();
		acquire.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		acquire.setNumberOfPermitTile(0);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.addAll(game.getCurrentPlayer().getHand());
		acquire.setCardsToDescard(discard);
		assertTrue(acquire.executeAction(game));
		assertEquals(State01.class, game.getState().getClass());
		assertTrue(tile==game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));
		Iterator<City> it= tile.getBuildableCities().iterator();
		City selectedCity= it.next();
	//	Set<Bonus> bonuses= selectedCity.getRewardToken();
		game.setState(new State11());
		BuildByPermitTile action= new BuildByPermitTile();
		action.setSelectedCity(selectedCity);
		action.executeAction(game);
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testexceptionIfSelectedCityIsNull() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().incrementAssistants(5);
		game.getCurrentPlayer().incrementCoins(10);
		assertEquals(20, game.getCurrentPlayer().getCoins());
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		game.getCurrentPlayer().addCardToHand(rainbow1);
		game.getCurrentPlayer().addCardToHand(rainbow2);
		game.getCurrentPlayer().addCardToHand(rainbow3);
		game.getCurrentPlayer().addCardToHand(rainbow4);
		game.setState(new State11());
		PermitTile tile= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0];
		AcquirePermitTile acquire= new AcquirePermitTile();
		acquire.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		acquire.setNumberOfPermitTile(0);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.addAll(game.getCurrentPlayer().getHand());
		acquire.setCardsToDescard(discard);
		assertTrue(acquire.executeAction(game));
		assertEquals(State01.class, game.getState().getClass());
		assertTrue(tile==game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));
	//	Set<Bonus> bonuses= selectedCity.getRewardToken();
		game.setState(new State11());
		BuildByPermitTile action= new BuildByPermitTile();
		action.setSelectedPermitTile(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));;
		action.executeAction(game);
		
	}
	
	@Test
	public void testIfExecuteActionReturnsFalseIfPlayerHasTooFewAssistants() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		Player b= new Player("Luca");
		a.setPlayerNumber(1);
		b.setPlayerNumber(2);
		players.add(a);
		players.add(b);
		game.setCurrentPlayer(a);
		game.start(players);
		game.getCurrentPlayer().decrementAssistants(1);
		game.getCurrentPlayer().incrementCoins(10);
		assertEquals(20, game.getCurrentPlayer().getCoins());
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		game.getCurrentPlayer().addCardToHand(rainbow1);
		game.getCurrentPlayer().addCardToHand(rainbow2);
		game.getCurrentPlayer().addCardToHand(rainbow3);
		game.getCurrentPlayer().addCardToHand(rainbow4);
		game.setState(new State11());
		PermitTile tile= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0];
		AcquirePermitTile acquire= new AcquirePermitTile();
		acquire.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		acquire.setNumberOfPermitTile(0);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.addAll(game.getCurrentPlayer().getHand());
		acquire.setCardsToDescard(discard);
		assertTrue(acquire.executeAction(game));
		assertEquals(State01.class, game.getState().getClass());
		assertTrue(tile==game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));
		Iterator<City> it= tile.getBuildableCities().iterator();
		City selectedCity= it.next();
	//	Set<Bonus> bonuses= selectedCity.getRewardToken();
		game.setState(new State11());
		BuildByPermitTile action= new BuildByPermitTile();
		action.setSelectedPermitTile(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));;
		action.setSelectedCity(selectedCity);
		action.executeAction(game);
		Emporium emp= new Emporium(b);
		selectedCity.addEmporium(emp);
		assertFalse(action.executeAction(game));
	}
	
	@Test
	public void testIfExecuteActionReturnsFalseIfTileDoesntContainsSelectedCity() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.setCurrentPlayer(a);
		game.start(players);
		game.getCurrentPlayer().decrementAssistants(1);
		game.getCurrentPlayer().incrementCoins(10);
		assertEquals(20, game.getCurrentPlayer().getCoins());
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		game.getCurrentPlayer().addCardToHand(rainbow1);
		game.getCurrentPlayer().addCardToHand(rainbow2);
		game.getCurrentPlayer().addCardToHand(rainbow3);
		game.getCurrentPlayer().addCardToHand(rainbow4);
		game.setState(new State11());
		PermitTile tile= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[1];
		AcquirePermitTile acquire= new AcquirePermitTile();
		acquire.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		acquire.setNumberOfPermitTile(0);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.addAll(game.getCurrentPlayer().getHand());
		acquire.setCardsToDescard(discard);
		acquire.executeAction(game);
		Iterator<City> it= tile.getBuildableCities().iterator();
		City selectedCity= it.next();
	//	Set<Bonus> bonuses= selectedCity.getRewardToken();
		game.setState(new State11());
		BuildByPermitTile action= new BuildByPermitTile();
		action.setSelectedPermitTile(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));;
		action.setSelectedCity(selectedCity);
		action.executeAction(game);
		assertFalse(action.executeAction(game));
	}
	
	@Test
	public void testIfExecuteActionReturnsFalseIfPlayerHasAlreadyAnEmporiumInTheSelectedCity() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.setCurrentPlayer(a);
		game.start(players);
		game.getCurrentPlayer().decrementAssistants(1);
		game.getCurrentPlayer().incrementCoins(10);
		assertEquals(20, game.getCurrentPlayer().getCoins());
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		game.getCurrentPlayer().addCardToHand(rainbow1);
		game.getCurrentPlayer().addCardToHand(rainbow2);
		game.getCurrentPlayer().addCardToHand(rainbow3);
		game.getCurrentPlayer().addCardToHand(rainbow4);
		game.setState(new State11());
		PermitTile tile= game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0];
		AcquirePermitTile acquire= new AcquirePermitTile();
		acquire.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		acquire.setNumberOfPermitTile(0);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.addAll(game.getCurrentPlayer().getHand());
		acquire.setCardsToDescard(discard);
		assertTrue(acquire.executeAction(game));
		assertEquals(State01.class, game.getState().getClass());
		assertTrue(tile==game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));
		Iterator<City> it= tile.getBuildableCities().iterator();
		City selectedCity= it.next();
	//	Set<Bonus> bonuses= selectedCity.getRewardToken();
		game.setState(new State11());
		BuildByPermitTile action= new BuildByPermitTile();
		action.setSelectedPermitTile(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().get(0));;
		action.setSelectedCity(selectedCity);
		action.executeAction(game);
		Emporium emp= new Emporium(a);
		selectedCity.addEmporium(emp);
		assertFalse(action.executeAction(game));
	}
}
