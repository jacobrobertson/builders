package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Backpack;
import com.jacobrobertson.builders.Block;
import com.jacobrobertson.builders.BuilderMap;
import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.Move.Direction;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.Moves;
import com.jacobrobertson.builders.Point;
import com.jacobrobertson.builders.Rule;

public class PlacerRule implements Rule {

	
	private Direction[] directions;
	
	public PlacerRule(Direction... directions) {
		this.directions = directions;
	}
	
	public Move chooseNextMove(MoveContext context) {
		
		String type = null;
		Backpack backpack = context.getBuilder().getBackpack();
		for (Block block: backpack.getContents()) {
			type = block.getType();
		}
		if (type == null) {
			return null;
		}
		
		Moves moves = context.getMoves();
		BuilderMap map = context.getMap(); 
		
		Point b = map.getBuilderPosition(context);
		
		for (Direction d: directions) {
			Point m = moves.getPoint(b, d);
			if (moves.isPlaceable(b, m)) {
				return Move.placeBlock(type, d);
			}
		}
		
		return null;
	}

}
