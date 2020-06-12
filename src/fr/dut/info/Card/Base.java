package fr.dut.info.Card;

import java.awt.Image;

import fr.dut.info.Abilitys.*;
import fr.dut.info.Game.GameBoard;


public class Base extends AbstractCards{
	
	private int defense;
	private final boolean outpost;
	

	public Base(int id, Image image, String name, String faction, int cost, int defense, boolean outpost, Ability primary, Ability ally, Ability scrap) {
		super(id, image, name, faction, cost, primary, ally, scrap);
		this.defense= defense;
		this.outpost=outpost;
	}

	public Base(String name, String faction, int cost, int defense, boolean outpost, Ability primary, Ability ally, Ability scrap) {
		super(-1, null, name, faction, cost, primary, ally, scrap);
		this.defense= defense;
		this.outpost=outpost;
	}
	
	@Override
	public int getDefense() {
		return defense;
	}
	
	@Override
	public boolean isOutpost() {
		return outpost;
	}

	public String type() {
		return "Base";
	}
	
	@Override
	public Card copy(GameBoard gameBoard) {
		return new Base(
				gameBoard.getAnId(), image,
				name, faction,
				cost,
				defense, outpost,
				primary, ally, scrap
				);
	}
	
	public Card copy() {
		return new Base(
				name, faction,
				cost,
				defense, outpost,
				primary, ally, scrap
				);
	}
}
