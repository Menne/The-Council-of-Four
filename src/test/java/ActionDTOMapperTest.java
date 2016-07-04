import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.actionsDTO.bonusActions.ChooseCityActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PickPermitTileActionDTO;
import client.modelDTO.actionsDTO.bonusActions.PurchasedPermitTileActionDTO;
import client.modelDTO.actionsDTO.marketActions.AcceptAnOfferDTO;
import client.modelDTO.actionsDTO.marketActions.MakeAnOfferDTO;
import client.modelDTO.actionsDTO.standardActions.AcquirePermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByPermitTileDTO;
import client.modelDTO.actionsDTO.standardActions.ChangePermitTilesDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorByAssistantDTO;
import client.modelDTO.actionsDTO.standardActions.ElectCouncillorDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.AssistantDTO;
import server.model.Game;
import server.model.actions.bonusActions.ChooseCityBonusAction;
import server.model.actions.bonusActions.PickPermitTileBonusAction;
import server.model.actions.bonusActions.PurchasedPermitTileAction;
import server.model.actions.marketActions.AcceptAnOffer;
import server.model.actions.marketActions.MakeAnOffer;
import server.model.actions.standardActions.AcquirePermitTile;
import server.model.actions.standardActions.BuildByKing;
import server.model.actions.standardActions.BuildByPermitTile;
import server.model.actions.standardActions.ChangePermitTiles;
import server.model.actions.standardActions.ElectCouncillor;
import server.model.actions.standardActions.ElectCouncillorByAssistant;
import server.model.bonuses.Bonus;
import server.model.bonuses.CoinsBonus;
import server.model.bonuses.ScoreBonus;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.CouncillorsReserve;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.mappers.ActionDTOMapper;
import server.model.player.Assistant;
import server.model.player.Player;
import server.model.stateMachine.State11;

public class ActionDTOMapperTest {

	@Test
	public void testAcquirePermitTileMap() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new State11());
		AcquirePermitTileDTO actionDTO= new AcquirePermitTileDTO();
		actionDTO.setNumberOfPermitTile(1);
		List<PoliticsCardDTO> cardsToDescard=new ArrayList<PoliticsCardDTO>();
		PoliticsCardDTO card= new PoliticsCardDTO();
		CardColourDTO colour= new CardColourDTO();
		colour.setName("Orange");
		card.setColour(colour);
		cardsToDescard.add(card);
		actionDTO.setCardsToDescard(cardsToDescard);
		PoliticsCard realCard= new PoliticsCard(new CardColour("Orange"));
		List<PoliticsCard> cardsToDescardReal= new ArrayList<>();
		cardsToDescardReal.add(realCard);
		RegionDTO choosenRegion= new RegionDTO();
		choosenRegion.setName("Sea");
		actionDTO.setChosenRegion(choosenRegion);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		AcquirePermitTile action=mapper.map(actionDTO);
		assertEquals(action.getCardsToDescard(), cardsToDescardReal);
		assertEquals(action.getChosenRegion(), game.getGameTable().getRegionBoards().get(0));
		assertTrue(1==action.getNumberOfPermitTile());
	}
	
	@Test
	public void testBuildByKingMap() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new State11());
		BuildByKingDTO actionDTO= new BuildByKingDTO();
		CityDTO selectedCity= new CityDTO();
		selectedCity.setName("Indur");
		List<PoliticsCardDTO> cardsToDescard= new ArrayList<>();
		PoliticsCardDTO card= new PoliticsCardDTO();
		CardColourDTO colourDTO= new CardColourDTO();
		colourDTO.setName("Rainbow");
		card.setColour(colourDTO);
		cardsToDescard.add(card);
		actionDTO.setCardsToDescard(cardsToDescard);
		actionDTO.setSelectedCity(selectedCity);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		BuildByKing action= mapper.map(actionDTO);
		assertEquals("Indur", action.getSelectedCity().getName());
		assertEquals("Rainbow", action.getCardsToDescard().get(0).getColour().getColour());
	}
	
	@Test
	public void testBuildByPermitTileMap() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		game.setCurrentPlayer(a);
		game.setState(new State11());
		BuildByPermitTileDTO actionDTO= new BuildByPermitTileDTO();
		PermitTileDTO selectedPermitTile= new PermitTileDTO();
		CityDTO selectedCity= new CityDTO();
		selectedCity.setName("Dorful");
		CoinsBonus coinsBonus= new CoinsBonus(3);
		ScoreBonus scoreBonus= new ScoreBonus(4);
		Set<Bonus> bonuses= new HashSet<>();
		bonuses.add(scoreBonus);
		bonuses.add(coinsBonus);
		Set<CityDTO> buildablecities= new HashSet<>();
		City cityDorful= null;
		for(City city1: game.getGameTable().getRegionBoards().get(0).getRegionCities())
			if (city1.getName().equals("Dorful"))
				cityDorful=city1;
		Set<City> cities= new HashSet<>();
		cities.add(cityDorful);
		PermitTile tile= new PermitTile(cities, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		a.getPlayersPermitTilesTurnedUp().add(tile);
		game.setCurrentPlayer(a);
		buildablecities.add(selectedCity);
		selectedPermitTile.setBonuses(bonuses);
		selectedPermitTile.setBuildablecities(buildablecities);
		actionDTO.setSelectedCity(selectedCity);
		actionDTO.setSelectedPermitTile(selectedPermitTile);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		BuildByPermitTile action= mapper.map(actionDTO);
		assertTrue(action.getSelectedPermitTile().getBonuses().contains(scoreBonus));
		assertEquals("Dorful", action.getSelectedCity().getName());
	}
	
	@Test
	public void testChangePermitTilesMap() throws IOException {
		RegionDTO selectedRegion= new RegionDTO();
		selectedRegion.setName("Sea");
		ChangePermitTilesDTO actionDTO= new ChangePermitTilesDTO();
		actionDTO.setSelectedRegion(selectedRegion);
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		ChangePermitTiles action= mapper.map(actionDTO);
		assertTrue(action.getSelectedRegion().getName().equals("Sea"));
	}
	
	@Test
	public void testElectCouncillorMapAndElectCouncillorByAssistant() throws IOException {
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		ElectCouncillorDTO actionDTO= new ElectCouncillorDTO();
		ElectCouncillorByAssistantDTO actionDTO1= new ElectCouncillorByAssistantDTO();
		CouncillorDTO newCouncillor= new CouncillorDTO();
		CouncillorDTO[] councilBalcony= new CouncillorDTO[4];
		CardColourDTO colour= new CardColourDTO();
		colour.setName("bo");
		newCouncillor.setColour(colour);
		councilBalcony[0]=newCouncillor;
		councilBalcony[1]=newCouncillor;
		councilBalcony[2]=newCouncillor;
		councilBalcony[3]=newCouncillor;
		actionDTO.setCouncilBalcony(councilBalcony);
		actionDTO.setNewCouncillor(newCouncillor);
		actionDTO1.setCouncilBalcony(councilBalcony);
		actionDTO1.setNewCouncillor(newCouncillor);
		CardColour realColour= new CardColour("bo");
		Councillor realCouncillor= new Councillor(realColour);
		List<Councillor> counc= new ArrayList<>();
		counc.add(realCouncillor);
		counc.add(realCouncillor);
		counc.add(realCouncillor);
		counc.add(realCouncillor);
		counc.add(realCouncillor);
		counc.add(realCouncillor);
		counc.add(realCouncillor);
		counc.add(realCouncillor);
		CouncillorsReserve reserve= new CouncillorsReserve(counc);
		CouncilBalcony realBalcony= new CouncilBalcony(reserve);
		realBalcony.getCouncillors()[0]=realCouncillor;
		realBalcony.getCouncillors()[1]=realCouncillor;
		realBalcony.getCouncillors()[2]=realCouncillor;
		realBalcony.getCouncillors()[3]=realCouncillor;
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors()[0]=realCouncillor;
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors()[1]=realCouncillor;
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors()[2]=realCouncillor;
		game.getGameTable().getRegionBoards().get(0).getRegionBalcony().getCouncillors()[3]=realCouncillor;
		game.getGameTable().getCouncilReserve().addConcullor(realCouncillor);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		ElectCouncillor action= mapper.map(actionDTO);
		ElectCouncillorByAssistant action1= mapper.map(actionDTO1);
		assertEquals(realCouncillor, action.getNewCouncillor());
		assertEquals(game.getGameTable().getRegionBoards().get(0).getRegionBalcony(), action.getCouncilBalcony());
		assertEquals(realCouncillor, action1.getNewCouncillor());
		assertEquals(game.getGameTable().getRegionBoards().get(0).getRegionBalcony(), action1.getCouncilBalcony());
		
	}
	
	@Test
	public void testMakeAnOfferMapAndAcceptAnOffer() throws IOException {
		MakeAnOfferDTO actionDTO= new MakeAnOfferDTO();
		OfferDTO offer1= new OfferDTO();
		OfferDTO offer2= new OfferDTO();
		offer1.setPrice(1);
		offer2.setPrice(2);
		String player= "player1";
		offer1.setOfferingPlayer(player);
		offer2.setOfferingPlayer(player);
		AssistantDTO assistantDTO= new AssistantDTO();
		PoliticsCardDTO cardDTO= new PoliticsCardDTO();
		CardColourDTO colourDTO= new CardColourDTO();
		colourDTO.setName("Blue");
		cardDTO.setColour(colourDTO);
		offer1.setOfferedObjectDTO(assistantDTO);
		offer2.setOfferedObjectDTO(cardDTO);
		actionDTO.addOfferToList(offer1);
		actionDTO.addOfferToList(offer2);
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("player1");
		players.add(a);
		game.start(players);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		MakeAnOffer action= mapper.map(actionDTO);
		Assistant assistant= new Assistant();
		CardColour colour= new CardColour("Blue");
		PoliticsCard politicsCard= new PoliticsCard(colour);
		assertEquals(action.getOfferingObjects().get(0).getOfferedObject(), assistant);
		assertEquals(action.getOfferingObjects().get(1).getOfferedObject(), politicsCard);
		assertTrue(action.getOfferingObjects().size()==2);
		assertEquals(a, action.getOfferingObjects().get(0).getOfferingPlayer());
		assertEquals(a, action.getOfferingObjects().get(1).getOfferingPlayer());
		assertTrue(action.getOfferingObjects().get(0).getPrice()==1);
		assertTrue(action.getOfferingObjects().get(1).getPrice()==2);	
		AcceptAnOfferDTO accept1DTO= new AcceptAnOfferDTO();
		AcceptAnOfferDTO accept2DTO= new AcceptAnOfferDTO();
		accept1DTO.setOffer(offer1);
		accept2DTO.setOffer(offer2);
		AcceptAnOffer accept1 = mapper.map(accept1DTO); 
		AcceptAnOffer accept2 = mapper.map(accept2DTO); 
		assertEquals(accept1.getOffer().getOfferedObject(), assistant);
		assertEquals(accept2.getOffer().getOfferedObject(), politicsCard);
		assertEquals(accept1.getOffer().getOfferingPlayer(), a);
		assertEquals(accept2.getOffer().getOfferingPlayer(), a);
		assertTrue(accept1.getOffer().getPrice()==1);
		assertTrue(accept2.getOffer().getPrice()==2);

	}
	
	@Test
	public void testChooseCityBonusActionMap() throws IOException {
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("player1");
		players.add(a);
		game.start(players);
		ChooseCityActionDTO actionDTO= new ChooseCityActionDTO(1);
		List<CityDTO> selectedCitiesDTO= new ArrayList<>();
		CityDTO arkon= new CityDTO();
		arkon.setName("Arkon");
		selectedCitiesDTO.add(arkon);
		actionDTO.setSelectedCities(selectedCitiesDTO);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		ChooseCityBonusAction action= mapper.map(actionDTO);
		City city=null;
		for(City c: game.getGameTable().getRegionBoards().get(0).getRegionCities())
			if(c.getName().equals("Arkon"))
				city= c;
		assertEquals(city, action.getSelectedCities().get(0));
		assertTrue(action.getNumberOfCities()==1);
	}
	
	@Test
	public void testPurchasedPermitTileActionMap() throws IOException {
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("player1");
		players.add(a);
		game.start(players);
		Set<CityDTO> buildablecities= new HashSet<>();
		Set<Bonus> bonuses= new HashSet<>();
		CityDTO city= new CityDTO();
		city.setName("Dorful");
		ScoreBonus bonus= new ScoreBonus(7);
		buildablecities.add(city);
		bonuses.add(bonus);
		PermitTileDTO permitTileDTO= new PermitTileDTO(buildablecities, bonuses);
		City cityDorful= null;
		for(City city1: game.getGameTable().getRegionBoards().get(0).getRegionCities())
			if (city1.getName().equals("Dorful"))
				cityDorful=city1;
		Set<City> cities= new HashSet<>();
		cities.add(cityDorful);
		PermitTile tile= new PermitTile(cities, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.setCurrentPlayer(a);
		a.getPlayersPermitTilesTurnedUp().add(tile);
		PurchasedPermitTileActionDTO actionDTO= new PurchasedPermitTileActionDTO();
		actionDTO.setPermitTile(permitTileDTO);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		PurchasedPermitTileAction action= mapper.map(actionDTO);
		assertEquals(tile, action.getSelectedPermitTile());
	}
	
	@Test
	public void testPickPermitTileBonusActionMap() throws IOException {
		Game game= new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("player1");
		players.add(a);
		game.start(players);
		Set<CityDTO> buildablecities= new HashSet<>();
		Set<Bonus> bonuses= new HashSet<>();
		CityDTO city= new CityDTO();
		city.setName("Dorful");
		ScoreBonus bonus= new ScoreBonus(7);
		buildablecities.add(city);
		bonuses.add(bonus);
		City cityDorful= null;
		for(City city1: game.getGameTable().getRegionBoards().get(0).getRegionCities())
			if (city1.getName().equals("Dorful"))
				cityDorful=city1;
		Set<City> cities= new HashSet<>();
		cities.add(cityDorful);
		PermitTile tile= new PermitTile(cities, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		game.getGameTable().getRegionBoards().get(0).getUncoveredPermitTiles()[1]=tile;
		PickPermitTileActionDTO actionDTO= new PickPermitTileActionDTO();
		actionDTO.setNumberOfPermitTile(1);
		RegionDTO regionDTO= new RegionDTO();
		regionDTO.setName("Sea");
		actionDTO.setSelectedRegion(regionDTO);
		ActionDTOMapper mapper= new ActionDTOMapper(game);
		PickPermitTileBonusAction action= mapper.map(actionDTO);
		assertTrue(action.getNumberOfPermitTile()==1);
		assertTrue(game.getGameTable().getRegionBoards().get(0)==action.getSelectedRegion());
	}
}
