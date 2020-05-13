package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

abstract class AbstractAbility implements Ability {
	
	public AbstractAbility() {}
	
	public void init(GameBoard gameBoard) {}
	
	public ArrayList<Card> getTarget(GameBoard gameBoard) { return null; }

}
