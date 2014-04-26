package com.jacobrobertson.builders.test;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import com.jacobrobertson.builders.Builder;
import com.jacobrobertson.builders.BuilderMapImpl;
import com.jacobrobertson.builders.FileMapParser;
import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.MoveMaker;
import com.jacobrobertson.builders.Rule;
import com.jacobrobertson.builders.swing.SwingMapComponent;

public class SwingTester {

	public static void main(String[] args) throws Exception {
		SwingMapComponent comp = new SwingMapComponent();
		comp.init();
		
		BuilderMapImpl map = new BuilderMapImpl();
		
		List<Builder> builders = new ArrayList<Builder>();
		Builder builder = getSimpleBuilder();
		builders.add(builder);

		addMap("map1.txt", map, builders);
		
		MoveMaker mover = new MoveMaker(map, comp, builders);
		
		mover.start();
	}
	private static void addMap(String mapFile, BuilderMapImpl map, List<Builder> builders) throws Exception {
		InputStream in = ClassLoader.getSystemResourceAsStream(mapFile);
		FileMapParser parser = new FileMapParser(in);
		parser.generate(map, builders);
	}
	
	private static Builder getSimpleBuilder() {
		final String name = "simple-walker";
		List<Rule> rules = new ArrayList<Rule>();

		Rule climb = new Rule() {
			public Move chooseNextMove(MoveContext mc) {
				return Move.climb();
			}
		};
		Rule walk = new LeftRightWalkRule();
		
		rules.add(climb);
		rules.add(walk);
		
		
		Builder b = new Builder(name, rules);
		return b;
	}
	
}
