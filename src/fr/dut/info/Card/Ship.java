package fr.dut.info.Card;

import java.awt.Image;

import javax.swing.ImageIcon;

import fr.dut.info.Abilitys.*;
import fr.dut.info.Game.GameBoard;

public class Ship extends AbstractCards{
	
	public Ship(int id, Image image, String name, String faction, int cost, Ability primary, Ability ally, Ability scrap) {
		super(id, image, name, faction, cost, primary, ally, scrap);
	}

	public Ship(Image image, String name, String faction, int cost, Ability primary, Ability ally, Ability scrap) {
		super(-1, image, name, faction, cost, primary, ally, scrap);
	}

	@Override
	public boolean isOutpost() {
		return false;
	}

	public String type() {
		return "Ship";
	}

	@Override
	public Card copy(GameBoard gameBoard) {
		return new Ship(
				gameBoard.getAnId(), image,
				name, faction,
				cost,
				primary, ally, scrap
				);
	}
	
	public Card copy() {
		return new Ship(
				image,
				name, faction,
				cost,
				primary, ally, scrap
				);
	}
	

	

}
