package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class ScrapMarket extends AbstractAbilityTarget {

	public ScrapMarket(int i) {
		super(i);
	}


	@Override
	public void init(GameBoard gameBoard, Player player) {
		super.init(gameBoard, player);
		used = nbCards;
	}
	
	@Override
	public void use(GameBoard gameBoard, Player player) {
		if (gameBoard.targetCardId != null) {
			if (used>0);
		}
	}

	@Override
	public boolean canBeUse(GameBoard gameBoard, Player player) {
		return (used>0);
	}

	@Override
	public ArrayList<Card> getTarget(GameBoard gameBoard, Player player) {
		return gameBoard.getTradeRow().getDeck();
	}

}
