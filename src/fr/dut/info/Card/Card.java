package fr.dut.info.Card;

import java.awt.Image;

import fr.dut.info.Abilitys.Ability;
import fr.dut.info.Game.GameBoard;

public interface Card {

	String getId();
	Image getImage();
	String getName();
	String getFaction();
	int getCost();
	int getDefense();
	boolean getOutpost();
	Ability getPrimary();
	Ability getAlly();
	Ability getScrap();
	void disableAllAbilitys();
	void resetAbilitys();
	abstract String cardType();
	
	void usePrimary(GameBoard gameBoard);
	void useAlly(GameBoard gameBoard);
	void useScrap(GameBoard gameBoard);
	boolean isSameFaction(Card card);
	
	Ship copyShip(GameBoard gameBoard, Card card);
	Base copyBase(GameBoard gameBoard, Card card);
	
	
	
}
