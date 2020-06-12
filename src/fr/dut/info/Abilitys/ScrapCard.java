package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class ScrapCard extends AbstractAbilityTarget {

	public ScrapCard(int i) {
		super(i);
	}


	@Override
	public void init(GameBoard gameBoard) {
		super.init(gameBoard);
		used = nbCards;
	}
	
	@Override
	public void use(GameBoard gameBoard) {
		super.use(gameBoard);
//		if (gameBoard.targetCardId != null && used>0) {
//		}
	}

	@Override
	public boolean canBeUse(GameBoard gameBoard) {
		return (used>0);
	}

	@Override
	public ArrayList<Card> getTarget(GameBoard gameBoard) {
		return gameBoard.getScarpHeap().getCopyList();
	}

	@Override
	public Ability copy() {
		return new ScrapCard(nbCards);
	}
}
