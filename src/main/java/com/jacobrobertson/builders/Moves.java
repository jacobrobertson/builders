package com.jacobrobertson.builders;

import com.jacobrobertson.builders.Move.Direction;

public interface Moves {

	boolean isOutOfBounds(Point p);
	boolean isEmpty(Point p);
	boolean isStairs(Point builder, Point block);
	boolean isWalkable(Point p);
	
	boolean isClimbable(Point p);
	boolean isClimbLegal(String builder);
	
	boolean isMineable(Point p);
	boolean isMineLegal(Move move, Builder builder);
	
	boolean isPlaceable(Point builder, Point block);
	Point getPoint(Point p, Direction d);

}