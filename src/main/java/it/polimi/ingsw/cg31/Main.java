package it.polimi.ingsw.cg31;

import gameStuff.*;
import gameTable.GameTable;
import model.Game;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;

import bonus.*;
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
		
		//raggruppo regioni in una lista
		List<RegionBoard> regionList =new ArrayList<RegionBoard>();
		regionList.add(seaRegion);
		regionList.add(hillRegion);
		regionList.add(mountainRegion);
		
		//creo città e le divido in set per regioni
		Set<City> seaSet=new HashSet<City>();
		City a=new City("Arkon", seaRegion, Citycolor4);
		City b=new City("Burgen", seaRegion, Citycolor1);
		City c=new City("Castrum", seaRegion, Citycolor2);
		City d=new City("Dorful", seaRegion, Citycolor2);
		City e=new City("Esti", seaRegion, Citycolor3);
		seaSet.add(a);
		seaSet.add(b);
		seaSet.add(c);
		seaSet.add(d);
		seaSet.add(e);
		
		Set<City> hillSet=new HashSet<City>();
		City f=new City("Framek", hillRegion, Citycolor1);
		City g=new City("Graden", hillRegion, Citycolor2);
		City h=new City("Hellar", hillRegion, Citycolor1);
		City i=new City("Indur", hillRegion, Citycolor3);
		City j=new City("Juvelar", hillRegion, Citycolor4); //TODO bisogna gestire il fatto che città del re non ha colore
		hillSet.add(f);
		hillSet.add(g);
		hillSet.add(h);
		hillSet.add(i);
		hillSet.add(j);
		
		Set<City> mountainSet=new HashSet<City>();
		City k=new City("Kultos", mountainRegion, Citycolor1);
		City l=new City("Lyram", mountainRegion, Citycolor2);
		City m=new City("Merkatim", mountainRegion, Citycolor4);
		City n=new City("Naris", mountainRegion, Citycolor3);
		City o=new City("Osium", mountainRegion, Citycolor1);
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
		
		Scanner scanner=new Scanner(System.in);
		GameLogic gameLogic=new GameLogic(game);
		View view=new CLI(gameLogic, scanner);
		view.registerObserver(gameLogic);
		
		gameLogic.play();
		scanner.close(); 
		
//		System.out.println(game);				
	}

}
