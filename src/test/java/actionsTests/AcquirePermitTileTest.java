package actionsTests;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import server.model.Game;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;
import server.model.gameTable.CardColour;
import server.model.gameTable.Councillor;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.player.Player;
import server.model.stateMachine.State01;
import server.model.stateMachine.State11;

public class AcquirePermitTileTest {


	@Test
	public void testPrice4() throws IOException {
		List<PoliticsCard> discard= new ArrayList<>();
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		discard.add(rainbow1);
		discard.add(rainbow2);
		discard.add(rainbow3);
		discard.add(rainbow4);
		AcquirePermitTile action=new AcquirePermitTile();
		action.setCardsToDescard(discard);
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		game.getCurrentPlayer().getHand().addAll(discard);
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		action.setNumberOfPermitTile(0);
		game.setState(new State11());
		assertEquals(10, game.getCurrentPlayer().getCoins());
		Boolean esito= action.executeAction(game);
		assertEquals(6, game.getCurrentPlayer().getCoins());
		assertEquals(10, game.getCurrentPlayer().getScore());
		assertTrue(game.getCurrentPlayer().getHand().isEmpty());
		assertTrue(esito);
		assertEquals(State01.class, game.getState().getClass());
	}
	
	
	@Test(expected=NullPointerException.class)
	public void testExceptionsSetNumberOfTile() throws IOException{
		List<PoliticsCard> discard= new ArrayList<>();
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		discard.add(rainbow1);
		discard.add(rainbow2);
		discard.add(rainbow3);
		discard.add(rainbow4);
		AcquirePermitTile action=new AcquirePermitTile();
		action.setCardsToDescard(discard);
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		game.setState(new State11());
		action.executeAction(game);
	}
	
	@Test(expected=NullPointerException.class)
	public void testExceptionsSetRegion() throws IOException{
		List<PoliticsCard> discard= new ArrayList<>();
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		discard.add(rainbow1);
		discard.add(rainbow2);
		discard.add(rainbow3);
		discard.add(rainbow4);
		AcquirePermitTile action=new AcquirePermitTile();
		action.setCardsToDescard(discard);
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setNumberOfPermitTile(0);
		game.setState(new State11());
		action.executeAction(game);
	}


	@Test(expected=NullPointerException.class)
	public void testExceptionsSetCardsToDiscard() throws IOException{
		List<PoliticsCard> discard= new ArrayList<>();
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow1= new PoliticsCard(rainbow);
		PoliticsCard rainbow2= new PoliticsCard(rainbow);
		PoliticsCard rainbow3= new PoliticsCard(rainbow);
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		discard.add(rainbow1);
		discard.add(rainbow2);
		discard.add(rainbow3);
		discard.add(rainbow4);
		AcquirePermitTile action=new AcquirePermitTile();
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setNumberOfPermitTile(0);
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		game.setState(new State11());
		action.executeAction(game);
	}
	
	@Test
	public void testErrorNotify() throws IOException{
		List<PoliticsCard> discard= new ArrayList<>();
		CardColour rainbow=new CardColour("Rainbow");
		PoliticsCard rainbow4= new PoliticsCard(rainbow);
		discard.add(rainbow4);
		AcquirePermitTile action=new AcquirePermitTile();
		action.setCardsToDescard(discard);
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setNumberOfPermitTile(0);
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		game.setState(new State11());
		assertFalse(action.executeAction(game));
	}
	
	@Test
	public void testPrice0() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour colour= new CardColour("Blue");
		Councillor x= new Councillor(colour);
		Councillor y= new Councillor(colour);
		Councillor w= new Councillor(colour);
		Councillor z= new Councillor(colour);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(x);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(y);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(z);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(w);
		PoliticsCard card1= new PoliticsCard(colour);
		PoliticsCard card2= new PoliticsCard(colour);
		PoliticsCard card3= new PoliticsCard(colour);
		PoliticsCard card4= new PoliticsCard(colour);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.add(card1);
		discard.add(card2);
		discard.add(card3);
		discard.add(card4);
		AcquirePermitTile action=new AcquirePermitTile();
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		action.setNumberOfPermitTile(0);
		action.setCardsToDescard(discard);
		game.setState(new State11());
		game.getCurrentPlayer().getHand().addAll(discard);
		assertTrue(game.getCurrentPlayer().getCoins()==10);
		assertTrue(game.getCurrentPlayer().getScore()==0);
		assertTrue(action.executeAction(game));
		assertTrue(game.getCurrentPlayer().getCoins()==10);
		assertTrue(game.getCurrentPlayer().getScore()==10);
		assertTrue(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().contains(tile));
	}
	
	@Test
	public void testPriceOf4() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour colour= new CardColour("Blue");
		Councillor x= new Councillor(colour);
		Councillor y= new Councillor(colour);
		Councillor w= new Councillor(colour);
		Councillor z= new Councillor(colour);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(x);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(y);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(z);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(w);
		PoliticsCard card1= new PoliticsCard(colour);
		PoliticsCard card2= new PoliticsCard(colour);
		PoliticsCard card3= new PoliticsCard(colour);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.add(card1);
		discard.add(card2);
		discard.add(card3);
		AcquirePermitTile action=new AcquirePermitTile();
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		action.setNumberOfPermitTile(0);
		action.setCardsToDescard(discard);
		game.setState(new State11());
		game.getCurrentPlayer().getHand().addAll(discard);
		assertTrue(game.getCurrentPlayer().getCoins()==10);
		assertTrue(game.getCurrentPlayer().getScore()==0);
		assertTrue(action.executeAction(game));
		assertTrue(game.getCurrentPlayer().getCoins()==6);
		assertTrue(game.getCurrentPlayer().getScore()==10);
		assertTrue(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().contains(tile));
	}
	
	@Test
	public void testPriceOfSevenCoins() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour colour= new CardColour("Blue");
		Councillor x= new Councillor(colour);
		Councillor y= new Councillor(colour);
		Councillor w= new Councillor(colour);
		Councillor z= new Councillor(colour);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(x);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(y);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(z);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(w);
		PoliticsCard card1= new PoliticsCard(colour);
		PoliticsCard card2= new PoliticsCard(colour);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.add(card1);
		discard.add(card2);
		AcquirePermitTile action=new AcquirePermitTile();
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		action.setNumberOfPermitTile(0);
		action.setCardsToDescard(discard);
		game.setState(new State11());
		game.getCurrentPlayer().getHand().addAll(discard);
		assertTrue(game.getCurrentPlayer().getCoins()==10);
		assertTrue(game.getCurrentPlayer().getScore()==0);
		assertTrue(action.executeAction(game));
		assertTrue(game.getCurrentPlayer().getCoins()==3);
		assertTrue(game.getCurrentPlayer().getScore()==10);
		assertTrue(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().contains(tile));
	}
	
	@Test
	public void testPriceOfTenCoins() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.getCurrentPlayer().getHand().removeAll(game.getCurrentPlayer().getHand());
		CardColour colour= new CardColour("Blue");
		Councillor x= new Councillor(colour);
		Councillor y= new Councillor(colour);
		Councillor w= new Councillor(colour);
		Councillor z= new Councillor(colour);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(x);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(y);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(z);
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().substituteCouncillor(w);
		PoliticsCard card1= new PoliticsCard(colour);
		List<PoliticsCard> discard= new ArrayList<>();
		discard.add(card1);
		AcquirePermitTile action=new AcquirePermitTile();
		Bonus bonus=new ScoreBonus(10);
		Set<Bonus> bonuses=new HashSet<>();
		bonuses.add(bonus);
		PermitTile tile=new PermitTile(null, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).pickUncoveredPermitTile(0);
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[0]=tile;
		action.setChosenRegion(game.getGameTable().getRegionBoards().get(0));
		action.setNumberOfPermitTile(0);
		action.setCardsToDescard(discard);
		game.setState(new State11());
		game.getCurrentPlayer().getHand().addAll(discard);
		assertTrue(game.getCurrentPlayer().getCoins()==10);
		assertTrue(game.getCurrentPlayer().getScore()==0);
		assertTrue(action.executeAction(game));
		assertTrue(game.getCurrentPlayer().getCoins()==0);
		assertTrue(game.getCurrentPlayer().getScore()==10);
		assertTrue(game.getCurrentPlayer().getPlayersPermitTilesTurnedUp().contains(tile));
	}
}
