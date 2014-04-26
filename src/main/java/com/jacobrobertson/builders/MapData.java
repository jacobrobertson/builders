package com.jacobrobertson.builders;

/**
 * Encapsulates information about what is on one square.
 * For now, this will not be complex, and will just contain the block data.
 * In future, there could be more than one block in a square, etc.
 * 
 * @author Jacob
 */
public class MapData {

	private Block blockData;

	public MapData(Block blockData) {
		this.blockData = blockData;
	}
	public Block getBlock() {
		return blockData;
	}
	
}
