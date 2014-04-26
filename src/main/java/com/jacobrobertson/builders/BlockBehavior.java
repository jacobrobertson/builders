package com.jacobrobertson.builders;

public class BlockBehavior {

//	private boolean sinkable;
	
	private boolean canStandIn;
	private boolean supporter;
	private boolean mineable;
	private boolean climbable;
	private boolean stairway;
	private boolean attachable;
	
	public BlockBehavior(boolean canStandIn, boolean supporter,
			boolean mineable, boolean climbable, boolean stairway, boolean attachable) {
		this.canStandIn = canStandIn;
		this.supporter = supporter;
		this.mineable = mineable;
		this.climbable = climbable;
		this.stairway = stairway;
		this.attachable = attachable;
	}
	public boolean canStandIn() {
		return canStandIn;
	}
	public boolean isSupporter() {
		return supporter;
	}
	public boolean isMineable() {
		return mineable;
	}
	public boolean isClimbable() {
		return climbable;
	}
	public boolean isStairway() {
		return stairway;
	}
	public boolean isAttachable() {
		return attachable;
	}
	
	
}
