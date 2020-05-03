package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

abstract class AbstractAbility implements AbilityInterface {
	
	public AbstractAbility() {}
	
	public void init(GameBoard gameBoard, Player player) {}
	
	public ArrayList<Card> getTarget(GameBoard gameBoard, Player player) { return null; }

}
