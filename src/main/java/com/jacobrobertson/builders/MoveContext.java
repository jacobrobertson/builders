package com.jacobrobertson.builders;

import com.jacobrobertson.builders.Move.Direction;

/**
 * TODO try to merge this with MoveMaker and Moves somehow - there is too much delegating, etc going on here.
 * @author Jacob
 */
public class MoveContext implements Moves, BuilderMap {

	private BuilderMap map;
	private Builder builder;
	private Moves moveMaker;
	public MoveContext(BuilderMap map, Builder builder, MoveMaker moveMaker) {
		this.map = map;
		this.builder = builder;
		this.moveMaker = moveMaker;
	}
	BuilderMap getMap() {
		return map;
	}
	public Builder getBuilder() {
		return builder;
	}
	public Point getBuilderPosition() {
		return map.getBuilderPosition(this);
	}
	public boolean isOutOfBounds(Point p) {
		return moveMaker.isOutOfBounds(p);
	}
	public boolean isEmpty(Point p) {
		return moveMaker.isEmpty(p);
	}
	public boolean isStairs(Point builder, Point block) {
		return moveMaker.isStairs(builder, block);
	}
	public boolean isWalkable(Point p) {
		return moveMaker.isWalkable(p);
	}
	public boolean isClimbable(Point p) {
		return moveMaker.isClimbable(p);
	}
	public boolean isClimbLegal(String builder) {
		return moveMaker.isClimbLegal(builder);
	}
	public boolean isMineable(Point p) {
		return moveMaker.isMineable(p);
	}
	public boolean isMineable(Direction d) {
		Point p = getPointFromBuilder(d);
		return moveMaker.isMineable(p);
	}
	public boolean isWalkable(Direction d) {
		Point p = getPointFromBuilder(d);
		return moveMaker.isWalkable(p);
	}
	public boolean isMineLegal(Move move, Builder builder) {
		return moveMaker.isMineLegal(move, builder);
	}
	public boolean isPlaceable(Direction d) {
		Point b = getBuilderPosition();
		Point p = getPointFromBuilder(d);
		return moveMaker.isPlaceable(b, p);
	}
	public boolean isPlaceable(Point builder, Point block) {
		return moveMaker.isPlaceable(builder, block);
	}
	public Point getPointFromBuilder(Direction d) {
		Point p = getBuilderPosition();
		return getPoint(p, d);
	}
	public Point getPoint(Point p, Direction d) {
		return moveMaker.getPoint(p, d);
	}
	public int getWidth() {
		return map.getWidth();
	}
	public int getHeight() {
		return map.getHeight();
	}
	public MapData getMapData(int x, int y) {
		return map.getMapData(x, y);
	}
	public Block getBlock(int x, int y) {
		return map.getBlock(x, y);
	}
	public MapData getMapData(Point p) {
		return map.getMapData(p);
	}
	public Block getBlock(Point p) {
		return map.getBlock(p);
	}
	public Block getBlock(Point p, Direction d) {
		Point m = getPoint(p, d);
		return getBlock(m);
	}

	public Point getBuilderPosition(String builderName) {
		return map.getBuilderPosition(builderName);
	}
	public Point getBuilderPosition(Builder builder) {
		return map.getBuilderPosition(builder);
	}
	public Point getBuilderPosition(MoveContext context) {
		return map.getBuilderPosition(context);
	}
	public Backpack getBackpack() {
		return builder.getBackpack();
	}
}
