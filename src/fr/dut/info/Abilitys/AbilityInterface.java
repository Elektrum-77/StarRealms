package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public interface AbilityInterface {
	public void use(GameBoard gameBoard, Player player);
	public boolean canBeUse(GameBoard gameBoard, Player player);
}
