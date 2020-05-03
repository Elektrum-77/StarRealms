package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

abstract class AbstractAbilityConditional extends AbstractAbility implements AbilityConditionalInterface {
	
	final AbilityInterface ability;
	
	public AbstractAbilityConditional(AbilityInterface ability) {
		super();
		this.ability = ability;
	}
	
	@Override
	public void use(GameBoard gameBoard, Player player) {
		if (!canBeUse(gameBoard, player)) return;
	}

	@Override
	public boolean canBeUse(GameBoard gameBoard, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

}
