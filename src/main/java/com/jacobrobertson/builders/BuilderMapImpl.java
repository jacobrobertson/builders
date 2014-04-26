package com.jacobrobertson.builders;

import java.util.HashMap;
import java.util.Map;

/**
 * Simple contents of the map.
 * Adds read/write methods.
 * @author Jacob
 */
public class BuilderMapImpl implements BuilderMap {

	private Map<Integer, Map<Integer, MapData>> map = new HashMap<Integer, Map<Integer,MapData>>();
	private Map<String, Point> builders = new HashMap<String, Point>();
	private int width;
	private int height;
	
	public BuilderMapImpl() {
	}
	
	public void setBuilderPosition(String builder, Point p) {
		builders.put(builder, p);
	}

	// TODO will include an offset, and different width/height
	public BuilderMap getVisibleMap() {
		return null;
	}
	public Point getBuilderPosition(MoveContext context) {
		return getBuilderPosition(context.getBuilder().getName());
	}
	public Point getBuilderPosition(Builder builder) {
		return getBuilderPosition(builder.getName());
	}
	public Point getBuilderPosition(String name) {
		Point p = builders.get(name);
		return new Point(p.x, p.y);
	}
	public Iterable<String> getBuilderNames() {
		return builders.keySet();
	}
	
	public int getWidth() {
		if (width == 0) {
			initSize();
		}
		return width;
	}
	private void initSize() {
		for (Integer x: map.keySet()) {
			if (x > width) {
				width = x;
			}
			for (Integer y: map.get(x).keySet()) {
				if (y > height) {
					height = y;
				}
			}
		}
		height++;
		width++;
	}
	public int getHeight() {
		if (height == 0) {
			initSize();
		}
		return height;
	}
	public Block removeBlock(Point p) {
		MapData md = map.get(p.x).remove(p.y);
		return md.getBlock();
	}
	public Block getBlock(Point p) {
		return getBlock(p.x, p.y);
	}
	public Block getBlock(int x, int y) {
		MapData md = getMapData(x, y);
		if (md == null) {
			return null;
		}
		return md.getBlock();
	}
	public MapData getMapData(Point p) {
		return getMapData(p.x, p.y);
	}
	public MapData getMapData(int x, int y) {
		Map<Integer, MapData> sub = map.get(x);
		if (sub != null) {
			return sub.get(y);
		}
		return null;
	}
	public void setMapData(int x, int y, MapData data) {
		Map<Integer, MapData> sub = map.get(x);
		if (sub == null) {
			sub = new HashMap<Integer, MapData>();
			map.put(x, sub);
		}
		sub.put(y,  data);
	}
	public void setSimpleMapData(Block block, int x, int y) {
		MapData md = new MapData(block);
		setMapData(x, y, md);
	}

}
