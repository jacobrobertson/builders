package com.jacobrobertson.builders.test;

import com.jacobrobertson.builders.Move;
import com.jacobrobertson.builders.MoveContext;
import com.jacobrobertson.builders.Rule;

public class WalkLeftRule implements Rule {

	public Move chooseNextMove(MoveContext mc) {
		return Move.walkLeft();
	}

}
