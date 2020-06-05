package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class DrawCard extends AbstractAbilityTarget {

	public DrawCard(int i) {
		super(i);
	}

	@Override
	public void use(GameBoard gameBoard) {
		super.use(gameBoard); //fait les test d'usagibilité
		gameBoard.playingPlayerDrawCards(1);
		used-=1;
	}

	@Override
	public boolean canBeUse(GameBoard gameBoard) {
		return (used > 0);
	}

	@Override
	public ArrayList<Card> getTarget(GameBoard gameBoard) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Ability copy() {
		return new DrawCard(nbCards);
	}

}
