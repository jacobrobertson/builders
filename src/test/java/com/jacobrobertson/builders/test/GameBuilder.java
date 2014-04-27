package com.jacobrobertson.builders.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jacobrobertson.builders.Builder;
import com.jacobrobertson.builders.BuilderMapImpl;
import com.jacobrobertson.builders.FileMapParser;
import com.jacobrobertson.builders.MoveMaker;
import com.jacobrobertson.builders.Rule;
import com.jacobrobertson.builders.swing.SwingMapComponent;

public class GameBuilder {

	private SwingMapComponent comp;
	private BuilderMapImpl map = new BuilderMapImpl();
	private Builder builder;
	private List<Builder> builders;
	private int msBetweenMoves;
	
	public GameBuilder(String mapName, String builderName, int msBetweenMoves) throws Exception {
		this(mapName, new Builder("simple-walker"), msBetweenMoves);
	}
	public GameBuilder(String mapName, Builder builder, int msBetweenMoves) throws Exception {
		comp = new SwingMapComponent("Builders - " + mapName + " - " + builder.getName());
		this.msBetweenMoves = msBetweenMoves;
		
		builders = new ArrayList<Builder>();
		builders.add(builder);

		addMap(mapName, map, builders);
	}
	public void start() {
		MoveMaker mover = new MoveMaker(map, comp, builders, msBetweenMoves);
		mover.start();
	}
	private void addMap(String mapFile, BuilderMapImpl map, List<Builder> builders) throws Exception {
		InputStream in = ClassLoader.getSystemResourceAsStream(mapFile);
		FileMapParser parser = new FileMapParser(in);
		parser.generate(map, builders);
	}
	
	public void addRule(Rule rule) {
		builder.addRule(rule);
	}
	
}
