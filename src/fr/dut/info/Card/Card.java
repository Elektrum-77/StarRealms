package fr.dut.info.Card;

import java.awt.Image;

import fr.dut.info.Abilitys.Ability;
import fr.dut.info.Game.GameBoard;

public interface Card {

	int getId();
	Image getImage();
	String getName();
	String getFaction();
	int getCost();
	int getDefense();
	boolean isOutpost();
	String type();

	boolean hasPrimary();
	boolean hasAlly();
	boolean hasScrap();
	
	void usePrimary(GameBoard gameBoard);
	void useAlly(GameBoard gameBoard);
	void useScrap(GameBoard gameBoard);
	void init(GameBoard gameBoard);
	boolean isSameFaction(Card card);
	Card copy(GameBoard gameBoard);
	
	
	
	
	
}
