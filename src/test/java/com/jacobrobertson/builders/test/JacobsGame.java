package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Move.Direction;

public class JacobsGame {

	public static void main(String[] args) throws Exception {
		
		GameBuilder game = new GameBuilder("map1.txt", "Jacob's Builder", 250);

		
		game.addRule(new MinerRule(Direction.Left));
		game.addRule(new PlacerRule(Direction.Right));
		
		game.addRule(new PlacerRule(Direction.Up));
		game.addRule(new ClimbRule());

		game.addRule(new MinerRule(Direction.Down));
		
//		game.addRule(new MinerRule(Direction.Right));
//		game.addRule(new PlacerRule(Direction.Left));
		
		game.addRule(new LeftRightWalkRule());
//		game.addRule(new MinerRule(Direction.Up));
		game.addRule(new ClimbDownRule());

		// NO-OP for now
//		game.addRule(new PlacerRule(Direction.Down));
		
		game.start();
	}
	
}
