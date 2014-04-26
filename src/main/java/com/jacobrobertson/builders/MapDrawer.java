package com.jacobrobertson.builders;

public interface MapDrawer {

	/**
	 * Assumes any other state info (such as zoom level) is already known by the MapDrawer
	 */
	void drawMap(BuilderMapImpl map);
	
}
