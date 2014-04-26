package com.jacobrobertson.builders;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * @author Jacob
 */
public class BackpackImpl implements Backpack {

	private List<Block> contents = new ArrayList<Block>();
	
	public void add(Block data) {
		contents.add(data);
	}
	public Block remove(String blockType) {
		for (int i = 0; i < contents.size(); i++) {
			Block one = contents.get(i);
			if (one.getType().equals(blockType)) {
				contents.remove(one);
				return one;
			}
		}
		return null;
	}
	/**
	 * Read only.
	 */
	public List<Block> getContents() {
		return Collections.unmodifiableList(contents);
	}
	
	
}