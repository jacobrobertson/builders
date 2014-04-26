package com.jacobrobertson.builders;

public class MoveContext {

	private BuilderMap map;
	private Builder builder;
	private Moves moves;
	public MoveContext(BuilderMap map, Builder builder, Moves moves) {
		this.map = map;
		this.builder = builder;
		this.moves = moves;
	}
	public BuilderMap getMap() {
		return map;
	}
	public Builder getBuilder() {
		return builder;
	}
	public Moves getMoves() {
		return moves;
	}
	
}
