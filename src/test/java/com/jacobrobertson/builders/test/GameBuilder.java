package com.jacobrobertson.builders.test;

import java.io.IOException;
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
	private List<Builder> builders;
	private int msBetweenMoves;
	
	public GameBuilder(String mapName, String builderName, int msBetweenMoves) {
		this(mapName, new Builder(builderName), msBetweenMoves);
	}
	public GameBuilder(String mapName, Builder builder, int msBetweenMoves) {
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
	public void setMsBetweenMoves(int msBetweenMoves) {
		this.msBetweenMoves = msBetweenMoves;
	}
	private void addMap(String mapFile, BuilderMapImpl map, List<Builder> builders) {
		InputStream in = ClassLoader.getSystemResourceAsStream(mapFile);
		FileMapParser parser;
		try {
			parser = new FileMapParser(in);
			parser.generate(map, builders);
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * TODO deprecate this - won't work well if we have more than one builder
	 * @param rule
	 */
	public void addRule(Rule rule) {
		for (Builder builder: builders) {
			builder.addRule(rule);
		}
	}
	
}
