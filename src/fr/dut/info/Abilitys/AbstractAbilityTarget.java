package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

abstract class AbstractAbilityTarget extends AbstractAbility implements AbillityTargetInterface {

	final int nbCards;
	int used;
	
	public AbstractAbilityTarget(int i) {
		super();
		nbCards = i;
		used = i;
	}

	
	@Override
	public void init(GameBoard gameBoard) {
		super.init(gameBoard);
		used = nbCards;
	}
	

}
