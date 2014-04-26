package com.jacobrobertson.builders.test;

public class JezecsGame {

	public static void main(String[] args) throws Exception {
		
		GameBuilder game = new GameBuilder("jezec1.txt", "jezecs-walker", 500);
		game.addRule(new ClimbRule());
		game.addRule(new LeftRightWalkRule());
		
		game.start();
	}

}
