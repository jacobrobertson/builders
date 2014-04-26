package com.jacobrobertson.builders;

public class Move {

	public static enum MoveType {
		Walk, Mine, Place, GameOver
	}
	public static enum Direction {
		Up, Down, Left, Right;
		static {
			Up.opposite = Down;
			Down.opposite = Up;
			Left.opposite = Right;
			Right.opposite = Left;
		}
		
		private Direction opposite;
		public Direction getOpposite() {
			return opposite;
		}
	}
	private String blockType;
	private MoveType moveType;
	private Direction direction;
	
	public static Move placeBlock(String blockType, Direction direction) {
		return new Move(MoveType.Place, direction, blockType);
	}
	public static Move walk(Direction direction) {
		return new Move(MoveType.Walk, direction, null);
	}
	public static Move walkLeft() {
		return new Move(MoveType.Walk, Direction.Left, null);
	}
	public static Move fall() {
		return new Move(MoveType.Walk, Direction.Down, null);
	}
	public static Move walkRight() {
		return new Move(MoveType.Walk, Direction.Right, null);
	}
	public static Move climb() {
		return new Move(MoveType.Walk, Direction.Up, null);
	}
	public static Move climbDown() {
		return new Move(MoveType.Walk, Direction.Down, null);
	}
	public static Move mine(Direction direction) {
		return new Move(MoveType.Mine, direction, null);
	}
	
	private Move(MoveType moveType, Direction direction, String blockType) {
		this.moveType = moveType;
		this.direction = direction;
		this.blockType = blockType;
	}
	
	public Direction getDirection() {
		return direction;
	}
	public MoveType getMoveType() {
		return moveType;
	}
	public String getBlockType() {
		return blockType;
	}
	@Override
	public String toString() {
		return moveType.toString() + " " + direction.toString();
	}
	
}
