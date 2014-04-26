package com.jacobrobertson.builders;

public interface BuilderMap {

	int getWidth();
	int getHeight();
	MapData getMapData(int x, int y);
	Block getBlock(int x, int y);

	MapData getMapData(Point p);
	Block getBlock(Point p);
	Point getBuilderPosition(String builderName);
	Point getBuilderPosition(MoveContext context);
	
}