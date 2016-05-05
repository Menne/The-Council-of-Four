package gameStuff;

import java.util.HashSet;
import java.util.Set;

import bonus.ScoreBonus;

/**
 * Models a region board
 * @author andreapasquali
 *
 */
public class RegionBoard {

	private final String name;
	private final PermitDeck regionPermitDeck;
	private final CouncilBalcony regionBalcony;
	private final ScoreBonus regionBonus;
	private boolean bonusAvailable;
	private final Set<City> regionCities;
	private final PermitTile[] uncoveredPermitTiles;
	private static final int numberOfUncoveredPermitTiles=2;
	
	/**
	 * Initially the bonus is available and the set of the cities of the region is empty
	 * The city's constructor will add the city to the set. 
	 * @param name
	 * @param regionPermitDeck
	 * @param regionBalcony
	 * @param regionBonus
	 */
	public RegionBoard(String name, PermitDeck regionPermitDeck, 
			CouncilBalcony regionBalcony, ScoreBonus regionBonus){		
		this.name=name;		
		this.regionPermitDeck=regionPermitDeck;
		this.regionBalcony=regionBalcony;		
		this.regionBonus=regionBonus;
		this.bonusAvailable=true;
		this.regionCities=new HashSet<City>();
		this.uncoveredPermitTiles=new PermitTile[numberOfUncoveredPermitTiles];
//		uncoverPermitTiles();		
	}
	
	
	
	public boolean isBonusAvailable() {
		return bonusAvailable;
	}



	public void notBonusAvailable() {
		this.bonusAvailable = false;
	}



	public String getName() {
		return name;
	}



	public PermitDeck getRegionPermitDeck() {
		return regionPermitDeck;
	}



	public CouncilBalcony getRegionBalcony() {
		return regionBalcony;
	}



	public ScoreBonus getRegionBonus() {
		return regionBonus;
	}



	public Set<City> getRegionCities() {
		return regionCities;
	}



	public PermitTile[] getUncoveredPermitTiles() {
		return uncoveredPermitTiles;
	}
	
	
	public void addCityOfThisRegion(City city){
		this.regionCities.add(city);
	}



	/**
	 * Picks an uncovered permit tile among the available-one.
	 * After the execution of this method there is an empty slot.
	 * @param index
	 * @return the picked uncovered card
	 */
	public PermitTile pickUncoveredPermitTile(int index) {
		if(index>=numberOfUncoveredPermitTiles)
			throw new IllegalArgumentException("Index too big");
		PermitTile temp=this.uncoveredPermitTiles[index];
		this.uncoveredPermitTiles[index]=null;
		return temp;
	}
	
	
	/**
	 * Uncovers a new permit tile in each empty slot.
	 */
	public void uncoverPermitTiles(){
		for(int i=0; i<numberOfUncoveredPermitTiles; i++)
			if(this.uncoveredPermitTiles[i]==null)
//				try{
					this.uncoveredPermitTiles[i]=
							this.regionPermitDeck.pickPermitTile();
//					}
//				catch(IndexOutOfBoundsException e){
//					System.out.println("Permit deck empty, game cannot go on");
//					System.exit(-1);
//				}
						
	}
	
	
	/**
	 * Substitutes both of the uncovered permit tiles.
	 */
	public void substitutePermitTiles() {
		for (int i=0; i<=numberOfUncoveredPermitTiles; i++) {
			this.regionPermitDeck.addOnBottom(this.uncoveredPermitTiles[i]);
			this.uncoveredPermitTiles[i]=null;
		}
		uncoverPermitTiles();
	}



	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		return result;
	}



	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		RegionBoard other = (RegionBoard) obj;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		return true;
	}



	@Override
	public String toString() {
		return "RegionBoard \n\t name=" + name + "\n\t regionCities=" + regionCities;
	}


}