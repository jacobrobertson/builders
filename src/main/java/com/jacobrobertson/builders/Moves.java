package com.jacobrobertson.builders;

import com.jacobrobertson.builders.Move.Direction;

public interface Moves {

	boolean isOutOfBounds(Point p);
	boolean isEmpty(Point p);
	boolean isStairs(Point builder, Point block);
	boolean isWalkable(Point p);
	boolean isClimbable(Point p);
	boolean isMineable(Point p);
	boolean isPlaceable(Point m);
	Point getPoint(Point p, Direction d);

}