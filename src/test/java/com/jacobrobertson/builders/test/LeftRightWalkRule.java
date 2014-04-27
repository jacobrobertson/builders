package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.Move.Direction;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.Point;
import com.jacobrobertson.builders.Rule;

public class LeftRightWalkRule implements Rule {

	private Direction d = Direction.Left;
	
	public Move chooseNextMove(MoveContext context) {
		Point p = context.getBuilderPosition();
		Point w = context.getPoint(p, d);
		if (!context.isWalkable(w) && !context.isStairs(p, w)) {
			d = d.getOpposite();
		}
		return Move.walk(d);
	}

	
}
