package server.model.actions.standardActions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import client.modelDTO.actionsDTO.ActionDTO;
import client.modelDTO.actionsDTO.standardActions.BuildByKingDTO;
import server.model.Game;
import server.model.actions.MainAction;
import server.model.bonuses.Bonus;
import server.model.gameTable.City;
import server.model.gameTable.ConnectedBuiltCityDiscover;
import server.model.gameTable.CouncilBalcony;
import server.model.gameTable.Councillor;
import server.model.gameTable.Emporium;
import server.model.gameTable.PoliticsCard;
import server.model.player.Player;
import server.serverNotifies.ErrorServerNotify;
import server.serverNotifies.MessageServerNotify;
import server.serverNotifies.PlayerServerNotify;

/**
 * This class is the main action "build an emporium with the help of the king"
 * @author cg31
 *
 */

public class BuildByKing implements MainAction {

	private City selectedCity;
	private List<PoliticsCard> cardsToDescard;
	
	
	public void setSelectedCity(City selectedCity) {
		this.selectedCity = selectedCity;
	}

	public void setCardsToDescard(List<PoliticsCard> cardsToDescard) {
		this.cardsToDescard = cardsToDescard;
	}
	
	public City getSelectedCity() {
		return selectedCity;
	}

	public List<PoliticsCard> getCardsToDescard() {
		return cardsToDescard;
	}

	/**
	 * First of all checks if the parameters the current player gave are corrected, 
	 * then removes the cards from the hand of the player and decrements his coins, 
	 * then removes the emporium from the hand of the player an adds it on the set of emporiums 
	 * of the selected city, then assigns all the bonuses of liked cities, then decrements player's assistants
	 * @return TRUE if the action goes well, false otherwise
	 */
	@Override
	public boolean executeAction(Game game) {
		
		if(this.cardsToDescard==null || this.selectedCity==null)
			throw new NullPointerException("Paramters not setted");
		
		if (!checkCityNotContainsEmporium(game)) {
			game.notifyObserver(new ErrorServerNotify("It seems that this city arelady contains your emporium!. Try again or choose another action",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		if (!checkEnoughAssistants(game)) {
			game.notifyObserver(new ErrorServerNotify("It seems that you haven't enough assistants!. Try again or choose another action",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		if (!checkHandSatisfiesBalcony(game)) {
			game.notifyObserver(new ErrorServerNotify("It seems that these cards don't satisfy the councillors!. Try again or choose another action",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}	
		if (!checkEnoughCoins(game)) {
			game.notifyObserver(new ErrorServerNotify("It seems that you haven't enough coins!. Try again or choose another action",
					Arrays.asList(game.getCurrentPlayer())));
			return false;
		}
		
		game.getCurrentPlayer().decrementCoins(coinsToPay(game));
		game.getCurrentPlayer().decrementAssistants(assistantsToPay());
		for (PoliticsCard card : cardsToDescard)
			game.getCurrentPlayer().removeCardFromHand(card);
		
		this.assignBonus(game);
		
		game.getGameTable().getKing().moveKing(selectedCity);

		if (this.selectedCity.getRegion().isBonusAvailable())
			assignRegionBonus(game);
		if (this.selectedCity.getColour().isBonusAvailable() && !"KingColour".equals(this.selectedCity.getColour().getName()))
			assignColourBonus(game);
		
		if (game.getCurrentPlayer().getRemainigEmporiums().isEmpty()){
			game.setLastLap(true);
			game.getCurrentPlayer().incrementScore(3);
		}
		
		this.notifyPlayers(game);
		this.nextState(game);
		
		return true;
	}
	
	/**
	 * Checks if the selected city already contains the emporium of the current player
	 * @return TRUE if the selected city doesn't contain the emporium, false otherwise
	 */
	private boolean checkCityNotContainsEmporium(Game game) {
		for (Emporium emporium : this.selectedCity.getCityEmporiums())
			if (emporium.getEmporiumsPlayer().equals(game.getCurrentPlayer()))
				return false;
		return true;
	}
	
	/**
	 * Checks if player's assistants are enough to build an emporium in the selected city
	 * @return TRUE if the assistants are enough, FALSE otherwise
	 */
	private boolean checkEnoughAssistants(Game game) {
		return game.getCurrentPlayer().getNumberOfAssistants() >= 
				assistantsToPay();
	}
	
	/**
	 * checks if the player has enough coins
	 */
	private boolean checkEnoughCoins(Game game) {
		return game.getCurrentPlayer().getCoins() >= coinsToPay(game);
	}
	
	/**
	 * @return the amount of assistants to pay
	 */
	private int assistantsToPay() {
		return this.selectedCity.getCityEmporiums().size();
	}
	
	/**
	 * Calculate the coins you have to pay according to the cards you
	 * selected and the number of rainbow politics cards
	 * @return the amount of coins
	 */
	private int coinsToPay(Game game) {
		int coinsToPay;
		game.getGameTable().getCouncilOfKing();
		if (this.cardsToDescard.size()==CouncilBalcony.getNumberofcouncillors())
			coinsToPay=0;
		else
			coinsToPay=((CouncilBalcony.getNumberofcouncillors()-(this.cardsToDescard.size()))*3)+1;
		for (PoliticsCard card : cardsToDescard)
			if ("Rainbow".equals(card.getColour().getColour()))
				coinsToPay++;
		return coinsToPay + 2*game.getGameTable().getMap().getShortestPathLenght(this.selectedCity, game.getGameTable().getKing().getCity());
	}
	
	/**
	 * checks if the player hands cards' colour matches with the colour of councillors
	 * of the selected balcony
	 */
	private boolean checkHandSatisfiesBalcony(Game game) {
		List<Councillor> temporaryBalcony=new ArrayList<>();
		int satisfyCounter=0;
		for (int i=0; i<=CouncilBalcony.getNumberofcouncillors()-1; i++)
			temporaryBalcony.add(game.getGameTable().getCouncilOfKing().getCouncillors()[i]);
		
		for (PoliticsCard politicsCardInHand: this.cardsToDescard) {
			if ("Rainbow".equals(politicsCardInHand.getColour().getColour()))
				satisfyCounter++;		
			for (Councillor councillorToSatisfy : temporaryBalcony)
				if (councillorToSatisfy.getColour().getColour().equals(politicsCardInHand.getColour().getColour())) {
					temporaryBalcony.remove(councillorToSatisfy);
					satisfyCounter++;
					break;
				} 
		}
		return satisfyCounter==this.cardsToDescard.size();
	}
	
	/**
	 * Checks if, after an emporium build, the current player has completed the region.
	 * in case of that, the player picks the region board bonus, and if they are available, 
	 * he also picks one king reward tile
	 */
	private void assignRegionBonus(Game game) {
		int regionCitiesCounter=0;
		for (City city : this.selectedCity.getRegion().getRegionCities())
			for (Emporium emporium : city.getCityEmporiums())
				if (emporium.getEmporiumsPlayer().equals(game.getCurrentPlayer()))
					regionCitiesCounter++;
		if (regionCitiesCounter==this.selectedCity.getRegion().getRegionCities().size()) {
			game.getCurrentPlayer().getPlayersFinalBonus().add(
					this.selectedCity.getRegion().getRegionBonus());
			this.selectedCity.getRegion().notBonusAvailable();
			if (!(game.getGameTable().getKingRewardTiles().isEmpty()))
				assignKingRewardTile(game);
		}
	}

	/**
	 * Checks if, after an emporium build, the current player has completed the cities of that colour.
	 * in case of that, the player picks the region board bonus, and if they are available, 
	 * he also picks one king reward tile
	 */
	private void assignColourBonus(Game game) {
		int colourCitiesCounter=0;
		for (City city : this.selectedCity.getColour().getCitiesOfThisColour())
			for (Emporium emporium : city.getCityEmporiums())
				if (emporium.getEmporiumsPlayer().equals(game.getCurrentPlayer()))
					colourCitiesCounter++;
		if (colourCitiesCounter==this.selectedCity.getColour().getCitiesOfThisColour().size()) {
			game.getCurrentPlayer().getPlayersFinalBonus().add(
					this.selectedCity.getColour().getColorBonus());
			this.selectedCity.getColour().notBonusAvailable();
			if (!(game.getGameTable().getKingRewardTiles().isEmpty()))
				assignKingRewardTile(game);
		}
	}
	
	/**
	 * Remove the king reward tile from the game table and assigns it to the player
	 */
	private void assignKingRewardTile(Game game) {
		game.getCurrentPlayer().getPlayersFinalBonus().add(
				game.getGameTable().getKingRewardTiles().remove(0));
	}
	
	@Override
	public void notifyPlayers(Game game) {
		game.notifyObserver(new PlayerServerNotify(game, game.getCurrentPlayer(), 
				Arrays.asList(game.getCurrentPlayer())));
		game.notifyObserver(new MessageServerNotify("Action completed succesfully!", 
				Arrays.asList(game.getCurrentPlayer())));
		List<Player> otherPlayers=new ArrayList<>();
		for (Player player : game.getPlayers())
			if (!player.equals(game.getCurrentPlayer()))
				otherPlayers.add(player);
		game.notifyObserver(new MessageServerNotify(game.getCurrentPlayer().getName()
				+ " built an emporium in " + this.selectedCity.getName() + " with the help of the King", otherPlayers));
	}

	/**
	 * For all the bonuses to assign, it assigns the bonus and notifies the player about the bonus earned
	 * @param game is the current game status
	 */
	private void assignBonus(Game game) {
		ConnectedBuiltCityDiscover linkedCities=new ConnectedBuiltCityDiscover();
		Emporium temporaryEmporium=game.getCurrentPlayer().removeEmporium();
		this.selectedCity.addEmporium(temporaryEmporium);
		for (City city : linkedCities.getConnectedBuiltCities(game.getGameTable().getMap().getGameMap(), this.selectedCity, temporaryEmporium))
			for (Bonus bonusToAssign : city.getRewardToken().getRewardTokenBonus())
				bonusToAssign.assignBonus(game);
	}

	@Override
	public ActionDTO map() {
		return new BuildByKingDTO();
	}

}