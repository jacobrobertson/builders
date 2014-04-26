package com.jacobrobertson.builders;

public class Blocks {

	public static final String Rock = "Rock";
	public static final String Bush = "Bush";
	
	
	public static Block rock() {
		return new Block(Rock, new BlockBehavior(false, true, true, false, true, true));
	}
	public static Block bush() {
		return new Block(Bush, new BlockBehavior(true, true, true, true, true, true));
	}
	
}
