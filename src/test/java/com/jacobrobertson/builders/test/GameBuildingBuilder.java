package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Builder;
import com.jacobrobertson.builders.Rule;

public abstract class GameBuildingBuilder extends Builder implements Rule {

	private GameBuilder gameBuilder;
	private String mapName;
	private int ms;
	
	public GameBuildingBuilder(String mapName, String builderName, int ms) {
		super(builderName);
		addRule(this);
		this.mapName = mapName;
		this.ms = ms;
	}
	public GameBuildingBuilder() {
		this(null);
	}
	public GameBuildingBuilder(String mapName) {
		this(mapName, null, 250);
	}
	private void init() {
		if (getName() == null) {
			setName(getClass().getSimpleName());
		}
		if (mapName == null) {
			mapName = getName() + ".txt";
		}
		gameBuilder = new GameBuilder(mapName, this, ms);
	}
	public void start(int msBetweenMoves) {
		this.ms = msBetweenMoves;
		start();
	}
	public void start() {
		init();
		gameBuilder.start();
	}

}
