package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public abstract class AbstractAbilityPoint extends AbstractAbility {
	
	final int point;
	
	public AbstractAbilityPoint(int p) {
		super();
		point = p;
	}
	
	@Override
	public boolean canBeUse(GameBoard gameBoard, Player player) {
		return true;
	}
	
}
