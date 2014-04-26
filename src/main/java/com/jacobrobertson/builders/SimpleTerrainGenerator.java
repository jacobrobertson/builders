package com.jacobrobertson.builders;

public class SimpleTerrainGenerator {

	public void generate(BuilderMapImpl map) {
		int w = map.getWidth();
		int h = map.getHeight();
		for (int i = 0; i < w; i++) {
			map.setSimpleMapData(Blocks.rock(), i, h - 1);
			map.setSimpleMapData(Blocks.rock(), i, h - 2);
			map.setSimpleMapData(Blocks.bush(), i, h - 3);
		}
	}
	
}
