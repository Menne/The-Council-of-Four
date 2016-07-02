package actionsTests;

import static org.junit.Assert.*;


import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import server.model.Game;
import server.model.actions.standardActions.BuildByKing;
import server.model.bonus.Bonus;
import server.model.bonus.CoinsBonus;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.Councillor;
import server.model.gameTable.PoliticsCard;
import server.model.player.Player;
import server.model.stateMachine.SellingState;
import server.model.stateMachine.State01;
import server.model.stateMachine.State10;
import server.model.stateMachine.State11;

public class BuildByKingTest {

	@Test(expected=NullPointerException.class)
	public void testException() {
		Game game=new Game();
		BuildByKing action= new BuildByKing();
		action.executeAction(game);
	}

	@Test
	public void testExecuteActionwithFourCards() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		City city1= null;
		City city2= null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getName().equals("Juvelar"))
				city1=city;
			if(city.getName().equals("Indur"))
				city2=city;
		}
		int oldCoins= game.getCurrentPlayer().getCoins();
		for(Bonus bonus: city2.getRewardToken().getRewardTokenBonus())
			if(bonus.getClass()==CoinsBonus.class)
				oldCoins= oldCoins+(bonus.hashCode()-31);
		assertTrue(game.getGameTable().getKing().getCity()==city1);
		CardColour blu= new CardColour("Blu");
		Councillor bluCounc= new Councillor(blu);
		PoliticsCard bluCard= new PoliticsCard(blu);
		a.getHand().clear();
		a.addCardToHand(bluCard);
		a.addCardToHand(bluCard);
		a.addCardToHand(bluCard);
		a.addCardToHand(bluCard);
		game.getGameTable().getCouncilOfKing().getCouncillors()[0]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[1]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[2]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[3]=bluCounc;
		List<PoliticsCard> cardsToDescard= new ArrayList<>();
		cardsToDescard.addAll(a.getHand());
		BuildByKing action= new BuildByKing();
		action.setCardsToDescard(cardsToDescard);
		action.setSelectedCity(city2);
		assertTrue(action.executeAction(game));
		assertEquals(oldCoins-2, a.getCoins());
		assertEquals(State01.class, game.getState().getClass());
		assertEquals(BuildByKingDTO.class, action.map().getClass());
	}
	
	@Test
	public void testExecuteActionwithThreeCards() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State10());
		City city1= null;
		City city2= null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getName().equals("Juvelar"))
				city1=city;
			if(city.getName().equals("Indur"))
				city2=city;
		}
		int oldCoins= game.getCurrentPlayer().getCoins();
		for(Bonus bonus: city2.getRewardToken().getRewardTokenBonus())
			if(bonus.getClass()==CoinsBonus.class)
				oldCoins= oldCoins+(bonus.hashCode()-31);
		assertTrue(game.getGameTable().getKing().getCity()==city1);
		CardColour blu= new CardColour("Blu");
		Councillor bluCounc= new Councillor(blu);
		PoliticsCard bluCard= new PoliticsCard(blu);
		a.getHand().clear();
		a.addCardToHand(bluCard);
		a.addCardToHand(bluCard);
		a.addCardToHand(bluCard);
		game.getGameTable().getCouncilOfKing().getCouncillors()[0]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[1]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[2]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[3]=bluCounc;
		List<PoliticsCard> cardsToDescard= new ArrayList<>();
		cardsToDescard.addAll(a.getHand());
		BuildByKing action= new BuildByKing();
		action.setCardsToDescard(cardsToDescard);
		action.setSelectedCity(city2);
		assertTrue(action.executeAction(game));
		assertEquals(oldCoins-6, a.getCoins());
		assertEquals(SellingState.class, game.getState().getClass());
	}
	
	@Test
	public void testExecuteActionwithTwoCards() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		City city1= null;
		City city2= null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getName().equals("Juvelar"))
				city1=city;
		}
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getName().equals("Indur"))
				city2=city;
		}
		int oldCoins= game.getCurrentPlayer().getCoins();
		for(Bonus bonus: city2.getRewardToken().getRewardTokenBonus())
			if(bonus.getClass()==CoinsBonus.class)
				oldCoins= oldCoins+(bonus.hashCode()-31);
		assertTrue(game.getGameTable().getKing().getCity()==city1);
		CardColour blu= new CardColour("Blu");
		Councillor bluCounc= new Councillor(blu);
		PoliticsCard bluCard= new PoliticsCard(blu);
		a.getHand().clear();
		a.addCardToHand(bluCard);
		a.addCardToHand(bluCard);
		game.getGameTable().getCouncilOfKing().getCouncillors()[0]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[1]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[2]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[3]=bluCounc;
		List<PoliticsCard> cardsToDescard= new ArrayList<>();
		cardsToDescard.addAll(a.getHand());
		BuildByKing action= new BuildByKing();
		action.setCardsToDescard(cardsToDescard);
		action.setSelectedCity(city2);
		assertTrue(action.executeAction(game));
		assertEquals(oldCoins-9, a.getCoins());
		assertEquals(State01.class, game.getState().getClass());
	}
	
	@Test
	public void testExecuteActionwithOneCard() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		a.setCoins(20);
		players.add(a);
		game.start(players);
		game.setState(new State11());
		City city1= null;
		City city2= null;
		for(City city: game.getGameTable().getRegionBoards().get(1).getRegionCities()){
			if(city.getName().equals("Juvelar"))
				city1=city;
			if(city.getName().equals("Indur"))
				city2=city;
		}
		game.getCurrentPlayer().setCoins(15);
		int oldCoins= game.getCurrentPlayer().getCoins();
		for(Bonus bonus: city2.getRewardToken().getRewardTokenBonus())
			if(bonus.getClass()==CoinsBonus.class)
				oldCoins= oldCoins+(bonus.hashCode()-31);
		assertTrue(game.getGameTable().getKing().getCity()==city1);
		CardColour blu= new CardColour("Blu");
		Councillor bluCounc= new Councillor(blu);
		PoliticsCard bluCard= new PoliticsCard(blu);
		a.getHand().clear();
		a.addCardToHand(bluCard);
		game.getGameTable().getCouncilOfKing().getCouncillors()[0]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[1]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[2]=bluCounc;
		game.getGameTable().getCouncilOfKing().getCouncillors()[3]=bluCounc;
		List<PoliticsCard> cardsToDescard= new ArrayList<>();
		cardsToDescard.addAll(a.getHand());
		BuildByKing action= new BuildByKing();
		action.setCardsToDescard(cardsToDescard);
		action.setSelectedCity(city2);
		assertTrue(action.executeAction(game));
		assertEquals(oldCoins-12, a.getCoins());
		assertEquals(State01.class, game.getState().getClass());
	}
}
