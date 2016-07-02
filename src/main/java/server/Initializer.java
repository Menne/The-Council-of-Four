package server;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.model.bonus.AssistantsBonus;
import server.model.bonus.Bonus;
import server.model.bonus.CoinsBonus;
import server.model.bonus.MainActionBonus;
import server.model.bonus.NobilityBonus;
import server.model.bonus.PoliticsCardsBonus;
import server.model.bonus.ScoreBonus;
import server.model.bonus.interactiveBonus.ChooseCityBonus;
import server.model.bonus.interactiveBonus.PickPermitTileBonus;
import server.model.bonus.interactiveBonus.PurchasedPermitTileBonus;
import server.model.gameTable.CardColour;
import server.model.gameTable.City;
import server.model.gameTable.CityColour;
import server.model.gameTable.ColourBonusTile;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.CouncillorsReserve;
import server.model.gameTable.GameTable;
import server.model.gameTable.King;
import server.model.gameTable.KingBonusTile;
import server.model.gameTable.Map;
import server.model.gameTable.NobilityTrack;
import server.model.gameTable.PermitDeck;
import server.model.gameTable.PermitTile;
import server.model.gameTable.PoliticsDeck;
import server.model.gameTable.RegionBoard;
import server.model.gameTable.RegionBonusTile;
import server.model.gameTable.RewardToken;

public class Initializer {

	private String s;
	@SuppressWarnings("unused")
	private String r;
	private int mapNumber=1;
	
	public void setMapNumber(int mapNumber) {
		this.mapNumber = mapNumber;
	}
	
	public GameTable initialize() throws IOException{
		FileReader f;
		f=new FileReader("src/main/file"+String.valueOf(mapNumber)+".txt");
		BufferedReader b;
	    b=new BufferedReader(f);
		List<CardColour> cardColourList = cardColourListInitializer(b);
		PoliticsDeck politicsDeck=new PoliticsDeck(new HashSet<CardColour>(cardColourList));
		List<CityColour> cityColourList=cityColourListInitializer(b);
		List<Councillor> councillorsList=councillorsList(b, cardColourList);
		CouncillorsReserve councillorsReserve=new CouncillorsReserve(councillorsList);
		List<RegionBoard> regionList = regionBoardsInitializer(b, councillorsReserve);
		List<RewardToken> rewardTokenList=rewardTokenListInitializer(b);
		Set<City> firstRegionCities= firstRegionCitiesInitializer(b, regionList, cityColourList, rewardTokenList);
		Set<City> secondRegionCities= secondRegionCitiesInitializer(b,  regionList, cityColourList, rewardTokenList);
		Set<City> thirdRegionCities= thirdRegionCitiesInitializer(b,  regionList, cityColourList, rewardTokenList);
		Set<City> allCities=new HashSet<>();
		allCities.addAll(firstRegionCities);
		allCities.addAll(secondRegionCities);
		allCities.addAll(thirdRegionCities);
		King king=new King(searchColoredCity(allCities, cityColourTranslator("KingColour", cityColourList)));
		addNearCities(b, allCities);
		Map map=new Map(allCities, king);
		firstRegionPermitListInitializer(b, allCities, regionList);
		secondRegionPermitListInitializer(b, allCities, regionList);
		thirdRegionPermitListInitializer(b, allCities, regionList);
		NobilityTrack nobilityTrack= nobilityTrackInitializer(b);
		List<KingBonusTile> kingRewardTiles= kingRewardTilesInitializer(b);
		CouncilBalcony kingBalcony=new CouncilBalcony(councillorsReserve);
		GameTable gameTable=
				new GameTable(map, regionList, kingBalcony, 
						councillorsReserve, politicsDeck, nobilityTrack, kingRewardTiles);
		b.close();
		return gameTable;
		
	}
	
	private List<CardColour> cardColourListInitializer(BufferedReader b) throws IOException{
		List<CardColour> cardColourList=new ArrayList<>();
		s=b.readLine();
		
		while(!"STOPCOLOURS".equals(s)){
			cardColourList.add(new CardColour(s));
			s=b.readLine();
		}
		s=b.readLine();
		return cardColourList;
	}
	
	private List<CityColour> cityColourListInitializer(BufferedReader b) throws IOException{
		List<CityColour> cityColourList=new ArrayList<>();
		
		while(!"STOPCITYCOLOUR".equals(s)){
			s=b.readLine();
			if(s!="KingColour")
				cityColourList.add(new CityColour(s,new ColourBonusTile(new ScoreBonus(Integer.parseInt(b.readLine())),s)));
			s=b.readLine();
		}
		r=b.readLine();//null
		return cityColourList;
	}
	
	private List<Councillor> councillorsList(BufferedReader b, List<CardColour> cardColourList) throws IOException{
		r=b.readLine();//NumberOfCouncillorOfEveryColour
		s=b.readLine();// numero di consiglieri di ogni colore
		List<Councillor> councillorsList=new ArrayList<>();
		for(CardColour cardColour : cardColourList)
			if(!"Rainbow".equals(cardColour.getColour()))
				for(int i=0;i<Integer.parseInt(s);i++)
					councillorsList.add(new Councillor(cardColour));
		Collections.shuffle(councillorsList);
		return councillorsList;
	}
	
	private List<RegionBoard> regionBoardsInitializer(BufferedReader b, CouncillorsReserve councillorsReserve) throws IOException{
		CouncilBalcony firstRegionBalcony=new CouncilBalcony(councillorsReserve);
		CouncilBalcony secondRegionBalcony=new CouncilBalcony(councillorsReserve);
		CouncilBalcony thirdRegionBalcony=new CouncilBalcony(councillorsReserve);
		
		PermitDeck firstRegionPermitDeck=new PermitDeck();
		PermitDeck secondRegionPermitDeck=new PermitDeck();
		PermitDeck thirdRegionPermitDeck=new PermitDeck();
		
		r=b.readLine();//null
		r=b.readLine(); //RegionNames
		
		/*
		 * inizializzo le regioni
		 */
		String name1=b.readLine();
		RegionBoard firstRegion=
				new RegionBoard(name1, firstRegionPermitDeck, firstRegionBalcony, new RegionBonusTile(new ScoreBonus(Integer.parseInt(b.readLine())), name1));
		String name2=b.readLine();
		RegionBoard secondRegion=
				new RegionBoard(name2, secondRegionPermitDeck, secondRegionBalcony,new RegionBonusTile(new ScoreBonus(Integer.parseInt(b.readLine())), name2));
		String name3=b.readLine();
		RegionBoard thirdRegion=
				new RegionBoard(name3, thirdRegionPermitDeck, thirdRegionBalcony, new RegionBonusTile(new ScoreBonus(Integer.parseInt(b.readLine())),name3));
		
		r=b.readLine();
		/*
		 * lista di tutte le regioni
		 */
		List<RegionBoard> regionList =new ArrayList<>();
		regionList.add(firstRegion);
		regionList.add(secondRegion);
		regionList.add(thirdRegion);
		
		r=b.readLine(); //null
		return regionList;
	}
	
	private List<RewardToken> rewardTokenListInitializer(BufferedReader b) throws IOException{
		r=b.readLine(); //rewardTokenList
		List<RewardToken> rewardTokenList= new ArrayList<>();
		while(!"STOPRewardTokenList".equals(s)) {
			Set<Bonus> bonuses=new HashSet<>();
			while(!"NextRewardToken".equals(s)){
				s=b.readLine();
				if("AssistantsBonus".equals(s)){
					bonuses.add(new AssistantsBonus(Integer.parseInt(b.readLine())));}
				if("CoinsBonus".equals(s))
					bonuses.add(new CoinsBonus(Integer.parseInt(b.readLine())));
				if("MainActionBonus".equals(s))
					bonuses.add(new MainActionBonus());
				if("NobilityBonus".equals(s))
					bonuses.add(new NobilityBonus(Integer.parseInt(b.readLine())));
				if("PoliticsCardsBonus".equals(s))
					bonuses.add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
				if("ScoreBonus".equals(s))
					bonuses.add(new ScoreBonus(Integer.parseInt(b.readLine())));
				s=b.readLine(); //NextRewardToken or NEXTBonus (with next bonus it doesn't exit from the while)
			}
			rewardTokenList.add(new RewardToken(bonuses));
			s=b.readLine();	
		}
		r=b.readLine();
		return rewardTokenList;
	}
	
	private Set<City> firstRegionCitiesInitializer(BufferedReader b, List<RegionBoard> regionList, 
			List<CityColour> cityColourList, List<RewardToken> rewardTokenList) throws IOException{
		Set<City> firstRegionCities= new HashSet<>();
		s=b.readLine();
		if("FirstRegionCities".equals(s)){
			while(!"STOPFirstRegionCities".equals(s)){
				firstRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
				s=b.readLine();
			}
		}
		return firstRegionCities;
	}
	
	private Set<City> secondRegionCitiesInitializer(BufferedReader b, List<RegionBoard> regionList, 
			List<CityColour> cityColourList, List<RewardToken> rewardTokenList) throws IOException{
		Set<City> secondRegionCities= new HashSet<>();
		s=b.readLine();
		if("SecondRegionCities".equals(s)){
			while(!"STOPSecondRegionCities".equals(s)){
				secondRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
				s=b.readLine();
			}
		}
		return secondRegionCities;
	}
	
	private Set<City> thirdRegionCitiesInitializer(BufferedReader b, List<RegionBoard> regionList, 
			List<CityColour> cityColourList, List<RewardToken> rewardTokenList) throws IOException{
		Set<City> thirdRegionCities= new HashSet<>();
		s=b.readLine();
		if("ThirdRegionCities".equals(s)){
			while(!"STOPThirdRegionCities".equals(s)){
				thirdRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
				s=b.readLine();
			}
		}
		return thirdRegionCities;
	}
	
	private void addNearCities(BufferedReader b, Set<City> allCities) throws IOException{
		s=b.readLine();
		if("NearCities".equals(s))
			while(!"STOPNearCities".equals(s)){
				cityTranslator(b.readLine(), allCities).addNearCity(cityTranslator(b.readLine(), allCities));
				s=b.readLine();
			}
	}
	
	private void firstRegionPermitListInitializer(BufferedReader b, Set<City> allCities, List<RegionBoard> regionBoards) throws IOException{
		List<PermitTile> firstRegionPermitList = new ArrayList<>();
		s=b.readLine();
		if("firstRegionPermitTileList".equals(s))
			while(!"STOPfirstRegionPermitTileList".equals(s)){
				Set<City> buildableCities=new HashSet<>();
				while(!"STOPBuildableCities".equals(s)){
					buildableCities.add(cityTranslator(b.readLine(), allCities));
					s=b.readLine();//NEXTCity or STOPBuildableCity
				}
				Set<Bonus> tileBonus=new HashSet<>();
				while(!"STOPTileBonus".equals(s)){
					s=b.readLine();
					if("AssistantsBonus".equals(s))
						tileBonus.add(new AssistantsBonus(Integer.parseInt(b.readLine())));
					if("CoinsBonus".equals(s))
						tileBonus.add(new CoinsBonus(Integer.parseInt(b.readLine())));
					if("MainActionBonus".equals(s))
						tileBonus.add(new MainActionBonus());
					if("NobilityBonus".equals(s))
						tileBonus.add(new NobilityBonus(Integer.parseInt(b.readLine())));
					if("PoliticsCardsBonus".equals(s))
						tileBonus.add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
					if("ScoreBonus".equals(s))
						tileBonus.add(new ScoreBonus(Integer.parseInt(b.readLine())));
					s=b.readLine();// STOPTileBonus or NEXTBonus
				}
				firstRegionPermitList.add(new PermitTile(buildableCities, tileBonus, regionBoards.get(0).getRegionPermitDeck()));
				s=b.readLine();//STOPfirstRegionPermitTileList or NEXTPermitTile
			}
		s=b.readLine();//null
	}
	
	private void secondRegionPermitListInitializer(BufferedReader b, Set<City> allCities, List<RegionBoard> regionBoards) throws IOException{
		List<PermitTile> secondRegionPermitList = new ArrayList<>();
		s=b.readLine();
		if("secondRegionPermitTileList".equals(s))
			while(!"STOPsecondRegionPermitTileList".equals(s)){
				Set<City> buildableCities=new HashSet<>();
				while(!"STOPBuildableCities".equals(s)){
					buildableCities.add(cityTranslator(b.readLine(), allCities));
					s=b.readLine();//NEXTCity or STOPBuildableCity
				}
				Set<Bonus> tileBonus=new HashSet<>();
				while(!"STOPTileBonus".equals(s)){
					s=b.readLine();
					if("AssistantsBonus".equals(s))
						tileBonus.add(new AssistantsBonus(Integer.parseInt(b.readLine())));
					if("CoinsBonus".equals(s))
						tileBonus.add(new CoinsBonus(Integer.parseInt(b.readLine())));
					if("MainActionBonus".equals(s))
						tileBonus.add(new MainActionBonus());
					if("NobilityBonus".equals(s))
						tileBonus.add(new NobilityBonus(Integer.parseInt(b.readLine())));
					if("PoliticsCardsBonus".equals(s))
						tileBonus.add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
					if("ScoreBonus".equals(s))
						tileBonus.add(new ScoreBonus(Integer.parseInt(b.readLine())));
					s=b.readLine();// STOPTileBonus or NEXTBonus
				}
				secondRegionPermitList.add(new PermitTile(buildableCities, tileBonus, regionBoards.get(1).getRegionPermitDeck()));
				s=b.readLine();
			}
		r=b.readLine();//null
	}
	
	private void thirdRegionPermitListInitializer(BufferedReader b, Set<City> allCities, List<RegionBoard> regionBoards) throws IOException{
		List<PermitTile> thirdRegionPermitList = new ArrayList<>();
		s=b.readLine();
		if("thirdRegionPermitTileList".equals(s))
			while(!"STOPthirdRegionPermitTileList".equals(s)){
				Set<City> buildableCities=new HashSet<>();
				while(!"STOPBuildableCities".equals(s)){
					buildableCities.add(cityTranslator(b.readLine(), allCities));
					s=b.readLine();//NEXTCity or STOPBuildableCity
				}
				Set<Bonus> tileBonus=new HashSet<>();
				while(!"STOPTileBonus".equals(s)){
					s=b.readLine();
					if("AssistantsBonus".equals(s))
						tileBonus.add(new AssistantsBonus(Integer.parseInt(b.readLine())));
					if("CoinsBonus".equals(s))
						tileBonus.add(new CoinsBonus(Integer.parseInt(b.readLine())));
					if("MainActionBonus".equals(s))
						tileBonus.add(new MainActionBonus());
					if("NobilityBonus".equals(s))
						tileBonus.add(new NobilityBonus(Integer.parseInt(b.readLine())));
					if("PoliticsCardsBonus".equals(s))
						tileBonus.add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
					if("ScoreBonus".equals(s))
						tileBonus.add(new ScoreBonus(Integer.parseInt(b.readLine())));
					s=b.readLine();// STOPTileBonus or NEXTBonus
				}
				thirdRegionPermitList.add(new PermitTile(buildableCities, tileBonus, regionBoards.get(2).getRegionPermitDeck()));
				s=b.readLine();//STOPfirstRegionPermitTileList or NEXTPermitTile
			}
		r=b.readLine();//null
	}
	
	private NobilityTrack nobilityTrackInitializer(BufferedReader b) throws IOException{
		r=b.readLine();//NobilityTrack
		
		/*
		 * inizializzo nobility track e assegno bonus alle caselle
		 */
		NobilityTrack nobilityTrack=new NobilityTrack(Integer.parseInt(b.readLine()));//int lunghezza nobility track
		
		while(!"STOPNobilityTrack".equals(s)){
			s=b.readLine();
			if("AssistantsBonus".equals(s))
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new AssistantsBonus(Integer.parseInt(b.readLine()))); //prima numero casella e poi numero assistenti!!
			if("CoinsBonus".equals(s))
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new CoinsBonus(Integer.parseInt(b.readLine())));
			if("ChooseCityBonus".equals(s))
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new ChooseCityBonus(Integer.parseInt(b.readLine())));
			if("PickPermitTileBonus".equals(s))
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new PickPermitTileBonus());
			if("PurchasedPermitTileBonus".equals(s))
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new PurchasedPermitTileBonus());
			if("PoliticsCardsBonus".equals(s))
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
			if("ScoreBonus".equals(s))	
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new ScoreBonus(Integer.parseInt(b.readLine())));
			if("MainActionBonus".equals(s))
				nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new MainActionBonus());
			
			s=b.readLine();//NEXT or STOPNobilityTrack
		}
		return nobilityTrack;
	}
	
	private List<KingBonusTile> kingRewardTilesInitializer(BufferedReader b) throws IOException{
		List<KingBonusTile> kingRewardTiles= new ArrayList<>();
		s=b.readLine();
		if("kingRewardTile".equals(s))
			while(!"STOPKingRewardTile".equals(s)){
				kingRewardTiles.add(new KingBonusTile(new ScoreBonus(Integer.parseInt(b.readLine()))));
				s=b.readLine();//NEXT or STOPKingRewardTile		
			}
		return kingRewardTiles;
	}
	
	
	
		
	private City searchColoredCity(Set<City> cities, CityColour colour){
		for(City city: cities)
			if(city.getColour().equals(colour))
				return city;
		throw new IllegalArgumentException("city of this colour doesn't exist");
	}
	
	private RegionBoard regionTranslator(String regionString, List<RegionBoard> regions){
		for(RegionBoard region : regions)
			if(region.getName().equals(regionString))
				return region;
		throw new IllegalArgumentException("regionString is not a region");
	}
	
	private CityColour cityColourTranslator(String colourString, List<CityColour> colours){
		for(CityColour colour : colours)
			if(colour.getName().equals(colourString))
				return colour;
		throw new IllegalArgumentException("colourString is not a colour");
	}
	
	private City cityTranslator(String cityString, Set<City> cities){
		for(City city : cities)
			if(city.getName().equals(cityString))
				return city;
		throw new IllegalArgumentException("cityString is not a city");
	}
}
