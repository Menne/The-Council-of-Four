package initializer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import model.bonus.*;
import model.gameTable.*;
import model.Game;
import players.Player;

public class Initializer {
	
	private String s;
	

	public Game initialize() throws IOException{
	
		
	FileReader f;
    f=new FileReader("src/main/file.txt");

    BufferedReader b;
    b=new BufferedReader(f);
	
    
    
    /*
     *inizializzo colori carte in una lista cardColourList
     */
	List<CardColour> cardColourList=new ArrayList<CardColour>();
	s=b.readLine();
	
	while(!s.equals("STOPCOLOURS")){
		cardColourList.add(new CardColour(s));
		s=b.readLine();
	}
	
	System.out.println("Colori istanziati");
	
	s=b.readLine();
	
	/*
	 * inizializzo mazzo politico politicsDeck
	 */
	PoliticsDeck politicsDeck=
			new PoliticsDeck(new HashSet<CardColour>(cardColourList));
	
	System.out.println("mazzo politico ok");
	/*
	 * inizializzo tutti i colori delle città in una lista cityColourList (colore del re è KingColour)
	 */
	List<CityColour> cityColourList=new ArrayList<CityColour>();
	
	while(!s.equals("STOPCITYCOLOUR")){
		s=b.readLine();
		cityColourList.add(new CityColour(s,new ScoreBonus(Integer.parseInt(b.readLine()))));
		s=b.readLine();
	}
	System.out.println("Colori città ok");
	
	b.readLine();//null
	b.readLine();//NumberOfCouncillorOfEveryColour
	s=b.readLine();// numero di consiglieri di ogni colore
	/*
	 * Inizializzo tutti i consiglieri (passo da file solo il numero di consiglieri per ogni colore)
	 */
	List<Councillor> councillorsList=new ArrayList<Councillor>();
	for(CardColour cardColour : cardColourList)
		if(!cardColour.getColour().equals("Rainbow"))
			for(int i=0;i<Integer.parseInt(s);i++)
				councillorsList.add(new Councillor(cardColour));
	Collections.shuffle(councillorsList);
	
	/*
	 * inizializzo riserva di consiglieri array councillorReserve
	 */
	CouncillorsReserve councillorsReserve=new CouncillorsReserve(councillorsList);
	
	System.out.println("riserva consiglieri ok");
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
	
	System.out.println("permit deck vuoti");
	
	b.readLine();//null
	s=b.readLine(); //RegionNames
	
	/*
	 * inizializzo le regioni
	 */
	RegionBoard firstRegion=
			new RegionBoard(b.readLine(), firstRegionPermitDeck, firstRegionBalcony, new ScoreBonus(Integer.parseInt(b.readLine())));
	RegionBoard secondRegion=
			new RegionBoard(b.readLine(), secondRegionPermitDeck, secondRegionBalcony, new ScoreBonus(Integer.parseInt(b.readLine())));
	RegionBoard thirdRegion=
			new RegionBoard(b.readLine(), thirdRegionPermitDeck, thirdRegionBalcony, new ScoreBonus(Integer.parseInt(b.readLine())));
	
	System.out.println("regioni ok");
	
	b.readLine();
	/*
	 * lista di tutte le regioni
	 */
	List<RegionBoard> regionList =new ArrayList<RegionBoard>();
	regionList.add(firstRegion);
	regionList.add(secondRegion);
	regionList.add(thirdRegion);
	
	b.readLine(); //null
	s=b.readLine(); //rewardTokenList
	
	/*
	 * lista di set di bonus (reward tiles)
	 */
	List<Set<Bonus>> rewardTokenList= new ArrayList<Set<Bonus>>();
	int i=0;
	while(!s.equals("STOPRewardTokenList")) {//legge da file il numero di reward token
		rewardTokenList.add(new HashSet<Bonus>());
		while(!s.equals("NextRewardToken")){
			s=b.readLine();
			if(s.equals("AssistantsBonus"))
				rewardTokenList.get(i).add(new AssistantsBonus(Integer.parseInt(b.readLine())));
			if(s.equals("CoinsBonus"))
				rewardTokenList.get(i).add(new CoinsBonus(Integer.parseInt(b.readLine())));
			if(s.equals("MainActionBonus"))
				rewardTokenList.get(i).add(new MainActionBonus());
			if(s.equals("NobilityBonus"))
				rewardTokenList.get(i).add(new NobilityBonus(Integer.parseInt(b.readLine())));
			if(s.equals("PoliticsCardsBonus"))
				rewardTokenList.get(i).add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
			if(s.equals("ScoreBonus"))
				rewardTokenList.get(i).add(new ScoreBonus(Integer.parseInt(b.readLine())));
			s=b.readLine(); //NextRewardToken or NEXTBonus (with next bonus it doesn't exit from the while)
		}
		s=b.readLine();
		i++;	
	}
	
	System.out.println("gettoni città ok");
	b.readLine();
	
	/*
	 * inizializzo e riempio le liste di città di ogni regione
	 */
	Set<City> firstRegionCities=new HashSet<City>();
	s=b.readLine();
	if(s.equals("FirstRegionCities")){
		while(!s.equals("STOPFirstRegionCities")){
		firstRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
		s=b.readLine();}
	}
	
	Set<City> secondRegionCities=new HashSet<City>();
	s=b.readLine();
	if(s.equals("SecondRegionCities")){
		while(!s.equals("STOPSecondRegionCities")){
		secondRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
		s=b.readLine();}
	}
	
	Set<City> thirdRegionCities=new HashSet<City>();
	s=b.readLine();
	if(s.equals("ThirdRegionCities")){
		while(!s.equals("STOPThirdRegionCities")){
		thirdRegionCities.add(new City(b.readLine(), regionTranslator(b.readLine(), regionList), cityColourTranslator(b.readLine(), cityColourList),rewardTokenList));
		s=b.readLine();}
	}
	
	System.out.println("città nelle varie regioni ok");
	
	
	/*
	 * creo un set di città che le contenga tutte
	 */
	Set<City> allCities=new HashSet<City>();
	allCities.addAll(firstRegionCities);
	allCities.addAll(secondRegionCities);
	allCities.addAll(thirdRegionCities);
	
	/*
	 * inizializzo re
	 */
	new King(searchColoredCity(allCities, cityColourTranslator("KingColour", cityColourList)));
	
	/*
	 * riempio le liste di città vicine di ogni città
	 */
	s=b.readLine();
	if(s.equals("NearCities"))
		while(!s.equals("STOPNearCities")){
			cityTranslator(b.readLine(), allCities).addNearCity(cityTranslator(b.readLine(), allCities));
			s=b.readLine();}

	
	System.out.println("città vicine ok");
	
	
	/*
	 * inizializzo la mappa passando il set di tutte le città
	 */
	Map map=new Map(allCities);

	/*
	 * riempio i permit deck di ogni regione inizializzando ogni permit tile
	 */
	List<PermitTile> firstRegionPermitList = new ArrayList<PermitTile>();
	s=b.readLine();
	if(s.equals("firstRegionPermitTileList"))
		while(!s.equals("STOPfirstRegionPermitTileList")){
			Set<City> buildableCities=new HashSet<City>();
			while(!s.equals("STOPBuildableCities")){
				buildableCities.add(cityTranslator(b.readLine(), allCities));
				s=b.readLine();//NEXTCity or STOPBuildableCity
			}
			Set<Bonus> tileBonus=new HashSet<Bonus>();
			while(!s.equals("STOPTileBonus")){
				s=b.readLine();
				if(s.equals("AssistantsBonus"))
					tileBonus.add(new AssistantsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("CoinsBonus"))
					tileBonus.add(new CoinsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("MainActionBonus"))
					tileBonus.add(new MainActionBonus());
				if(s.equals("NobilityBonus"))
					tileBonus.add(new NobilityBonus(Integer.parseInt(b.readLine())));
				if(s.equals("PoliticsCardsBonus"))
					tileBonus.add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("ScoreBonus"))
					tileBonus.add(new ScoreBonus(Integer.parseInt(b.readLine())));
				s=b.readLine();// STOPTileBonus or NEXTBonus
			}
			firstRegionPermitList.add(new PermitTile(buildableCities, tileBonus, firstRegionPermitDeck));
			s=b.readLine();//STOPfirstRegionPermitTileList or NEXTPermitTile
		}
	System.out.println("permit tiles prima regione ok");
	s=b.readLine();//null
	
	List<PermitTile> secondRegionPermitList = new ArrayList<PermitTile>();
	s=b.readLine();
	if(s.equals("secondRegionPermitTileList"))
		while(!s.equals("STOPsecondRegionPermitTileList")){
			Set<City> buildableCities=new HashSet<City>();
			while(!s.equals("STOPBuildableCities")){
				buildableCities.add(cityTranslator(b.readLine(), allCities));
				s=b.readLine();//NEXTCity or STOPBuildableCity
			}
			Set<Bonus> tileBonus=new HashSet<Bonus>();
			while(!s.equals("STOPTileBonus")){
				s=b.readLine();
				if(s.equals("AssistantsBonus"))
					tileBonus.add(new AssistantsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("CoinsBonus"))
					tileBonus.add(new CoinsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("MainActionBonus"))
					tileBonus.add(new MainActionBonus());
				if(s.equals("NobilityBonus"))
					tileBonus.add(new NobilityBonus(Integer.parseInt(b.readLine())));
				if(s.equals("PoliticsCardsBonus"))
					tileBonus.add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("ScoreBonus"))
					tileBonus.add(new ScoreBonus(Integer.parseInt(b.readLine())));
				s=b.readLine();// STOPTileBonus or NEXTBonus
			}
			secondRegionPermitList.add(new PermitTile(buildableCities, tileBonus, secondRegionPermitDeck));
			s=b.readLine();
		}
		
		
	System.out.println("permit tiles seconda regione ok");

	b.readLine();//null
	
	List<PermitTile> thirdRegionPermitList = new ArrayList<PermitTile>();
	s=b.readLine();
	if(s.equals("thirdRegionPermitTileList"))
		while(!s.equals("STOPthirdRegionPermitTileList")){
			Set<City> buildableCities=new HashSet<City>();
			while(!s.equals("STOPBuildableCities")){
				buildableCities.add(cityTranslator(b.readLine(), allCities));
				s=b.readLine();//NEXTCity or STOPBuildableCity
			}
			Set<Bonus> tileBonus=new HashSet<Bonus>();
			while(!s.equals("STOPTileBonus")){
				s=b.readLine();
				if(s.equals("AssistantsBonus"))
					tileBonus.add(new AssistantsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("CoinsBonus"))
					tileBonus.add(new CoinsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("MainActionBonus"))
					tileBonus.add(new MainActionBonus());
				if(s.equals("NobilityBonus"))
					tileBonus.add(new NobilityBonus(Integer.parseInt(b.readLine())));
				if(s.equals("PoliticsCardsBonus"))
					tileBonus.add(new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
				if(s.equals("ScoreBonus"))
					tileBonus.add(new ScoreBonus(Integer.parseInt(b.readLine())));
				s=b.readLine();// STOPTileBonus or NEXTBonus
			}
			thirdRegionPermitList.add(new PermitTile(buildableCities, tileBonus, thirdRegionPermitDeck));
			s=b.readLine();//STOPfirstRegionPermitTileList or NEXTPermitTile
		}
	
	System.out.println("permit tiles terza regione ok");
	
	b.readLine();//null
	b.readLine();//NobilityTrack
	
	/*
	 * inizializzo nobility track e assegno bonus alle caselle
	 */
	NobilityTrack nobilityTrack=new NobilityTrack(Integer.parseInt(b.readLine()));//int lunghezza nobility track
	
	while(!s.equals("STOPNobilityTrack")){
		s=b.readLine();
		if(s.equals("AssistantsBonus"))
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new AssistantsBonus(Integer.parseInt(b.readLine()))); //prima numero casella e poi numero assistenti!!
		if(s.equals("CoinsBonus"))
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new CoinsBonus(Integer.parseInt(b.readLine())));
		if(s.equals("ChooseCityBonus"))
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new ChooseCityBonus());
//		if(b.readLine()=="PickPermitTileBonus")
//			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new PickPermitTileBonus());
		if(s.equals("PurchasedPermitTileBonus"))
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new PurchasedPermitTileBonus());
		if(s.equals("PoliticsCardsBonus"))
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new PoliticsCardsBonus(Integer.parseInt(b.readLine())));
		if(s.equals("ScoreBonus"))	
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new ScoreBonus(Integer.parseInt(b.readLine())));
		if(s.equals("MainActionBonus"))
			nobilityTrack.addBonus(Integer.parseInt(b.readLine()), new MainActionBonus());
		
		s=b.readLine();//NEXT or STOPNobilityTrack
	}
	
	System.out.println("nobility track ok");
	
	List<ScoreBonus> kingRewardTiles= new ArrayList<ScoreBonus>();
	/*
	 * inizializzo king reward tile
	 */
	s=b.readLine();
	if(s.equals("kingRewardTile"))
		while(!s.equals("STOPKingRewardTile")){
			kingRewardTiles.add(new ScoreBonus(Integer.parseInt(b.readLine())));
			s=b.readLine();//NEXT or STOPKingRewardTile		
		}
	System.out.println("king reward tile ok");
	
	/*
	 * inizializzo game table
	 */
	GameTable gameTable=
			new GameTable(map, regionList, kingBalcony, 
					councillorsReserve, politicsDeck, nobilityTrack, kingRewardTiles);
	
	System.out.println("gametable ok");

	
	List<Player> players=new ArrayList<Player>();
	Player player1=new Player(1,"Luca",1,10,politicsDeck);
	players.add(player1);
	Player player2=new Player(2,"Menne",2,11,politicsDeck);
	players.add(player2);
	Player player3=new Player(3,"Andrea",3,12,politicsDeck);
	players.add(player3);
	
	b.close();
	
	Game game=new Game(players, gameTable);
	return game;
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

