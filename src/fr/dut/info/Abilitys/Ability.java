package fr.dut.info.Abilitys;

import java.util.ArrayList;

import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public interface Ability {
	public void init(GameBoard gameBoard);
	public void use(GameBoard gameBoard);
	public boolean canBeUse(GameBoard gameBoard);
	public ArrayList<Card> getTarget(GameBoard gameBoard);
	public Ability copy();
}
