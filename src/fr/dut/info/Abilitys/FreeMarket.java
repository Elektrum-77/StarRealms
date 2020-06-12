package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class FreeMarket extends AbstractAbilityTarget {

	public FreeMarket(int i) {
		super(i);
	}

	@Override
	public void use(GameBoard gameBoard) {
		super.use(gameBoard);
	}

	@Override
	public ArrayList<Card> getTarget(GameBoard gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ability copy() {
		return new FreeMarket(nbCards);
	}

}
