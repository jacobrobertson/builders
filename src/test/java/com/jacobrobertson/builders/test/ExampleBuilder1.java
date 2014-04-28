package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.Move.Direction;
import com.jacobrobertson.builders.MoveContext;

/**
 * Mines to the left, then moves, then places to the right.
 * 
 * @author Jacob
 */
public class ExampleBuilder1 extends GameBuildingBuilder {

	public static void main(String[] args) throws Exception {
		new ExampleBuilder1().start();
	}
	
	public Move chooseNextMove(MoveContext context) {

		if (context.isMineable(Direction.Left)) {
			return Move.mine(Direction.Left);
		} else if (context.isPlaceable(Direction.Right) && !context.getBackpack().isEmpty()) {
			return Move.placeBlock(context.getBackpack().getFirstBlockType(), Direction.Right);
		} else if (context.isWalkable(Direction.Left)) {
			return Move.walk(Direction.Left);
		} else {
			// I guess we're out of moves!
			return null;
		}
		
	}
	
}
