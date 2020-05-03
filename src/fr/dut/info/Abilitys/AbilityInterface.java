package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public interface AbilityInterface {
	public void init(GameBoard gameBoard, Player player);
	public void use(GameBoard gameBoard, Player player);
	public boolean canBeUse(GameBoard gameBoard, Player player);
	public ArrayList<Card> getTarget(GameBoard gameBoard, Player player);
}
