package fr.dut.info.Abilitys;

import java.util.ArrayList;
import fr.dut.info.Card.Card;
import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public interface AbillityTargetInterface extends AbilityInterface {
	public ArrayList<Card> getTarget(GameBoard gameBoard, Player player);
}
