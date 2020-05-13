package fr.dut.info.Abilitys;

import fr.dut.info.Game.GameBoard;
import fr.dut.info.Player.Player;

public class AndConditionalAbility extends AbstractAbilityConditional{
	
	private Ability abilitySecond;
	
	public AndConditionalAbility(Ability ability, Ability other) {
		super(ability);
		abilitySecond = other;
	}
	
	@Override
	public Ability copy() {
		return new AndConditionalAbility(ability, abilitySecond);
	}

	@Override
	public void use(GameBoard gameBoard) {
		ability.use(gameBoard);
		abilitySecond.use(gameBoard);
	}

	@Override
	public boolean canBeUse(GameBoard gameBoard) {
		return true;
	}
	
	
	
}
