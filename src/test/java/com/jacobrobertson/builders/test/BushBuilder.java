package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Block;
import com.jacobrobertson.builders.Blocks;
import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.Move.Direction;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.Point;

/**
 * Walks around looking for bushes to mine.
 * Once she can't find anymore bushes, she will build a bush tower.
 * 
 * @author Jacob
 */
public class BushBuilder extends GameBuildingBuilder {

	public static void main(String[] args) throws Exception {
		new BushBuilder("map-plain.txt").start();
	}
	
	private static final int MAX_CHANGES = 2;
	
	private Direction currentDirection = Direction.Left;
	private int directionChangesWithoutNewBushes = 0;
	private boolean foundBush = false;
	private boolean buildingTower = false;
	
	public BushBuilder(String mapName) {
		super(mapName);
	}
	
	public Move chooseNextMove(MoveContext context) {
		if (!buildingTower) {
			Point builderPos = context.getBuilderPosition();
			
			// Are there any bushes to mine nearby?
			// - If so, mine them
			for (Direction directionTest: Direction.values()) {
				Block block = context.getBlock(builderPos, directionTest);
				if (block != null && Blocks.Bush.equals(block.getType())) {
					foundBush = true;
					return Move.mine(directionTest);
				}
			}
	
			// - If not, walk in the current direction
			// If we reach an unpassable spot, reverse direction
			// Count how many times I walked back and forth without finding any more bushes, 
			//		and after X times, stop
			Point walkTo = context.getPoint(builderPos, currentDirection);
			if (!context.isWalkable(walkTo)) {
				currentDirection = currentDirection.getOpposite();
				if (!foundBush) {
					directionChangesWithoutNewBushes++;
				} else {
					// we found a bush, so we'll keep going
					directionChangesWithoutNewBushes = 0;
					foundBush = false;
				}
			} else {
				return Move.walk(currentDirection);
			}
		}

		// Then build the tower
		if (buildingTower || directionChangesWithoutNewBushes > MAX_CHANGES) {
			buildingTower = true;
			return Move.placeBlock(Blocks.Bush, Direction.Down); 
		} else {
			// TODO in this case we're actually skipping a turn... could make this better
			return null;
		}
	}
	
}
