package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Backpack;
import com.jacobrobertson.builders.Block;
import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.Move.Direction;
import com.jacobrobertson.builders.MoveContext;
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
		
		Point b = context.getBuilderPosition();
		
		for (Direction d: directions) {
			Point m = context.getPoint(b, d);
			if (context.isPlaceable(b, m)) {
				return Move.placeBlock(type, d);
			}
		}
		
		return null;
	}

}
