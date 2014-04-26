package com.jacobrobertson.builders.test;

public class JacobsGame {

	public static void main(String[] args) throws Exception {
		
		GameBuilder game = new GameBuilder("map1.txt", "simple-walker", 500);
		game.addRule(new LeftRightWalkRule());
		game.addRule(new ClimbRule());
		
		game.start();
	}
	
}
