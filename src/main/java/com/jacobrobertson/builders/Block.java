package com.jacobrobertson.builders;

/**
 * Could later contain more information, like the amount of damage taken.
 * @author Jacob
 */
public class Block {

	private String type;
	private BlockBehavior behavior;
	
	public Block(String type, BlockBehavior behavior) {
		this.type = type;
		this.behavior = behavior;
	}
	public String getType() {
		return type;
	}
	public BlockBehavior getBehavior() {
		return behavior;
	}
	
}
