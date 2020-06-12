package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

abstract class AbstractAbilityTarget extends AbstractAbility{

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
	
	@Override
	public boolean canBeUse(GameBoard gameBoard) {
		return false;
	}

	@Override
	public void use(GameBoard gameBoard) {
		if (!canBeUse(gameBoard)) return;
	}

}
