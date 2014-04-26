package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.BuilderMap;
import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.Moves;
import com.jacobrobertson.builders.Point;
import com.jacobrobertson.builders.Rule;
import com.jacobrobertson.builders.Move.Direction;

public class MinerRule implements Rule {

	private Direction[] directions;
	
	public MinerRule(Direction... directions) {
		this.directions = directions;
	}

	public Move chooseNextMove(MoveContext context) {
		
		Moves moves = context.getMoves();
		BuilderMap map = context.getMap(); 
		
		Point b = map.getBuilderPosition(context);
		
		for (Direction d: directions) {
			Point m = moves.getPoint(b, d);
			if (moves.isMineable(m)) {
				return Move.mine(d);
			}
		}
		
		return null;
	}
	
}
