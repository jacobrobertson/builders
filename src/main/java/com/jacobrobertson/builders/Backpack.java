package com.jacobrobertson.builders;

import java.util.List;

public interface Backpack {
	List<Block> getContents();
	int count(String blockType);
}
