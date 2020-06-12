package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

abstract class AbstractAbility implements Ability {
	
	boolean used = false;

	public AbstractAbility() {}
	
	public ArrayList<Card> getTarget(GameBoard gameBoard) { return null; }

	public void use(GameBoard gameBoard) {
		if (used) return;
		used = true;
	}
	
	public boolean canBeUse(GameBoard gameBoard) {
		return !used;
	}
	
	public void init(GameBoard gameBoard) {
		used = false;
	}
}
