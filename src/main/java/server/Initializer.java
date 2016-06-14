package server;

import java.io.BufferedReader;import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import server.model.bonus.*;
import server.model.bonus.interactiveBonus.ChooseCityBonus;
import server.model.bonus.interactiveBonus.PurchasedPermitTileBonus;
import server.model.gameTable.*;

public class Initializer {
	
	private String s;
	@SuppressWarnings("unused")
	private String r;

	public GameTable initialize() throws IOException{
	
		
	FileReader f;
    f=new FileReader("src/main/file.txt");

    BufferedReader b;
    b=new BufferedReader(f);
	
    
    
    /*
     *inizializzo colori carte in una lista cardColourList
     */
	List<CardColour> cardColourList=new ArrayList<>();
	s=b.readLine();
	
	while(!"STOPCOLOURS".equals(s)){
		cardColourList.add(new CardColour(s));
		s=b.readLine();
	}
	
	s=b.readLine();
	
	/*
	 * inizializzo mazzo politico politicsDeck
	 */
	PoliticsDeck politicsDeck=
			new PoliticsDeck(new HashSet<CardColour>(cardColourList));
	

	/*
	 * inizializzo tutti i colori delle città in una lista cityColourList (colore del re è KingColour)
	 */
	List<CityColour> cityColourList=new ArrayList<>();
	
	while(!"STOPCITYCOLOUR".equals(s)){
		s=b.readLine();
		if(s!="KingColour")
			cityColourList.add(new CityColour(s,new ScoreBonus(Integer.parseInt(b.readLine()))));
		s=b.readLine();
	}
	
	r=b.readLine();//null
	r=b.readLine();//NumberOfCouncillorOfEveryColour
	s=b.readLine();// numero di consiglieri di ogni colore
	/*
	 * Inizializzo tutti i consiglieri (passo da file solo il numero di consiglieri per ogni colore)
	 */
	List<Councillor> councillorsList=new ArrayList<>();
	for(CardColour cardColour : cardColourList)
		if(!cardColour.getColour().equals("Rainbow"))
			for(int i=0;i<Integer.parseInt(s);i++)
				councillorsList.add(new Councillor(cardColour));
	Collections.shuffle(councillorsList);
	
	/*
	 * inizializzo riserva di consiglieri array councillorReserve
	 */
	CouncillorsReserve councillorsReserve=new CouncillorsReserve(councillorsList);
	
	/*
	 * inizializzo tutti i balconi
	 */
	CouncilBalcony firstRegionBalcony=new CouncilBalcony(councillorsReserve);
	CouncilBalcony secondRegionBalcony=new CouncilBalcony(councillorsReserve);
	CouncilBalcony thirdRegionBalcony=new CouncilBalcony(councillorsReserve);
	CouncilBalcony kingBalcony=new CouncilBalcony(councillorsReserve);
	
	/*
	 * inizializzo tutti i permit deck vuoti
	 */
	PermitDeck firstRegionPermitDeck=new PermitDeck();
	PermitDeck secondRegionPermitDeck=new PermitDeck();
	PermitDeck thirdRegionPermitDeck=new PermitDeck();
	
	r=b.readLine();//null
	r=b.readLine(); //RegionNames
	
	/*
	 * inizializzo le regioni
	 */
	RegionBoard firstRegion=
			new RegionBoard(b.readLine(), firstRegionPermitDeck, firstRegionBalcony, new ScoreBonus(Integer.parseInt(b.readLine())));
	RegionBoard secondRegion=
			new RegionBoard(b.readLine(), secondRegionPermitDeck, secondRegionBalcony, new ScoreBonus(Integer.parseInt(b.readLine())));
	RegionBoard thirdRegion=
			new RegionBoard(b.readLine(), thirdRegionPermitDeck, thirdRegionBalcony, new ScoreBonus(Integer.parseInt(b.readLine())));
	
	r=b.readLine();
	/*
	 * lista di tutte le regioni
	 */
	List<RegionBoard> regionList =new ArrayList<>();
	regionList.add(firstRegion);
	regionList.add(secondRegion);
	regionList.add(thirdRegion);
	
	r=b.readLine(); //null
	r=b.readLine(); //rewardTokenList
	
	/*
	 * lista di set di bonus (reward tiles)
	 */
	List<Set<Bonus>> rewardTokenList= new ArrayList<>();
	int i=0;
	while(!"STOPRewardTokenList".equals(s)) {
		rewardTokenList.add(new HashSet<Bonus>());
		while(!"NextRewardToken".equals(s)){
			s=b.readLine();
			if("AssistantsBonus".equals(s)){
				rewardTokenList.get(i).add(new AssistantsBonus(Integer.parseInt(b.readLine())));}
			if("CoinsBonus".equals(s))
				rewardTokenList.get(i).add(new CoinsBonus(Integer.parseInt(b.readLine())));
			if("MainActionBonus".equals(s))
				rewardTokenList.get(i).add(new MainActionBonus());
			if("NobilityBonus".equals(s))
				rewardTokenList.get(i).add(new NobilityBonus(Integer.parseInt(b.readLine())));
			if("PoliticsCardsBonus".equals(s))
				rewardTokenList.get(i).add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
			if("ScoreBonus".equals(s))
				rewardTokenList.get(i).add(new ScoreBonus(Integer.parseInt(b.readLine())));
			s=b.readLine(); //NextRewardToken or NEXTBonus (with next bonus it doesn't exit from the while)
		}
		s=b.readLine();
		i++;	
	}
	
	r=b.readLine();
	
	/*
	 * inizializzo e riempio le liste di città di ogni regione
	 */
	Set<City> firstRegionCities=new HashSet<>();
	s=b.readLine();
	if("FirstRegionCities".equals(s)){
		while(!"STOPFirstRegionCities".equals(s)){
		firstRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
		s=b.readLine();}
	}
	
	Set<City> secondRegionCities=new HashSet<>();
	s=b.readLine();
	if("SecondRegionCities".equals(s)){
		while(!"STOPSecondRegionCities".equals(s)){
		secondRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
		s=b.readLine();}
	}
	
	Set<City> thirdRegionCities=new HashSet<>();
	s=b.readLine();
	if("ThirdRegionCities".equals(s)){
		while(!"STOPThirdRegionCities".equals(s)){
		thirdRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
		s=b.readLine();}
	}
	
	
	/*
	 * creo un set di città che le contenga tutte
	 */
	Set<City> allCities=new HashSet<>();
	allCities.addAll(firstRegionCities);
	allCities.addAll(secondRegionCities);
	allCities.addAll(thirdRegionCities);
	
	/*
	 * inizializzo re
	 */
	King king=new King(searchColoredCity(allCities, cityColourTranslator("KingColour", cityColourList)));
	
	/*
	 * riempio le liste di città vicine di ogni città
	 */
	s=b.readLine();
	if("NearCities".equals(s))
		while(!"STOPNearCities".equals(s)){
			cityTranslator(b.readLine(), allCities).addNearCity(cityTranslator(b.readLine(), allCities));
			s=b.readLine();}
	
	
	/*
	 * inizializzo la mappa passando il set di tutte le città
	 */
	Map map=new Map(allCities, king);

	/*
	 * riempio i permit deck di ogni regione inizializzando ogni permit tile
	 */
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
			firstRegionPermitList.add(new PermitTile(buildableCities, tileBonus, firstRegionPermitDeck));
			s=b.readLine();//STOPfirstRegionPermitTileList or NEXTPermitTile
		}
	s=b.readLine();//null
	
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
			secondRegionPermitList.add(new PermitTile(buildableCities, tileBonus, secondRegionPermitDeck));
			s=b.readLine();
		}

	r=b.readLine();//null
	
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
			thirdRegionPermitList.add(new PermitTile(buildableCities, tileBonus, thirdRegionPermitDeck));
			s=b.readLine();//STOPfirstRegionPermitTileList or NEXTPermitTile
		}
	
	r=b.readLine();//null
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
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new ChooseCityBonus());
//		if(b.readLine()=="PickPermitTileBonus")
//			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new PickPermitTileBonus());
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
	
	List<ScoreBonus> kingRewardTiles= new ArrayList<>();
	/*
	 * inizializzo king reward tile
	 */
	s=b.readLine();
	if("kingRewardTile".equals(s))
		while(!"STOPKingRewardTile".equals(s)){
			kingRewardTiles.add(new ScoreBonus(Integer.parseInt(b.readLine())));
			s=b.readLine();//NEXT or STOPKingRewardTile		
		}
	
	/*
	 * inizializzo game table
	 */
	GameTable gameTable=
			new GameTable(map, regionList, kingBalcony, 
					councillorsReserve, politicsDeck, nobilityTrack, kingRewardTiles);
	b.close();
	return gameTable;
	

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

