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
	public int count(String blockType) {
		int count = 0;
		for (int i = 0; i < contents.size(); i++) {
			Block one = contents.get(i);
			if (blockType.equals(one.getType())) {
				count++;
			}
		}
		return count;
	}
	public boolean isEmpty() {
		return contents.isEmpty();
	}
	public int size() {
		return contents.size();
	}
	public String getFirstBlockType() {
		if (contents.isEmpty()) {
			return null;
		} else {
			return contents.get(0).getType();
		}
	}
	public boolean contains(String blockType) {
		return count(blockType) > 0;
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
