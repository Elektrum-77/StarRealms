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
	public void use(GameBoard gameBoard, Player player) {
	}

	@Override
	public boolean canBeUse(GameBoard gameBoard, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Card> getTarget(GameBoard gameBoard, Player player) {
		// TODO Auto-generated method stub
		return null;
	}

}
