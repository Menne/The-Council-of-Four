package model.gameTable;

import java.util.Set;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlElementWrapper;

import model.bonus.Bonus;

public class RewardToken {
	
	@XmlElementWrapper(name="rewardTokenBonus")
	@XmlElement(name="Bonus")
	private final Set<Bonus> rewardTokenBonus;
	
	public RewardToken(Set<Bonus> rewardTokenBonus){
		this.rewardTokenBonus=rewardTokenBonus;
	}
}
