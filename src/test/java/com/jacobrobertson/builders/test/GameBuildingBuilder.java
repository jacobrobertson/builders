package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Builder;
import com.jacobrobertson.builders.Rule;

public abstract class GameBuildingBuilder extends Builder implements Rule {

	private GameBuilder gameBuilder;
	
	public GameBuildingBuilder(String mapName, String builderName, int ms) {
		super(builderName);
		addRule(this);
		gameBuilder = new GameBuilder(mapName, this, ms);
	}
	public GameBuildingBuilder(String mapName) {
		this(mapName, "My Builder", 250);
	}
	public void start(int msBetweenMoves) {
		gameBuilder.setMsBetweenMoves(msBetweenMoves);
		start();
	}
	public void start() {
		gameBuilder.start();
	}

}
