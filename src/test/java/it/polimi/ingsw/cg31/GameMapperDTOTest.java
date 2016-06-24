package it.polimi.ingsw.cg31;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.junit.Test;

import client.modelDTO.gameTableDTO.BonusTileDTO;
import client.modelDTO.gameTableDTO.CardColourDTO;
import client.modelDTO.gameTableDTO.CityColourDTO;
import client.modelDTO.gameTableDTO.CityDTO;
import client.modelDTO.gameTableDTO.CouncillorDTO;
import client.modelDTO.gameTableDTO.GameTableDTO;
import client.modelDTO.gameTableDTO.GenericPlayerDTO;
import client.modelDTO.gameTableDTO.PermitTileDTO;
import client.modelDTO.gameTableDTO.PoliticsCardDTO;
import client.modelDTO.gameTableDTO.RegionDTO;
import client.modelDTO.gameTableDTO.RewardTokenDTO;
import client.modelDTO.marketDTO.MarketDTO;
import client.modelDTO.marketDTO.OfferDTO;
import client.modelDTO.playerDTO.AssistantDTO;
import client.modelDTO.playerDTO.ClientPlayerDTO;
import server.model.Game;
import server.model.bonus.Bonus;
import server.model.bonus.ScoreBonus;
import server.model.gameMapper.GameDTOMapper;
import server.model.gameTable.Assistant;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.CityColour;
import server.model.gameTable.ColourBonusTile;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.KingBonusTile;
import server.model.gameTable.PermitDeck;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsCard;
import server.model.gameTable.RegionBoard;
import server.model.gameTable.RegionBonusTile;
import server.model.gameTable.RewardToken;
import server.model.market.Market;
import server.model.market.Offer;
import server.model.player.Player;

public class GameMapperDTOTest {

	@Test
	public void testGameTableMap() throws IOException {
		Game realObject=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		realObject.start(players);
		GameDTOMapper mapper= new GameDTOMapper();
		GameTableDTO gameTableDTO= mapper.gameTableMap(realObject);
		assertTrue(gameTableDTO.getCurrentPlayer()==realObject.getCurrentPlayer().getName());
		assertTrue(gameTableDTO.getClientNobilityTrack()==(ArrayList<Set<Bonus>>) realObject.getGameTable().getNobilityTrack().getTrack());
		assertTrue(gameTableDTO.getKing()==realObject.getGameTable().getKing().getCity().getName());
		}
	
	@Test
	public void testRegionMap() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		GameDTOMapper mapper= new GameDTOMapper();
		RegionBoard realObject= game.getGameTable().getRegionBoards().get(0);
		RegionDTO regionDTO=mapper.regionMap(realObject);
		assertTrue(regionDTO.getName()==realObject.getName());
		realObject.getRegionBalcony();
		assertTrue(regionDTO.getBalcony().length==CouncilBalcony.getNumberofcouncillors());
		assertTrue(regionDTO.getCities().size()==realObject.getRegionCities().size());
		assertTrue(regionDTO.getUncoveredPermitTiles().length== realObject.getUncoveredPermitTiles().length);

	}
	
	@Test
	public void testMarketMap() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		players.add(a);
		game.start(players);
		GameDTOMapper mapper= new GameDTOMapper();
		Market realObject= new Market();
		Offer offer= new Offer(a, null, 0);
		realObject.addOffer(offer);
		realObject.addPlayer(a);
		MarketDTO marketDTO=mapper.marketMap(realObject);
		assertTrue(marketDTO.getOffersList().get(0).getPrice()==realObject.getOffersList().get(0).getPrice());
		assertTrue(marketDTO.getBuyingPlayerList().get(0)==realObject.getBuyingPlayerList().get(0).getName());
		assertEquals(marketDTO.getSellingPlayerList().size(), realObject.getSellingPlayerList().size());
	}
	
	@Test
	public void testClientPlayerMap() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player realObject = new Player("Andre");
		players.add(realObject);
		game.start(players);
		GameDTOMapper mapper= new GameDTOMapper();
		ClientPlayerDTO clientPlayerDTO= mapper.clientPlayerMap(realObject);
		assertTrue(clientPlayerDTO.getName()== realObject.getName());
		assertTrue(clientPlayerDTO.getPlayerNumber()==realObject.getPlayerNumber());
		assertTrue(clientPlayerDTO.getHand().get(0).getColour().getName()== realObject.getHand().get(0).getColour().getColour());
		assertTrue(clientPlayerDTO.getAvailablePermitTiles().size()==realObject.getPlayersPermitTilesTurnedUp().size());
		assertTrue(clientPlayerDTO.getCoveredPermitTiles().size()==realObject.getPlayersPermitTilesTurnedDown().size());
		assertEquals(clientPlayerDTO.getAssistants().size(), realObject.getNumberOfAssistants());
	}
	
	@Test
	public void testCardColouMap(){
		CardColour realObject= new CardColour("blu");
		GameDTOMapper mapper= new GameDTOMapper();
		CardColourDTO cardColourDTO= mapper.cardColourMap(realObject);
		assertTrue(cardColourDTO.getName()== realObject.getColour());
	}
	
	@Test
	public void testPoliticsCardMap(){
		CardColour colour= new CardColour("blu");
		PoliticsCard realObject= new PoliticsCard(colour);
		GameDTOMapper mapper= new GameDTOMapper();
		PoliticsCardDTO politicsCardDTO=mapper.politicsCardMap(realObject);
		assertTrue(politicsCardDTO.getColour().getName()==realObject.getColour().getColour());
	}
	
	@Test
	public void testCouncillorMap(){
		CardColour colour= new CardColour("blu");
		Councillor realObject= new Councillor(colour);
		GameDTOMapper mapper= new GameDTOMapper();
		CouncillorDTO councillorDTO= mapper.councillorMap(realObject);
		assertTrue(realObject.getColour().getColour()== councillorDTO.getColour().getName());
	}
	
	@Test
	public void testCityColourMap(){
		ColourBonusTile bonus= new ColourBonusTile(new ScoreBonus(1), "Blue");
		CityColour realObject= new CityColour("Blue", bonus);
		GameDTOMapper mapper= new GameDTOMapper();
		CityColourDTO colour= mapper.cityColourMap(realObject);
		assertTrue(realObject.getName()== colour.getName());
	}
	
	@Test
	public void testCityMap() throws IOException {
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		CityColour colour= new CityColour("Bluee", new ColourBonusTile(new ScoreBonus(1), "Blue"));
		List<RewardToken> rewardTokenList= new ArrayList<>();
		Set<Bonus> rewardTokenBonus= new HashSet<>();
		rewardTokenBonus.add(new ScoreBonus(1));
		rewardTokenList.add(new RewardToken(rewardTokenBonus));
		City realObject= new City("city", game.getGameTable().getRegionBoards().get(0), colour, rewardTokenList);
		GameDTOMapper mapper= new GameDTOMapper();
		CityDTO cityDTO= mapper.cityMap(realObject);
		assertTrue(cityDTO.getBuildedEmporiums().size()==realObject.getCityEmporiums().size());
		assertTrue(cityDTO.getColour().getName()== realObject.getColour().getName());
		assertTrue(cityDTO.getName()==realObject.getName());
		assertTrue(cityDTO.getRewardToken().getBonuses()==realObject.getRewardToken().getRewardTokenBonus());
	}
	
	@Test
	public void testGenericPlayerMap() {
		Player realObject= new Player("Andrea");
		realObject.setCoins(10);
		realObject.setNobility(10);
		realObject.setPlayerNumber(1);
		realObject.setScore(10);
		realObject.incrementAssistants(1);
		GameDTOMapper mapper= new GameDTOMapper();
		GenericPlayerDTO genericPlayerDTO= mapper.genericPlayerMap(realObject);
		assertTrue(realObject.getName()==genericPlayerDTO.getName());
		assertTrue(realObject.getPlayerNumber()==genericPlayerDTO.getPlayerNumber());
		assertTrue(realObject.getNumberOfAssistants()==genericPlayerDTO.getAssistants());
		assertTrue(realObject.getScore()==genericPlayerDTO.getScore());
		assertTrue(realObject.getNobility()==genericPlayerDTO.getNobility());
		assertTrue(realObject.getCoins()==genericPlayerDTO.getCoins());
		assertTrue(realObject.getRemainigEmporiums().size()==genericPlayerDTO.getEmporiums());
		assertTrue(realObject.getHand().size()==genericPlayerDTO.getHand());
		assertTrue(realObject.getPlayersPermitTilesTurnedUp().size()==genericPlayerDTO.getAvailablePermitTiles().size());
		assertTrue(realObject.getPlayersFinalBonus().size()==genericPlayerDTO.getPlayersFinalBonus().size());
		assertTrue(realObject.getPlayersPermitTilesTurnedDown().size()==genericPlayerDTO.getNumberOfCoveredTiles());
	}
	
	@Test
	public void testPermitTileMap() throws IOException{
		Game game=new Game();
		List<Player> players = new ArrayList<>();
		Player a = new Player("Andre");
		a.setPlayerNumber(1);
		players.add(a);
		game.start(players);
		Set<City> buildableCities= new HashSet<>();
		ColourBonusTile colourBonus= new ColourBonusTile( new ScoreBonus(1), "ooo");
		CityColour colour= new CityColour("blue", colourBonus);
		List<RewardToken> rewardTokenList= new ArrayList<>();
		Set<Bonus> rewardTokenBonus= new HashSet<>();
		rewardTokenBonus.add(new ScoreBonus(1));
		rewardTokenList.add(new RewardToken(rewardTokenBonus));
		City city= new City("ok", game.getGameTable().getRegionBoards().get(0), colour , rewardTokenList);
		buildableCities.add(city);
		Set<Bonus> bonuses= new HashSet<>();
		bonuses.add(new ScoreBonus(1));
		PermitTile realObject= new PermitTile(buildableCities, bonuses, game.getGameTable().getRegionBoards().get(0).getRegionPermitDeck());
		GameDTOMapper mapper= new GameDTOMapper();
		PermitTileDTO permitTileDTO= mapper.permitTileMap(realObject);
		assertTrue(realObject.getBonuses()== permitTileDTO.getBonuses());
		assertTrue(realObject.getBuildableCities().size()== permitTileDTO.getBuildablecities().size());
	}
	
	@Test
	public void testAssistantOfferMap() {
		Player player= new Player("a");
		Assistant assistant= new Assistant();
		int price=10;
		Offer realObject= new Offer(player, assistant, price);
		GameDTOMapper mapper= new GameDTOMapper();
		OfferDTO offerDTO= mapper.offerMap(realObject);
		assertTrue(offerDTO.getOfferedObjectDTO().getClass()==AssistantDTO.class);
		assertTrue(offerDTO.getOfferingPlayer()==realObject.getOfferingPlayer().getName());
		assertTrue(offerDTO.getPrice()==realObject.getPrice());
	}
	
	@Test
	public void testPoliticCardOfferMap() {
		Player player= new Player("a");
		CardColour colour= new CardColour("blu");
		PoliticsCard card= new PoliticsCard(colour);
		int price=10;
		Offer realObject= new Offer(player, card, price);
		GameDTOMapper mapper= new GameDTOMapper();
		OfferDTO offerDTO= mapper.offerMap(realObject);
		assertTrue(offerDTO.getOfferedObjectDTO().equals(mapper.politicsCardMap(card)));
		assertTrue(offerDTO.getOfferingPlayer()==realObject.getOfferingPlayer().getName());
		assertTrue(offerDTO.getPrice()==realObject.getPrice());
	}
	
	@Test
	public void testPermitTileOfferMap() {
		Player player= new Player("a");
		Set<City> buildableCities= new HashSet<>(); 
		Set<Bonus> bonuses= new HashSet<>(); 
		PermitDeck deck= new PermitDeck();
		PermitTile tile= new PermitTile(buildableCities, bonuses, deck);
		int price=10;
		Offer realObject= new Offer(player, tile, price);
		GameDTOMapper mapper= new GameDTOMapper();
		OfferDTO offerDTO= mapper.offerMap(realObject);
		assertTrue(offerDTO.getOfferedObjectDTO().equals(mapper.permitTileMap(tile)));
		assertTrue(offerDTO.getOfferingPlayer()==realObject.getOfferingPlayer().getName());
		assertTrue(offerDTO.getPrice()==realObject.getPrice());
	}
	
	@Test
	public void testRewardTokenMap() {
		Set<Bonus> rewardTokenBonus= new HashSet<>();
		rewardTokenBonus.add(new ScoreBonus(1));
		RewardToken realToken= new RewardToken(rewardTokenBonus);
		GameDTOMapper mapper= new GameDTOMapper();
		RewardTokenDTO rewardTokenDTO= mapper.rewardTokenMap(realToken);
		assertTrue(rewardTokenDTO.getBonuses()==realToken.getRewardTokenBonus());
	}
	
	@Test
	public void testKingBonusTileMap() {
		ScoreBonus bonus= new ScoreBonus(1);
		KingBonusTile realObject= new KingBonusTile(bonus);
		GameDTOMapper mapper= new GameDTOMapper();
		BonusTileDTO bonusTileDTO= mapper.bonusTileMap(realObject);
		assertTrue(bonusTileDTO.getType()=="King");
		assertTrue(bonusTileDTO.getBonus()==realObject.getBonus());
	}
	
	@Test
	public void testColourBonusTileMap() {
		ScoreBonus bonus= new ScoreBonus(1);
		ColourBonusTile realObject= new ColourBonusTile(bonus, "blue");
		GameDTOMapper mapper= new GameDTOMapper();
		BonusTileDTO bonusTileDTO= mapper.bonusTileMap(realObject);
		assertTrue(bonusTileDTO.getBonus()==realObject.getBonus());
		assertTrue(bonusTileDTO.getType()==realObject.getColour());
	}
	
	@Test
	public void testRegionBonusTileMap() {
		ScoreBonus bonus= new ScoreBonus(1);
		RegionBonusTile realObject= new RegionBonusTile(bonus, "Sea");
		GameDTOMapper mapper= new GameDTOMapper();
		BonusTileDTO bonusTileDTO= mapper.bonusTileMap(realObject);
		assertTrue(bonusTileDTO.getBonus()==realObject.getBonus());
		assertTrue(bonusTileDTO.getType()==realObject.getRegion());
	}
}
