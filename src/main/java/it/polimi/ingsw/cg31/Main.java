package it.polimi.ingsw.cg31;

import model.Game;
import model.bonus.AssistantsBonus;
import model.bonus.Bonus;
import model.bonus.MainActionBonus;
import model.bonus.NobilityBonus;
import model.bonus.PoliticsCardsBonus;
import model.bonus.ScoreBonus;
import model.gameTable.CardColour;
import model.gameTable.City;
import model.gameTable.CityColour;
import model.gameTable.CouncilBalcony;
import model.gameTable.Councillor;
import model.gameTable.CouncillorsReserve;
import model.gameTable.GameTable;
import model.gameTable.Map;
import model.gameTable.NobilityTrack;
import model.gameTable.PermitDeck;
import model.gameTable.PermitTile;
import model.gameTable.PoliticsDeck;
import model.gameTable.RegionBoard;
import model.parser.Parser;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import controller.GameLogic;
import players.*;

import view.*;

public class Main {

	public static void main(String[] args) {		
		//creo lista dei possibili card colour
				List<CardColour> cardColourList=new ArrayList<CardColour>();
				CardColour CardColour1=new CardColour("White");
				cardColourList.add(CardColour1);
				CardColour CardColour2=new CardColour("Black");
				cardColourList.add(CardColour2);
				CardColour CardColour3=new CardColour("Orange");
				cardColourList.add(CardColour3);
				CardColour CardColour4=new CardColour("Pink");
				cardColourList.add(CardColour4);
				CardColour CardColour5=new CardColour("Purple");
				cardColourList.add(CardColour5);
				CardColour CardColour6=new CardColour("Violet");
				cardColourList.add(CardColour6);
				CardColour CardColour7=new CardColour("Rainbow");
				cardColourList.add(CardColour7);
		
		//creo politic deck
		PoliticsDeck politicsDeck=
				new PoliticsDeck(new HashSet<CardColour>(cardColourList));
		
		//creo giocatori e li metto in una lista
		List<Player> players=new ArrayList<Player>();
		Player player1=new Player(1,"Luca",1,10,politicsDeck);
		players.add(player1);
		Player player2=new Player(2,"Menne",2,11,politicsDeck);
		players.add(player2);
		Player player3=new Player(3,"Andrea",3,12,politicsDeck);
		players.add(player3);
		
		//creo i possibili city colour
		CityColour Citycolor1=new CityColour("Gold", new ScoreBonus(20));
		CityColour Citycolor2=new CityColour("Silver", new ScoreBonus(12));
		CityColour Citycolor3=new CityColour("Bronze", new ScoreBonus(8));
		CityColour Citycolor4=new CityColour("Blue", new ScoreBonus(5));
		
		
		
		//instanzio tutti i consiglieri del gioco partendo dalla lista di card colour
		//e li metto in una lista
		List<Councillor> councillorsList=new ArrayList<Councillor>();
		for(CardColour cardColour : cardColourList)
			if(!cardColour.equals("Rainbow"))
				for(int i=0;i<6;i++)
					councillorsList.add(new Councillor(cardColour));
		Collections.shuffle(councillorsList);
		
		//creo la riserva consiglieri passando al costruttore la lista di tutti i consiglieri
		CouncillorsReserve councillorsReserve=new CouncillorsReserve(councillorsList);
				
		//creo i 4 balconi rimuovendo 4 consiglieri alla volta dalla lista
		CouncilBalcony seaBalcony=new CouncilBalcony(councillorsReserve);
		CouncilBalcony hillBalcony=new CouncilBalcony(councillorsReserve);
		CouncilBalcony mountainBalcony=new CouncilBalcony(councillorsReserve);
		CouncilBalcony kingBalcony=new CouncilBalcony(councillorsReserve);
		
		
		
		//creo permit deck vuoti
		PermitDeck seaPermitDeck=new PermitDeck();
		PermitDeck hillPermitDeck=new PermitDeck();
		PermitDeck mountainPermitDeck=new PermitDeck();
		
		//creo le regioni
		//N.B. passo al costruttore deck vuoti perchè non posso ancora creare le città
		RegionBoard seaRegion=
				new RegionBoard("Sea", seaPermitDeck, seaBalcony, new ScoreBonus(5));
		RegionBoard hillRegion=
				new RegionBoard("Hill", hillPermitDeck, hillBalcony, new ScoreBonus(5));
		RegionBoard mountainRegion=
				new RegionBoard("Mountain", mountainPermitDeck, mountainBalcony, new ScoreBonus(5));
		
		//creo lista di reward token
		List<Set<Bonus>> rewardTokenList = new ArrayList<Set<Bonus>>();
		for(int i=0; i<16;i++)
			rewardTokenList.add(new HashSet<Bonus>());
		for(Set<Bonus> rewardToken : rewardTokenList){
			rewardToken.add(new ScoreBonus(3));
			rewardToken.add(new NobilityBonus(1));
		} 
			
		
		
		//raggruppo regioni in una lista
		List<RegionBoard> regionList =new ArrayList<RegionBoard>();
		regionList.add(seaRegion);
		regionList.add(hillRegion);
		regionList.add(mountainRegion);
		
		//creo città e le divido in set per regioni
		Set<City> seaSet=new HashSet<City>();
		City a=new City("Arkon", seaRegion, Citycolor4,rewardTokenList);
		City b=new City("Burgen", seaRegion, Citycolor1,rewardTokenList);
		City c=new City("Castrum", seaRegion, Citycolor2,rewardTokenList);
		City d=new City("Dorful", seaRegion, Citycolor2,rewardTokenList);
		City e=new City("Esti", seaRegion, Citycolor3,rewardTokenList);
		seaSet.add(a);
		seaSet.add(b);
		seaSet.add(c);
		seaSet.add(d);
		seaSet.add(e);
		
		Set<City> hillSet=new HashSet<City>();
		City f=new City("Framek", hillRegion, Citycolor1,rewardTokenList);
		City g=new City("Graden", hillRegion, Citycolor2,rewardTokenList);
		City h=new City("Hellar", hillRegion, Citycolor1,rewardTokenList);
		City i=new City("Indur", hillRegion, Citycolor3,rewardTokenList);
		City j=new City("Juvelar", hillRegion, Citycolor4,rewardTokenList); //TODO bisogna gestire il fatto che città del re non ha colore
		hillSet.add(f);
		hillSet.add(g);
		hillSet.add(h);
		hillSet.add(i);
		hillSet.add(j);
		
		Set<City> mountainSet=new HashSet<City>();
		City k=new City("Kultos", mountainRegion, Citycolor1,rewardTokenList);
		City l=new City("Lyram", mountainRegion, Citycolor2,rewardTokenList);
		City m=new City("Merkatim", mountainRegion, Citycolor4,rewardTokenList);
		City n=new City("Naris", mountainRegion, Citycolor3,rewardTokenList);
		City o=new City("Osium", mountainRegion, Citycolor1,rewardTokenList);
		mountainSet.add(k);
		mountainSet.add(l);
		mountainSet.add(m);
		mountainSet.add(n);
		mountainSet.add(o);
		

		//creo permit tiles. 
		//Tutte le permit tile permettono di costruire in tutte le città della regione
		//Tutte le permit tile di una regione hanno lo stesso bonus
		//Di conseguenza il deck di una regione sarà composto da 10 tile uguali
		List<PermitTile> permitSeaList = new ArrayList<PermitTile>();
		for(int ind=0;ind<10;ind++){
			permitSeaList.add(new PermitTile(seaSet, new HashSet<Bonus>(), seaPermitDeck));
			permitSeaList.get(ind).getBonus().add(new NobilityBonus(1));
		}
		
		List<PermitTile> permitHillList = new ArrayList<PermitTile>();
		for(int ind=0;ind<10;ind++){
			permitHillList.add(new PermitTile(hillSet, new HashSet<Bonus>(), hillPermitDeck));
			permitHillList.get(ind).getBonus().add(new AssistantsBonus(1));
		}
		
		List<PermitTile> permitMountainList = new ArrayList<PermitTile>();
		for(int ind=0;ind<10;ind++){
			permitMountainList.add(new PermitTile(mountainSet, new HashSet<Bonus>(), mountainPermitDeck));
			permitMountainList.get(ind).getBonus().add(new PoliticsCardsBonus(2));
		}
		
		//aggiungo collegamenti tra le città
		a.addNearCity(c);
		a.addNearCity(b);
		b.addNearCity(d);
		b.addNearCity(e);
		c.addNearCity(f);
		d.addNearCity(g);
		e.addNearCity(h);
		f.addNearCity(i);
		g.addNearCity(j);
		h.addNearCity(j);
		h.addNearCity(m);
		i.addNearCity(k);
		i.addNearCity(j);
		j.addNearCity(l);
		k.addNearCity(n);
		l.addNearCity(o);
		m.addNearCity(o);
		
		//creo mappa
		Set<City> cities=new HashSet<City>(seaSet);
		cities.addAll(hillSet);
		cities.addAll(mountainSet);
		
		Map map=new Map(cities);
		
		//creo NobilityTrack con bonus main action in casella 1
		NobilityTrack nobilityTrack=new NobilityTrack(20);
		nobilityTrack.addBonus(1, new MainActionBonus());
		
		
		//creo game table
		GameTable gameTable=
				new GameTable(map, regionList, kingBalcony, 
						councillorsReserve, politicsDeck, nobilityTrack);
		
		//aggiungo king reward tiles
		gameTable.addKingRewardTile(new ScoreBonus(25));
		gameTable.addKingRewardTile(new ScoreBonus(18));
		gameTable.addKingRewardTile(new ScoreBonus(12));
		gameTable.addKingRewardTile(new ScoreBonus(7));
		gameTable.addKingRewardTile(new ScoreBonus(3));
		
		//creo game
		Game game=new Game(players, gameTable);
		
		Parser parser=new Parser(game);
		CLI view=new CLI(game, parser);
		new GameLogic(game, view);
		
		while(true){
			Scanner scanner=new Scanner(System.in);
			System.out.println("Player " + game.getCurrentPlayer().getName() + 
					", it's yout turn! what do you want to do? For action, press a");
			String input=scanner.nextLine();
			view.input(input);
			scanner.close(); 
		}
		
		
//		gameLogic.play();		
//		System.out.println(game);				
	}

}
