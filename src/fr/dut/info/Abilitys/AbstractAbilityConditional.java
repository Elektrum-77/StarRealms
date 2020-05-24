package fr.dut.info.Abilitys;

abstract class AbstractAbilityConditional extends AbstractAbility implements AbilityConditionalInterface {
	
	final Ability ability;
	
	public AbstractAbilityConditional(Ability ability) {
		super();
		this.ability = ability;
	}
	
}
