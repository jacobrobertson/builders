package com.jacobrobertson.builders;

import java.util.List;

public interface Backpack {
	List<Block> getContents();
	int count(String blockType);
	boolean contains(String blockType);
	int size();
	boolean isEmpty();
	/**
	 * Meaning "what is on the top of the backpack?"
	 */
	String getFirstBlockType();
}
