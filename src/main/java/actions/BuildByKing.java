package actions;

import java.util.ArrayList;
import java.util.List;

import bonus.Bonus;
import gameStuff.City;
import gameStuff.ConnectedBuiltCityDiscover;
import gameStuff.CouncilBalcony;
import gameStuff.Councillor;
import gameStuff.Emporium;
import gameStuff.PoliticsCard;
import gameStuff.RegionBoard;
import model.Game;

/**
 * This class models the build an emporium with king's help action
 * @author Emanuele
 *
 */

public class BuildByKing extends MainAction {

	private City selectedCity;
	private List<PoliticsCard> cardsToDescard;

	/**
	 * Constructor of BuidByKing
	 * @param game is the current game
	 * @param selectedCity is the city in which the current player wants to build
	 * @param cardsToDescard are the cards used to satisfy the balcony of the king
	 */
	public BuildByKing(Game game){
		super(game);
	}
	
	
	
	public void setSelectedCity(City selectedCity) {
		this.selectedCity = selectedCity;
	}



	public void setCardsToDescard(List<PoliticsCard> cardsToDescard) {
		this.cardsToDescard = cardsToDescard;
	}



	/**
	 * First of all checks if the parameters the current player gave are corrected, 
	 * then removes the cards from the hand of the player and decrements his coins, 
	 * then removes the emporium from the hand of the player an adds it on the set of emporiums 
	 * of the selected city, then assigns all the bonuses of liked cities, then decrements player's assistants
	 * @return TRUE if the action goes well, false otherwise
	 */
	public boolean executeAction() throws NullPointerException{
		
		if(this.cardsToDescard==null || this.selectedCity==null)
			throw new NullPointerException("Paramters not setted");
		
		ConnectedBuiltCityDiscover likedCities=new ConnectedBuiltCityDiscover();
		
		if (!(checkCityNotContainsEmporium() && checkEnoughAssistants() && 
				CheckHandSatisfiesBalcony() && CheckEnoughCoins()))
			return false;
		
		this.game.getCurrentPlayer().decrementCoins(CoinsToPay());
		for (PoliticsCard card : cardsToDescard)
			this.game.getCurrentPlayer().removeCardFromHand(card);
		
		Emporium temporaryEmporium=this.game.getCurrentPlayer().removeEmporium();
		this.selectedCity.addEmporium(temporaryEmporium);
		for (City city : likedCities.getConnectedBuiltCities(this.game.getGameTable().getMap().getGameMap(), this.selectedCity, temporaryEmporium))
			for (Bonus bonusToAssign : city.getRewardToken())
				bonusToAssign.assignBonus(this.game);
		this.game.getCurrentPlayer().decrementAssistants(assistantsToPay());
		findKing().setIsKingPresent(false);
		this.selectedCity.setIsKingPresent(true);

		if (this.selectedCity.getRegion().isBonusAvailable())
			assignRegionBonus();
		if (this.selectedCity.getColour().isBonusAvailable())
			assignColourBonus();
		this.decrementAction();
		
		return true;
	}
	
	/**
	 * Finds the city where the king is located
	 * @return the city with the king
	 */
	private City findKing() {
		for (RegionBoard region : this.game.getGameTable().getRegionBoards())
			for (City city : region.getRegionCities())
				if (city.getIsKingPresent()==true)
					return city;
		return selectedCity;
	}
	
	/**
	 * Checks if the selected city already contains the emporium of the current player
	 * @return TRUE if the selected city doesn't contain the emporium, false otherwise
	 */
	private boolean checkCityNotContainsEmporium() {
		for (Emporium emporium : this.selectedCity.getCityEmporiums())
			if (emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
				return false;
		return true;
	}
	
	/**
	 * Checks if player's assistants are enough to build an emporium in the selected city
	 * @return TRUE if the assistants are enough, FALSE otherwise
	 */
	private boolean checkEnoughAssistants() {
		return this.game.getCurrentPlayer().getAssistants() >= 
				assistantsToPay();
	}
	
	/**
	 * checks if the player has enough coins
	 */
	private boolean CheckEnoughCoins() {
		return this.game.getCurrentPlayer().getCoins() >= 
				CoinsToPay();
	}
	
	/**
	 * @return the amount of assistants to pay
	 */
	private int assistantsToPay() {
		return this.selectedCity.getCityEmporiums().size()+
				2*this.game.getGameTable().getMap().getShortestPathLenght(this.selectedCity, findKing());
	}
	
	/**
	 * Calculate the coins you have to pay according to the cards you
	 * selected and the number of rainbow politics cards
	 * @return the amount of coins
	 */
	private int CoinsToPay() {
		int coinsToPay;
		this.game.getGameTable().getCouncilOfKing();
		if (this.cardsToDescard.size()==CouncilBalcony.getNumberofcouncillors())
			coinsToPay=0;
		else
			coinsToPay=((CouncilBalcony.getNumberofcouncillors()-(this.cardsToDescard.size()))*3)+1;
		for (PoliticsCard card : cardsToDescard)
			if (card.getColour().getColour() == "rainbow")
				coinsToPay++;
		return coinsToPay;
	}
	
	/**
	 * checks if the player hands cards' colour match with the colour of councillors
	 * of the selected balcony
	 */
	private boolean CheckHandSatisfiesBalcony() {
		List<Councillor> temporaryBalcony=new ArrayList<Councillor>();
		int satisfyCounter=0;
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors(); i++)
			temporaryBalcony.add(this.game.getGameTable().getCouncilOfKing().getCouncillors()[i]);
		
		for (PoliticsCard politicsCardInHand: cardsToDescard) {
			if (politicsCardInHand.getColour().getColour() == "rainbow")
				satisfyCounter++;
			for (Councillor councillorToCheck : temporaryBalcony)
				if (councillorToCheck.getColour().equals(politicsCardInHand.getColour())) {
					temporaryBalcony.remove(councillorToCheck);
					satisfyCounter++;
				}
			}
		return satisfyCounter == this.cardsToDescard.size();
	}
	
	/**
	 * Checks if, after an emporium build, the current player has completed the region.
	 * in case of that, the player picks the region board bonus, and if they are available, 
	 * he also picks one king reward tile
	 */
	private void assignRegionBonus() {
		int regionCitiesCounter=0;
		for (City city : this.selectedCity.getRegion().getRegionCities())
			for (Emporium emporium : city.getCityEmporiums())
				if (emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
					regionCitiesCounter++;
		if (regionCitiesCounter==this.selectedCity.getRegion().getRegionCities().size()) {
			this.selectedCity.getRegion().getRegionBonus().assignBonus(this.game);
			this.selectedCity.getRegion().notBonusAvailable();
		}
		if (!(this.game.getGameTable().getKingRewardTiles().isEmpty()))
			assignKingRewardTile();
	}

	/**
	 * Checks if, after an emporium build, the current player has completed the cities of that colour.
	 * in case of that, the player picks the region board bonus, and if they are available, 
	 * he also picks one king reward tile
	 */
	private void assignColourBonus() {
		int colourCitiesCounter=0;
		for (City city : this.selectedCity.getColour().getCitiesOfThisColour())
			for (Emporium emporium : city.getCityEmporiums())
				if (emporium.getEmporiumsPlayer().equals(this.game.getCurrentPlayer()))
					colourCitiesCounter++;
		if (colourCitiesCounter==this.selectedCity.getColour().getCitiesOfThisColour().size()) {
			this.selectedCity.getColour().getColorBonus().assignBonus(this.game);
			this.selectedCity.getColour().notBonusAvailable();
		}
		if (!(this.game.getGameTable().getKingRewardTiles().isEmpty()))
			assignKingRewardTile();
	}
	
	/**
	 * Remove the king reward tile from the game table and assigns it to the player
	 */
	private void assignKingRewardTile() {
		this.game.getGameTable().getKingRewardTiles().remove(0).assignBonus(this.game);
	}

}