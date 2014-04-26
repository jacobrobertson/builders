package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.BuilderMap;
import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.Moves;
import com.jacobrobertson.builders.Point;
import com.jacobrobertson.builders.Rule;
import com.jacobrobertson.builders.Move.Direction;

public class LeftRightWalkRule implements Rule {

	private Direction d = Direction.Left;
	
	public Move chooseNextMove(MoveContext context) {
		Moves moves = context.getMoves();
		BuilderMap map = context.getMap();
		
		Point p = map.getBuilderPosition(context.getBuilder().getName());
		Point w = moves.getPoint(p, d);
		if (!moves.isWalkable(w) && !moves.isStairs(p, w)) {
			d = d.getOpposite();
		}
		return Move.walk(d);
	}

	
}
