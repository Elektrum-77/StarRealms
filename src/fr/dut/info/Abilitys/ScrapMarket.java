package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class ScrapMarket extends AbstractAbilityTarget {

	private final int nbCards;
	
	public ScrapMarket(int i) {
		super();
		nbCards = i;
	}
	
	@Override
	public void use(GameBoard gameBoard, Player player) {
		if (gameBoard.targetCardId != null) {
			
		}
	}

	@Override
	public boolean canBeUse(GameBoard gameBoard, Player player) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ArrayList<Card> getTarget(GameBoard gameBoard, Player player) {
		return gameBoard.getTradeRow().getDeck();
	}

}
