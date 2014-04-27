package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.Move.Direction;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.Point;
import com.jacobrobertson.builders.Rule;

public class MinerRule implements Rule {

	private Direction[] directions;
	
	public MinerRule(Direction... directions) {
		this.directions = directions;
	}

	public Move chooseNextMove(MoveContext context) {
		
		for (Direction d: directions) {
			Point m = context.getPointFromBuilder(d);
			if (context.isMineable(m)) {
				return Move.mine(d);
			}
		}
		
		return null;
	}
	
}
