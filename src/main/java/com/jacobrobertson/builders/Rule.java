package com.jacobrobertson.builders;

public interface Rule {

	Move chooseNextMove(MoveContext context);
	
}
