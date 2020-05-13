package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

abstract class AbstractAbilityConditional extends AbstractAbility implements AbilityConditionalInterface {
	
	final Ability ability;
	
	public AbstractAbilityConditional(Ability ability) {
		super();
		this.ability = ability;
	}
	
}
